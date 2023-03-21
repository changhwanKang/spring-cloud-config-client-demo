package com.example.spingcloudconfigclientdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final PropertiesConfig propertiesConfig;
  @GetMapping("/test")
  @ResponseStatus(HttpStatus.OK)
  public String test(){
    return propertiesConfig.getTestString();
  }

}
