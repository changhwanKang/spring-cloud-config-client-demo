package com.example.spingcloudconfigclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpingCloudConfigClientDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpingCloudConfigClientDemoApplication.class, args);
  }

}
