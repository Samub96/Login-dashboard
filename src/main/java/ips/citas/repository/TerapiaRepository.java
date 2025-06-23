package ips.citas.repository;

import ips.citas.entity.Paciente;
import ips.citas.entity.Terapia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TerapiaRepository extends JpaRepository<Terapia, Long> {
    List<Terapia> findByOrdenId(Long ordenId);


}
