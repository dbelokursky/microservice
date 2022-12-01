package ru.dbelokursky.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dbelokursky.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  User findById(long id);

  void deleteById(long id);

  User save(User user);

}
