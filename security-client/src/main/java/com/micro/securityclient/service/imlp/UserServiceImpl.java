package com.micro.securityclient.service.imlp;

import com.micro.securityclient.entity.ConfirmationTokenEntity;
import com.micro.securityclient.entity.UserEntity;
import com.micro.securityclient.repository.UserRepository;
import com.micro.securityclient.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    @Override
    public String signUpUser(UserEntity userEntity) {
        boolean userExists = userRepository
                .findByEmail(userEntity.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(userEntity.getPassword());

        userEntity.setPassword(encodedPassword);

        userRepository.save(userEntity);

        String token = UUID.randomUUID().toString();

        ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userEntity
        );

        confirmationTokenServiceImpl.saveConfirmationToken(
                confirmationTokenEntity);

//        TODO: SEND EMAIL

        return token;
    }

    @Override
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
