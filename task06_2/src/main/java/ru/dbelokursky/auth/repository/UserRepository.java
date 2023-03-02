package ru.dbelokursky.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dbelokursky.auth.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  User findByLogin(String login);
}
