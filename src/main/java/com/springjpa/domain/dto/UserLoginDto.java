package com.springjpa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDto {
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "E-mail deve ser preenchido")
    @NotEmpty(message = "E-mail deve ser preenchido")
    @NotNull(message = "E-mail não foi informado")
    private String pass;
}
