package ru.dbelokursky.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
public class UserEntity {

  @Id
  private long id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private Integer userStatus;
}

