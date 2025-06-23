package ips.citas.controller;

import ips.citas.dto.TerapiaRequestDTO;
import ips.citas.dto.TerapiaResponseDTO;
import ips.citas.dto.TerapiaUpdateSesionesDTO;
import ips.citas.services.TerapiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/terapias")
public class TerapiaController {

    @Autowired
    private TerapiaService terapiaService;

    @PostMapping
    public ResponseEntity<TerapiaResponseDTO> crearTerapia(@Valid @RequestBody TerapiaRequestDTO terapiaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(terapiaService.crearTerapia(terapiaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerapiaResponseDTO> actualizarTerapia(@PathVariable Long id, @Valid @RequestBody TerapiaRequestDTO terapiaDTO) {
        return ResponseEntity.ok(terapiaService.actualizarTerapia(id, terapiaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTerapia(@PathVariable Long id) {
        terapiaService.eliminarTerapia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerapiaResponseDTO> obtenerTerapiaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(terapiaService.obtenerTerapiaPorId(id));
    }

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<List<TerapiaResponseDTO>> listarTerapiasPorOrden(@PathVariable Long ordenId) {
        return ResponseEntity.ok(terapiaService.listarTerapiasPorOrden(ordenId));
    }
    @PutMapping("/{id}/sesion")
    public ResponseEntity<TerapiaResponseDTO> actualizarTerapiaSesion(
            @PathVariable Long id,
            @RequestBody TerapiaRequestDTO terapiaDTO) {

        TerapiaResponseDTO respuesta = terapiaService.actualizarTerapiaSesion(id, terapiaDTO);
        return ResponseEntity.ok(respuesta);
    }
    @PutMapping("/sesiones/{id}")
    public ResponseEntity<TerapiaResponseDTO> actualizarSesiones(
            @PathVariable Long id,
            @Valid @RequestBody TerapiaUpdateSesionesDTO sesionesDTO) {
        return ResponseEntity.ok(terapiaService.actualizarSesiones(id, sesionesDTO));
    }

}

