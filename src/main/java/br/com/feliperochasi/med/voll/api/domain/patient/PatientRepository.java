package br.com.feliperochasi.med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable p);

    @Query("SELECT p.active FROM Patient p WHERE p.id = :idPatient")
    Boolean findActiveById(Long idPatient);
}
