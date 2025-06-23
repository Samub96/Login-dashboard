package ips.citas.services;

import ips.citas.dto.OrdenResponseDTO;
import ips.citas.dto.PacienteRequestDTO;
import ips.citas.dto.PacienteResponseDTO;
import ips.citas.dto.TerapiaResponseDTO;
import ips.citas.entity.Orden;
import ips.citas.entity.Paciente;
import ips.citas.entity.Terapia;
import ips.citas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface PacienteService {
    PacienteResponseDTO crearPaciente(PacienteRequestDTO pacienteDTO);
    PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO pacienteDTO);
    void eliminarPaciente(Long id);
    PacienteResponseDTO obtenerPacientePorId(Long id);
    List<PacienteResponseDTO> listarPacientes();
    PacienteResponseDTO obtenerPacientePorDocumento(String documentoIdentidad);
    List<PacienteResponseDTO> listarPacientesPorTerapia(String nombreTerapia);
    PacienteResponseDTO obtenerPacientePorNumeroOrden(String numeroOrden);
}
