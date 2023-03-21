package com.example.spingcloudconfigclientdemo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Getter
@Component
@RefreshScope
public class PropertiesConfig {

  @Value("${test}")
  private String testString;


}
