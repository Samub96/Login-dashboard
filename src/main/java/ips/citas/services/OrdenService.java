package ips.citas.services;

import ips.citas.dto.OrdenRequestDTO;
import ips.citas.dto.OrdenResponseDTO;
import ips.citas.dto.TerapiaResponseDTO;
import ips.citas.entity.*;
import ips.citas.exeption.ResourceNotFoundException;
import ips.citas.repository.OrdenRepository;
import ips.citas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface OrdenService {
    OrdenResponseDTO crearOrden(OrdenRequestDTO ordenDTO);
    OrdenResponseDTO actualizarOrden(Long id, OrdenRequestDTO ordenDTO);
    void eliminarOrden(Long id);
    OrdenResponseDTO obtenerOrdenPorId(Long id);
    List<OrdenResponseDTO> listarOrdenesPorPaciente(Long pacienteId);
    OrdenResponseDTO buscarPorNumeroOrden(String numeroOrden);
}

