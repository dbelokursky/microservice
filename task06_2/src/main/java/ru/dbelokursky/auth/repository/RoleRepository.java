package ru.dbelokursky.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dbelokursky.auth.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

  Role findByName(String roleUser);

  List<Role> findAllByIdIn(List<Long> ids);
}
