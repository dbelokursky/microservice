package ru.dbelokursky.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserEntity {

  @Id
  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private Integer userStatus;
}

