package com.processador.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateClienteDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        @NotBlank(message = "CPF/CNPJ é obrigatório")
        @Size(min = 11, max = 14, message = "CPF/CNPJ inválido")
        String cpfCnpj,

        String telefone) {
}
