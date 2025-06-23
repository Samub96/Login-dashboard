package ips.citas.services;

import ips.citas.dto.TerapiaRequestDTO;
import ips.citas.dto.TerapiaResponseDTO;
import ips.citas.entity.Orden;
import ips.citas.entity.Terapia;
import ips.citas.exeption.ResourceNotFoundException;
import ips.citas.repository.OrdenRepository;
import ips.citas.repository.TerapiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TerapiaServiceImpl implements TerapiaService {

    @Autowired
    private TerapiaRepository terapiaRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public TerapiaResponseDTO crearTerapia(TerapiaRequestDTO terapiaDTO) {
        Orden orden = ordenRepository.findById(terapiaDTO.getOrdenId())
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + terapiaDTO.getOrdenId()));

        Terapia terapia = new Terapia();
        terapia.setNombreTerapia(terapiaDTO.getNombreTerapia());
        terapia.setCantidadSesiones(terapiaDTO.getCantidadSesiones());
        terapia.setSesionesRealizadas(0);
        terapia.setOrden(orden);

        Terapia terapiaGuardada = terapiaRepository.save(terapia);
        return convertToDto(terapiaGuardada);
    }

    @Override
    public TerapiaResponseDTO actualizarTerapia(Long id, TerapiaRequestDTO terapiaDTO) {
        Terapia terapia = terapiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terapia no encontrada con ID: " + id));

        terapia.setNombreTerapia(terapiaDTO.getNombreTerapia());
        terapia.setCantidadSesiones(terapiaDTO.getCantidadSesiones());
        terapia.setOrden(ordenRepository.findById(terapiaDTO.getOrdenId())
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + terapiaDTO.getOrdenId())));

        return convertToDto(terapia);
    }

    @Override
    public TerapiaResponseDTO actualizarTerapiaSesion(Long id, TerapiaRequestDTO terapiaDTO) {
        Terapia terapia = terapiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terapia no encontrada con ID: " + id));

        terapia.setCantidadSesiones(terapiaDTO.getCantidadSesiones());

        terapiaRepository.save(terapia); // Persistimos el cambio

        return convertToDto(terapia);
    }

    @Override
    public void eliminarTerapia(Long id) {
        if (!terapiaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Terapia no encontrada con ID: " + id);
        }
        terapiaRepository.deleteById(id);
    }

    @Override
    public TerapiaResponseDTO obtenerTerapiaPorId(Long id) {
        Terapia terapia = terapiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terapia no encontrada con ID: " + id));
        return convertToDto(terapia);
    }

    @Override
    public List<TerapiaResponseDTO> listarTerapiasPorOrden(Long ordenId) {
        List<Terapia> terapias = terapiaRepository.findByOrdenId(ordenId);
        return terapias.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TerapiaResponseDTO convertToDto(Terapia terapia) {
        TerapiaResponseDTO dto = new TerapiaResponseDTO();
        dto.setId(terapia.getId());
        dto.setNombreTerapia(terapia.getNombreTerapia());
        dto.setCantidadSesiones(terapia.getCantidadSesiones());
        dto.setSesionesRealizadas(terapia.getSesionesRealizadas());
        dto.setSesionesPendientes(terapia.getCantidadSesiones() - terapia.getSesionesRealizadas());
        return dto;
    }
}
