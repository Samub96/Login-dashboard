package ips.citas.controller;

import ips.citas.dto.OrdenRequestDTO;
import ips.citas.dto.OrdenResponseDTO;
import ips.citas.services.OrdenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping
    public ResponseEntity<OrdenResponseDTO> crearOrden(@Valid @RequestBody OrdenRequestDTO ordenDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.crearOrden(ordenDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenResponseDTO> actualizarOrden(@PathVariable Long id, @Valid @RequestBody OrdenRequestDTO ordenDTO) {
        return ResponseEntity.ok(ordenService.actualizarOrden(id, ordenDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponseDTO> obtenerOrdenPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.obtenerOrdenPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<OrdenResponseDTO>> listarOrdenesPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(ordenService.listarOrdenesPorPaciente(pacienteId));
    }
}

