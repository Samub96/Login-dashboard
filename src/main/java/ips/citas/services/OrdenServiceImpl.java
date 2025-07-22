package ips.citas.services;

import ips.citas.dto.OrdenRequestDTO;
import ips.citas.dto.OrdenResponseDTO;
import ips.citas.dto.TerapiaResponseDTO;
import ips.citas.entity.Orden;
import ips.citas.entity.Paciente;
import ips.citas.entity.Terapia;
import ips.citas.exeption.ResourceNotFoundException;
import ips.citas.repository.OrdenRepository;
import ips.citas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public OrdenResponseDTO crearOrden(OrdenRequestDTO ordenDTO) {
        Paciente paciente = pacienteRepository.findById(ordenDTO.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + ordenDTO.getPacienteId()));

        Orden orden = new Orden();
        orden.setNumeroOrden(ordenDTO.getNumeroOrden());
        orden.setPaciente(paciente);

        Orden ordenGuardada = ordenRepository.save(orden);
        return convertToDto(ordenGuardada);
    }

    @Override
    public OrdenResponseDTO actualizarOrden(Long id, OrdenRequestDTO ordenDTO) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + id));

        orden.setNumeroOrden(ordenDTO.getNumeroOrden());
        orden.setPaciente(pacienteRepository.findById(ordenDTO.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + ordenDTO.getPacienteId())));

        return convertToDto(orden);
    }

    @Override
    public void eliminarOrden(Long id) {
        if (!ordenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Orden no encontrada con ID: " + id);
        }
        ordenRepository.deleteById(id);
    }

    @Override
    public OrdenResponseDTO obtenerOrdenPorId(Long id) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + id));
        return convertToDto(orden);
    }

    @Override
    public List<OrdenResponseDTO> listarOrdenesPorPaciente(Long pacienteId) {
        List<Orden> ordenes = ordenRepository.findByPacienteId(pacienteId);
        return ordenes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrdenResponseDTO buscarPorNumeroOrden(String numeroOrden) {
        Optional<Orden> ordenOpt = ordenRepository.findByNumeroOrden(numeroOrden);
        if (ordenOpt.isEmpty()) return null;
        return convertToDto(ordenOpt.get());
    }

    private OrdenResponseDTO convertToDto(Orden orden) {
        OrdenResponseDTO dto = new OrdenResponseDTO();
        dto.setId(orden.getId());
        dto.setNumeroOrden(orden.getNumeroOrden());

        if (orden.getTerapias() != null) {
            dto.setTerapias(
                    orden.getTerapias().stream()
                            .map(this::convertTerapiaToDto)
                            .collect(Collectors.toList())
            );
        } else {
            dto.setTerapias(Collections.emptyList());
        }

        return dto;
    }

    private TerapiaResponseDTO convertTerapiaToDto(Terapia terapia) {
        TerapiaResponseDTO dto = new TerapiaResponseDTO();
        dto.setId(terapia.getId());
        dto.setNombreTerapia(terapia.getNombreTerapia());
        dto.setCantidadSesiones(terapia.getCantidadSesiones());
        dto.setSesionesRealizadas(terapia.getSesionesRealizadas());
        dto.setSesionesPendientes(terapia.getCantidadSesiones() - terapia.getSesionesRealizadas());
        return dto;
    }
}
