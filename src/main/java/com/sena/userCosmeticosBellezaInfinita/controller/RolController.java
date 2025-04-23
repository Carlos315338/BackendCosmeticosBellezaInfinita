package com.sena.userCosmeticosBellezaInfinita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.userCosmeticosBellezaInfinita.dto.ApiResponse;
import com.sena.userCosmeticosBellezaInfinita.dto.RolSelectDTO;
import com.sena.userCosmeticosBellezaInfinita.services.RolService;

@RestController
@RequestMapping("api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("obtenerSelectRol")
    public ResponseEntity<ApiResponse<List<RolSelectDTO>>> obtenerRolSelect(){
        List<RolSelectDTO> rolSelectDTOs = rolService.obtenerSelectRol();
        return ResponseEntity.ok(ApiResponse.ok("Operacion Exitosa", rolSelectDTOs));
    }
}
