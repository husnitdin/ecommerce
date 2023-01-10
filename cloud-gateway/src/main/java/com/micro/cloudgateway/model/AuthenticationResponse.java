package com.micro.cloudgateway.model;


import lombok.*;

import java.util.Collection;
import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    private String userId;
    private String accessToken;
    private String refreshToken;
    private long expiresAt;
    private Collection<String> authorityList;
}
