package ru.dbelokursky.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.dbelokursky.auth.dto.UserRegisterRequest;

@FeignClient(value = "userServiceClient", url = "${app.userServiceUrl}")
public interface UserServiceClient {

  @PostMapping
  void createProfile(@RequestBody UserRegisterRequest request);
}
