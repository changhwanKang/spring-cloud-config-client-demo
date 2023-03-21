package com.example.spingcloudconfigclientdemo.sqs;

import io.awspring.cloud.messaging.listener.Acknowledgment;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsSqsListener {


  private final ContextRefresher contextRefresher;

  @SqsListener(value = "sqs-paychat.fifo", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
  public void listen(@Payload String info, @Headers Map<String, String> headers) {
    log.info("-------------------------------------start SqsListener");
    log.info("-------------------------------------info {}", info);
    log.info("-------------------------------------headers {}", headers);
    if(headers.get("MessageGroupId").equals("sqs-test")){
      contextRefresher.refresh();
    }
  }


}
