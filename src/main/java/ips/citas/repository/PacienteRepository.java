package ips.citas.repository;

import ips.citas.entity.Orden;
import ips.citas.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDocumentoIdentidad(String documentoIdentidad);
    @Query("SELECT DISTINCT p FROM Paciente p JOIN p.ordenes o JOIN o.terapias t WHERE t.nombreTerapia = :nombreTerapia")
    List<Paciente> findPacientesByNombreTerapia(@Param("nombreTerapia") String nombreTerapia);
    @Query("SELECT p FROM Paciente p JOIN p.ordenes o WHERE o.numeroOrden = :numeroOrden")
    Optional<Paciente> findPacienteByNumeroOrden(@Param("numeroOrden") String numeroOrden);

}
