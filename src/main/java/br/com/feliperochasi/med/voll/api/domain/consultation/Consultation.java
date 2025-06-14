package br.com.feliperochasi.med.voll.api.domain.consultation;

import br.com.feliperochasi.med.voll.api.domain.medic.Medic;
import br.com.feliperochasi.med.voll.api.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Consultation")
@Table(name = "consultations")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medic_id")
    private Medic medic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime date;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private ReasonCancel reasonCancel;

    public Consultation(Long id, Medic medic, Patient patient, LocalDateTime date) {
        this.medic = medic;
        this.patient = patient;
        this.date = date;
        this.active = true;
    }

    public void delete(ReasonCancel cancel) {
        this.active = false;
        this.reasonCancel = cancel;
    }
}
