package ru.dbelokursky.auth.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("auth_role")
public class Role {

  @Id
  @Column("id")
  private Long id;

  @Column("name")
  private String name;

  @Column("created")
  @CreatedDate
  private Date created;

  @Column("updated")
  @LastModifiedDate
  private Date updated;
}
