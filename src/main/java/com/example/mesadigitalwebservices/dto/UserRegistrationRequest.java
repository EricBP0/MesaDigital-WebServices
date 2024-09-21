package com.example.mesadigitalwebservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;
}
