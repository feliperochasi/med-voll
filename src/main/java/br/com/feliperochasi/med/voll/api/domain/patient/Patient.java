package br.com.feliperochasi.med.voll.api.domain.patient;

import br.com.feliperochasi.med.voll.api.domain.address.Address;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Patient")
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String cpf;

    @Embedded
    private Address address;

    private boolean active;

    public Patient(@Valid RegisterDataPatient dataPatient) {
        this.name = dataPatient.nome();
        this.email = dataPatient.email();
        this.phone = dataPatient.telefone();
        this.cpf = dataPatient.cpf();
        this.address = new Address(dataPatient.endereco());
        this.active = true;
    }

    public void exclude() {
        this.active = false;
    }

    public void update(UpdateDataPatient dataPatient) {
        if(dataPatient.nome() != null) {
            this.name = dataPatient.nome();
        }

        if(dataPatient.telefone() != null) {
            this.phone = dataPatient.telefone();
        }

        if(dataPatient.endereco() != null) {
            this.address.update(dataPatient.endereco());
        }
    }
}
