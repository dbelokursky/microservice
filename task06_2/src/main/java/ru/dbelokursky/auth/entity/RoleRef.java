package ru.dbelokursky.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("AUTH_USER_AUTH_ROLE")
public class RoleRef {

  @Column("AUTH_ROLE")
  private long roleId;
}
