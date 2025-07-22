package ips.citas.controller;

import ips.citas.dto.OrdenRequestDTO;
import ips.citas.dto.OrdenResponseDTO;
import ips.citas.services.OrdenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SECRETARIA')")
    @PostMapping
    public ResponseEntity<OrdenResponseDTO> crearOrden(@Valid @RequestBody OrdenRequestDTO ordenDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.crearOrden(ordenDTO));
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SECRETARIA')")
    @PutMapping("/{id}")
    public ResponseEntity<OrdenResponseDTO> actualizarOrden(@PathVariable Long id, @Valid @RequestBody OrdenRequestDTO ordenDTO) {
        return ResponseEntity.ok(ordenService.actualizarOrden(id, ordenDTO));
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SECRETARIA')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SECRETARIA')")
    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponseDTO> obtenerOrdenPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.obtenerOrdenPorId(id));
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SECRETARIA')")
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<OrdenResponseDTO>> listarOrdenesPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(ordenService.listarOrdenesPorPaciente(pacienteId));
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SECRETARIA')")
    @GetMapping("/buscar-por-numero")
    public ResponseEntity<OrdenResponseDTO> buscarPorNumeroOrden(@RequestParam String numeroOrden) {
        OrdenResponseDTO orden = ordenService.buscarPorNumeroOrden(numeroOrden);
        if (orden != null) {
            return ResponseEntity.ok(orden);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

