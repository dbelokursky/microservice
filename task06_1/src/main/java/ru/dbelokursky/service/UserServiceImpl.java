package ru.dbelokursky.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dbelokursky.exception.UserNotFoundException;
import ru.dbelokursky.mapper.UserMapper;
import ru.dbelokursky.repository.UserRepository;
import ru.dbelokursky.rest.model.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Override
  public void create(User user) {
    user.setUserStatus(1);
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

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username).map(userMapper::userEntityToUser).orElseThrow(UserNotFoundException::new);
  }
}
