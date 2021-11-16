package com.example.gateway.service;

import com.example.gateway.model.AuthKey;
import com.example.gateway.repository.AuthKeyRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

  private final AuthKeyRedisRepository authKeyRedisRepository;

  public void validateAuthorization(HttpHeaders httpHeaders) {
    List<String> authorizations = httpHeaders.get("Authorization");
    if (authorizations == null) {
      throw new IllegalArgumentException("Authorization 필수임.");
    }

    String authorization = authorizations.stream().findFirst().get();
    AuthKey saveAuthKey = authKeyRedisRepository.findById(authorization)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Key임."));
    log.info(saveAuthKey.getSecret());

  }
}
