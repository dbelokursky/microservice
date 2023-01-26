package ru.dbelokursky.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dbelokursky.mapper.UserMapper;
import ru.dbelokursky.repository.UserRepository;
import ru.dbelokursky.rest.model.User;
import ru.dbelokursky.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public void create(User user) {
    userRepository.save(userMapper.userToUserEntity(user));
  }

  @Override
  public User get(long id) {
    return userRepository.findById(id).map(userMapper::userEntityToUser).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public void update(User user) {
    userRepository.save(userMapper.userToUserEntity(user));
  }

  @Override
  public void delete(long id) {
    userRepository.deleteById(id);
  }
}
