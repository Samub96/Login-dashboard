package ips.citas.services;


import ips.citas.dto.PacienteRequestDTO;
import ips.citas.dto.PacienteResponseDTO;

import java.util.List;


public interface PacienteService {
    PacienteResponseDTO crearPaciente(PacienteRequestDTO pacienteDTO);
    PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO pacienteDTO);
    PacienteResponseDTO actualizarPacienteByDoc(PacienteRequestDTO pacienteDTO);
    void eliminarPaciente(Long id);
    PacienteResponseDTO obtenerPacientePorId(Long id);
    List<PacienteResponseDTO> listarPacientes();
    PacienteResponseDTO obtenerPacientePorDocumento(String documentoIdentidad);
    List<PacienteResponseDTO> listarPacientesPorTerapia(String nombreTerapia);
    PacienteResponseDTO obtenerPacientePorNumeroOrden(String numeroOrden);
}
