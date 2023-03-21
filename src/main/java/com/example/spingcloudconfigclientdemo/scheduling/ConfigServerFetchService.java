package com.example.spingcloudconfigclientdemo.scheduling;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ConfigServerFetchService {


  public ConfigServerResponse fetch(){

    RestTemplate restTemplate = new RestTemplate();

    return restTemplate.getForObject("http://localhost:8888/test-server/dev",ConfigServerResponse.class);
  }

}
