package com.daou.test.db.repository;

import com.daou.test.db.entity.UserRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepository extends CrudRepository<UserRedis, Long> {
}
