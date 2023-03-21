package com.example.spingcloudconfigclientdemo.scheduling;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.config.client.ConfigClientStateHolder;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Closeable;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class ConfigScheduling implements Closeable, EnvironmentAware {

  private static Log log = LogFactory.getLog(ConfigServicePropertySourceLocator.class);

  private final AtomicBoolean running = new AtomicBoolean(false);

  private final ContextRefresher refresher;

  private Environment environment;

  private final ConfigServerFetchService fetchService;

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  @PostConstruct
  public void start() {
    this.running.compareAndSet(false, true);
  }

  @Scheduled(initialDelayString = "${spring.cloud.config.watch.initialDelay:180000}",
    fixedDelayString = "${spring.cloud.config.watch.delay:500}")
  public void watchConfigServer() {
    if (this.running.get()) {
      String newState = fetchService.fetch().getVersion();
      String oldState = ConfigClientStateHolder.getState();
      // only refresh if state has changed
      if (stateChanged(oldState, newState)) {
        ConfigClientStateHolder.setState(newState);
        this.refresher.refresh();
      }
    }
  }

  /* for testing */ boolean stateChanged(String oldState, String newState) {
    return (!hasText(oldState) && hasText(newState)) || (hasText(oldState) && !oldState.equals(newState));
  }

  @Override
  public void close() {
    this.running.compareAndSet(true, false);
  }

}
