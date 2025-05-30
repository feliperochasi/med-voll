package br.com.feliperochasi.med.voll.api.medic;

import br.com.feliperochasi.med.voll.api.address.DataAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDataMedic(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Specialised especialidade,
        @NotBlank
        String telefone,
        @NotNull
        @Valid
        DataAddress endereco) {
}
