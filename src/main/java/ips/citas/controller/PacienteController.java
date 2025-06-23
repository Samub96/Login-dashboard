package ips.citas.controller;


import ips.citas.dto.PacienteRequestDTO;
import ips.citas.dto.PacienteResponseDTO;
import ips.citas.entity.Paciente;
import ips.citas.exeption.ResourceNotFoundException;
import ips.citas.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SECRETARIA')")
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> crearPaciente(@Valid @RequestBody PacienteRequestDTO pacienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.crearPaciente(pacienteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> obtenerPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obtenerPacientePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> actualizarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteRequestDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(id, pacienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }
    @GetMapping("/buscar")
    public ResponseEntity<PacienteResponseDTO> buscarPorDocumento(@RequestParam String documento) {
        PacienteResponseDTO respuesta = pacienteService.obtenerPacientePorDocumento(documento);
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/por-terapia")
    public ResponseEntity<List<PacienteResponseDTO>> listarPorTerapia(@RequestParam String terapia) {
        List<PacienteResponseDTO> respuesta = pacienteService.listarPacientesPorTerapia(terapia);
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/buscar-por-orden")
    public ResponseEntity<PacienteResponseDTO> buscarPorNumeroOrden(@RequestParam String numeroOrden) {
        PacienteResponseDTO respuesta = pacienteService.obtenerPacientePorNumeroOrden(numeroOrden);
        return ResponseEntity.ok(respuesta);
    }


}

