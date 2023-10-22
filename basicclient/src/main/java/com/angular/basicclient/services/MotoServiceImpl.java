package com.angular.basicclient.services;

import com.angular.basicclient.models.dao.IMotoDao;
import com.angular.basicclient.models.entity.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoServiceImpl implements IMotoService {

    @Autowired
    private IMotoDao iMotoDao;

    @Override
    public List<Moto> getMotos() {
        return iMotoDao.findAll();
    }

    @Override
    public Optional<Moto> getIdMoto(Integer id) {
        return iMotoDao.findById(id);
    }

    @Override
    public Moto createMoto(Moto moto) {
        return iMotoDao.save(moto);
    }

    @Override
    public Moto updateMoto(Moto moto) {
        return iMotoDao.save(moto);
    }

    @Override
    public void deleteMoto(Integer moto) {
        iMotoDao.deleteById(moto);
    }
}
