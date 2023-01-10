package com.micro.securityclient.service;

import com.micro.securityclient.entity.ConfirmationTokenEntity;

import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationTokenEntity token);

    Optional<ConfirmationTokenEntity> getToken(String token);

    int setConfirmedAt(String token);

}
