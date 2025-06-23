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

public interface TerapiaService {
    TerapiaResponseDTO crearTerapia(TerapiaRequestDTO terapiaDTO);
    TerapiaResponseDTO actualizarTerapia(Long id, TerapiaRequestDTO terapiaDTO);
    TerapiaResponseDTO actualizarTerapiaSesion(Long id, TerapiaRequestDTO terapiaDTO);
    void eliminarTerapia(Long id);
    TerapiaResponseDTO obtenerTerapiaPorId(Long id);
    List<TerapiaResponseDTO> listarTerapiasPorOrden(Long ordenId);
}

