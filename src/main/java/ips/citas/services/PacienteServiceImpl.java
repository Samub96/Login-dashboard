package ips.citas.services;

import ips.citas.dto.OrdenResponseDTO;
import ips.citas.dto.PacienteRequestDTO;
import ips.citas.dto.PacienteResponseDTO;
import ips.citas.dto.TerapiaResponseDTO;
import ips.citas.entity.Orden;
import ips.citas.entity.Paciente;
import ips.citas.entity.Terapia;
import ips.citas.exeption.ResourceNotFoundException;
import ips.citas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public PacienteResponseDTO crearPaciente(PacienteRequestDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setDocumentoIdentidad(pacienteDTO.getDocumentoIdentidad());
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setApellido(pacienteDTO.getApellido());
        paciente.setEntidadSalud(pacienteDTO.getEntidadSalud());
        pacienteRepository.save(paciente);
        return convertToDto(paciente);
    }

    @Override
    public PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + id));

        paciente.setDocumentoIdentidad(pacienteDTO.getDocumentoIdentidad());
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setApellido(pacienteDTO.getApellido());
        paciente.setEntidadSalud(pacienteDTO.getEntidadSalud());

        pacienteRepository.save(paciente); // El save es opcional si usas @Transactional
        return convertToDto(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente no encontrado con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public PacienteResponseDTO obtenerPacientePorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + id));
        return convertToDto(paciente);
    }

    @Override
    public List<PacienteResponseDTO> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteResponseDTO obtenerPacientePorDocumento(String documentoIdentidad) {
        Paciente paciente = pacienteRepository.findByDocumentoIdentidad(documentoIdentidad)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con documento: " + documentoIdentidad));
        return convertToDto(paciente);
    }

    @Override
    public List<PacienteResponseDTO> listarPacientesPorTerapia(String nombreTerapia) {
        List<Paciente> pacientes = pacienteRepository.findPacientesByNombreTerapia(nombreTerapia);
        return pacientes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    private PacienteResponseDTO convertToDto(Paciente paciente) {
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(paciente.getId());
        dto.setDocumentoIdentidad(paciente.getDocumentoIdentidad());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setEntidadSalud(paciente.getEntidadSalud());

        // Mapear las órdenes (si existen)
        if (paciente.getOrdenes() != null) {
            dto.setOrdenes(
                    paciente.getOrdenes().stream()
                            .map(this::convertOrdenToDto)
                            .collect(Collectors.toList())
            );
        } else {
            dto.setOrdenes(Collections.emptyList());
        }

        return dto;
    }

    private OrdenResponseDTO convertOrdenToDto(Orden orden) {
        OrdenResponseDTO ordenDTO = new OrdenResponseDTO();
        ordenDTO.setId(orden.getId());
        ordenDTO.setNumeroOrden(orden.getNumeroOrden());

        // Mapear las terapias (si existen)
        if (orden.getTerapias() != null) {
            ordenDTO.setTerapias(
                    orden.getTerapias().stream()
                            .map(this::convertTerapiaToDto)
                            .collect(Collectors.toList())
            );
        } else {
            ordenDTO.setTerapias(Collections.emptyList());
        }

        return ordenDTO;
    }

    private TerapiaResponseDTO convertTerapiaToDto(Terapia terapia) {
        TerapiaResponseDTO terapiaDTO = new TerapiaResponseDTO();
        terapiaDTO.setId(terapia.getId());
        terapiaDTO.setNombreTerapia(terapia.getNombreTerapia());
        terapiaDTO.setCantidadSesiones(terapia.getCantidadSesiones());
        terapiaDTO.setSesionesRealizadas(terapia.getSesionesRealizadas());
        terapiaDTO.setSesionesPendientes(terapia.getCantidadSesiones() - terapia.getSesionesRealizadas());

        return terapiaDTO;
    }
    @Override
    public PacienteResponseDTO obtenerPacientePorNumeroOrden(String numeroOrden) {
        Paciente paciente = pacienteRepository.findPacienteByNumeroOrden(numeroOrden)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con la orden: " + numeroOrden));
        return convertToDto(paciente);
    }

}
