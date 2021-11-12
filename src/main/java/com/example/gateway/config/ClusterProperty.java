package com.example.gateway.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Data
@ToString
@Component
@Profile({"local"})
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class ClusterProperty {
  private List<String> nodes;
  private int maxRedirects;
  private String password;

  @PostConstruct
  public void init() {
    log.debug("{}", this.toString());
  }
}
