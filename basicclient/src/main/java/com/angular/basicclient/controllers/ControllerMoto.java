package com.angular.basicclient.controllers;

import com.angular.basicclient.models.entity.Moto;
import com.angular.basicclient.services.IMotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/moto")
public class ControllerMoto {

    @Autowired
    private IMotoService iMotoService;

    @GetMapping
    public ResponseEntity<?> getMotosAll() {
        Map<String, Object> response = new HashMap<>();
        List<Moto> getMotos = null;

        try {
            getMotos = iMotoService.getMotos();
        } catch (DataAccessException e) {
            response.put("response", "Error al realizar la consulta en la bbdd");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (getMotos == null) {
            response.put("response", "No existe datos en la bbdd");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Moto>>(getMotos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMotoId(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Moto> getMoto = null;

        try {
            getMoto = iMotoService.getIdMoto(id);
        } catch (DataAccessException e) {
            response.put("response", "Error al realizar la consulta en la bbdd");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (getMoto.isEmpty()) {
            response.put("response", "El moto id ".concat(id.toString()).concat(" no existe en la bbdd"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Moto>>(getMoto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createMoto(@Valid @RequestBody Moto moto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Moto getMoto = null;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "'El campo' " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            getMoto = iMotoService.createMoto(moto);
            // DataAccessExcption
        } catch (DataAccessException e) {// permite para manejar los datos no null
            response.put("response", "Error al realizar el insert en la bbdd");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("response", "El moto ha sido creado con éxito");
        response.put("vmoto", getMoto);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMoto(@Valid @PathVariable Integer id, @RequestBody Moto moto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Optional<Moto> getMoto = null;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "'El campo' " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            getMoto = iMotoService.getIdMoto(id);
        } catch (DataAccessException e) {
            response.put("response", "Error al realizar el consulta en la bbdd");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (getMoto == null) {
            response.put("response",
                    "Error: no se pude editar, el moto id ".concat(id.toString()).concat(" no existe en la bbdd"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (getMoto.isPresent()) {
            getMoto.get().setName(moto.getName());
            getMoto.get().setImage(moto.getImage());
            getMoto.get().setDescription(moto.getDescription());
            iMotoService.updateMoto(getMoto.get());

            response.put("response", "El moto ha sido actualizado con éxito");
            response.put("vmoto", getMoto);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMoto(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            iMotoService.deleteMoto(id);
        } catch (DataAccessException e) {
            response.put("response", "Error al eliminar en la bbdd");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("response", "El moto ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
