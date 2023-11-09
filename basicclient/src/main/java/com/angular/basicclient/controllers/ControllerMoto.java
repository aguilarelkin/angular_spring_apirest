package com.angular.basicclient.controllers;

import com.angular.basicclient.models.entity.Moto;
import com.angular.basicclient.services.IMotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1/moto")
public class ControllerMoto {

    @Autowired
    private IMotoService iMotoService;

    @GetMapping
    public ResponseEntity<?> getMotosAll() {

        return new ResponseEntity<>(iMotoService.getMotos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMotoId(@PathVariable Integer id) {

        return new ResponseEntity<>(iMotoService.getIdMoto(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createMoto(@RequestBody Moto moto) {
        return new ResponseEntity<>(iMotoService.createMoto(moto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMoto(@PathVariable Integer id, @RequestBody Moto moto) {
        Optional<Moto> getMoto = iMotoService.getIdMoto(id);
        if (getMoto.isPresent()) {
            System.out.println(moto.getDescription());
            getMoto.get().setName(moto.getName());
            getMoto.get().setImage(moto.getImage());
            getMoto.get().setDescription(moto.getDescription());
        }

        return new ResponseEntity<>(iMotoService.updateMoto(getMoto.get()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMoto(@PathVariable Integer id) {
        iMotoService.deleteMoto(id);
        return new ResponseEntity<>("Delete correct", HttpStatus.OK);
    }

}
