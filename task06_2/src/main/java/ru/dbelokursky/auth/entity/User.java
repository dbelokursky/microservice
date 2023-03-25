package ru.dbelokursky.auth.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Table("auth_user")
@Accessors(chain = true)
public class User {

  @Id
  @Column("id")
  private long id;

  @Column("login")
  private String login;

  @Column("password")
  private String password;

  @Column("status")
  private int status;

  private Set<RoleRef> roleIds = new HashSet<>();

  @Column("created")
  @CreatedDate
  private Date created;

  @Column("updated")
  @LastModifiedDate
  private Date updated;
}
