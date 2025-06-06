package br.com.feliperochasi.med.voll.api.domain.patient;

import br.com.feliperochasi.med.voll.api.domain.address.Address;

public record DetailsDataPatient(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Address endereco
) {
    public DetailsDataPatient(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
    }
}
