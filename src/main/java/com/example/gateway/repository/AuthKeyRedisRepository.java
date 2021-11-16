package com.example.gateway.repository;

import com.example.gateway.model.AuthKey;
import org.springframework.data.repository.CrudRepository;

public interface AuthKeyRedisRepository extends CrudRepository<AuthKey, String> {
}
