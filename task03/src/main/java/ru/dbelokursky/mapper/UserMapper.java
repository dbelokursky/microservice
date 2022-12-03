package ru.dbelokursky.mapper;

import org.mapstruct.Mapper;
import ru.dbelokursky.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserEntity userToUserEntity(ru.dbelokursky.rest.model.User user);

  ru.dbelokursky.rest.model.User userEntityToUser(UserEntity user);
}
