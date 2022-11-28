package ru.dbelokursky.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dbelokursky.rest.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
