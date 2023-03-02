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
@Table("AUTH_USER")
@Accessors(chain = true)
public class User {

  @Id
  @Column("ID")
  private long id;

  @Column("LOGIN")
  private String login;

  @Column("PASSWORD")
  private String password;

  @Column("STATUS")
  private int status;

  private Set<RoleRef> roleIds = new HashSet<>();

  @Column("CREATED")
  @CreatedDate
  private Date created;

  @Column("UPDATED")
  @LastModifiedDate
  private Date updated;
}
