package ru.dbelokursky.auth.security;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusEnum {
  ACTIVE(1, "Учетная запись активна"),
  BLOCKED(0, "Учетная запись деактивирована");

  public final int code;
  public final String description;
}
