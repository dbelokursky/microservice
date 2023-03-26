package ru.dbelokursky.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("auth_user_auth_role")
public class RoleRef {

  @Column("auth_role")
  private Long roleId;
}
