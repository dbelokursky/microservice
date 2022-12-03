package ru.dbelokursky.service;

public interface UserService {

  void create(ru.dbelokursky.rest.model.User user);

  ru.dbelokursky.rest.model.User get(long id);

  void update(ru.dbelokursky.rest.model.User user);

  void delete(long id);
}
