package ips.citas.repository;

import ips.citas.entity.Orden;
import ips.citas.entity.Terapia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByPacienteId(Long pacienteId);
    Optional<Orden> findByNumeroOrden(String numeroOrden);
}
