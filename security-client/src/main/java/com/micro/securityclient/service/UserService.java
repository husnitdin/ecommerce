package com.micro.securityclient.service;

import com.micro.securityclient.entity.UserEntity;

public interface UserService {

    String signUpUser(UserEntity userEntity);

    int enableAppUser(String email);
}
