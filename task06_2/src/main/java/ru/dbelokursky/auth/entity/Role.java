package ru.dbelokursky.auth.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("AUTH_ROLE")
public class Role {

  @Id
  @Column("ID")
  private long id;

  @Column("NAME")
  private String name;

  @Column("CREATED")
  @CreatedDate
  private Date created;

  @Column("UPDATED")
  @LastModifiedDate
  private Date updated;
}
