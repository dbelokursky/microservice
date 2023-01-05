package ru.dbelokursky.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dbelokursky.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  UserEntity userToUserEntity(ru.dbelokursky.rest.model.User user);

  ru.dbelokursky.rest.model.User userEntityToUser(UserEntity user);
}
