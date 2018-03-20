package com.symphony.springbootdata.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "girl")
public class GrilPro {
  private Integer age;
  private String cupSize;

}
