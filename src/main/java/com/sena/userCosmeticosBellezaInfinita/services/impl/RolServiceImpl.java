package com.sena.userCosmeticosBellezaInfinita.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sena.userCosmeticosBellezaInfinita.dto.RolSelectDTO;
import com.sena.userCosmeticosBellezaInfinita.entity.Rol;
import com.sena.userCosmeticosBellezaInfinita.mapper.RolMapper;
import com.sena.userCosmeticosBellezaInfinita.repository.RolRepository;
import com.sena.userCosmeticosBellezaInfinita.services.RolService;

@Repository
public class RolServiceImpl implements RolService{


    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private RolMapper rolMapper;

    public List<RolSelectDTO> obtenerSelectRol(){
        List<Rol> roles = rolRepository.findAll();
        List<RolSelectDTO> rolSelectDTOs = rolMapper.listRolToListRolSelectDTO(roles);
        return rolSelectDTOs;
    }

}
