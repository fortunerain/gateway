package com.example.gateway.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash("auth")
@Builder
public class AuthKey {

  @Id
  private String id;
  private String secret;
  private LocalDateTime refreshTime;

  public void refresh(String secret, LocalDateTime refreshTime){
    if(refreshTime.isAfter(this.refreshTime)){ // 저장된 데이터보다 최신 데이터일 경우
      this.secret = secret;
      this.refreshTime = refreshTime;
    }
  }
}

