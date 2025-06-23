package ips.citas.repository;

import ips.citas.entity.Orden;
import ips.citas.entity.Terapia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByPacienteId(Long pacienteId);

}
