package ru.dbelokursky.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dbelokursky.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);
}
