package com.micro.securityclient.service;


import com.micro.securityclient.model.UserRequest;

public interface RegistrationService {

    String register(UserRequest request);

    String confirmToken(String token);

    String buildEmail(String name, String link);

}
