package com.angular.basicclient.services;

import com.angular.basicclient.models.entity.Moto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IMotoService {

    List<Moto> getMotos();

    Optional<Moto> getIdMoto(Integer id);

    Moto createMoto(Moto moto);

    Moto updateMoto(Moto moto);

    void deleteMoto(Integer moto);


}
