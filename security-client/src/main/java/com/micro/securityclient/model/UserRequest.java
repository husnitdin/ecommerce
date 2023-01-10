package com.micro.securityclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.micro.securityclient.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRequest extends BaseDto {
    @JsonProperty
    @Length(max = 64)
    private String firstName;

    @JsonProperty
    @Length(max = 64)
    private String lastName;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @JsonProperty
    @Length(max = 16)
    private String phone;

    @JsonProperty
    @Length(max = 255)
    private String email;

    @NotBlank()
    @Length(min = 8, message = "Password length should be 6 symbols at least")
    private String password;
}
