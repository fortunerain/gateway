package com.example.gateway;

import com.example.gateway.model.AuthKey;
import com.example.gateway.repository.AuthKeyRedisRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RedisTest1 {

  @Autowired
  private AuthKeyRedisRepository authKeyRedisRepository;

//  @AfterEach
  public void tearDown() throws Exception {
    authKeyRedisRepository.deleteAll();
  }

  @Test
  public void 기본_등록_조회기능() {
    //given
    String id = "123";
    LocalDateTime refreshTime = LocalDateTime.of(2018, 5, 26, 0, 0);

    AuthKey authKey = AuthKey.builder()
        .id(id)
        .secret("456")
        .refreshTime(refreshTime)
        .build();

    //when
    authKeyRedisRepository.save(authKey);

    //then
    AuthKey saveAuthKey = authKeyRedisRepository.findById(id).get();
    assertThat(saveAuthKey.getSecret()).isEqualTo("456");
    assertThat(saveAuthKey.getRefreshTime()).isEqualTo(refreshTime);
  }
}

