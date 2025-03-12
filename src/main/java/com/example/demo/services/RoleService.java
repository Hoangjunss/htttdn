package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    private RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Boolean isEnabledRole(Integer roleId){
        return roleRepository.findById(roleId).isPresent();
    }

    public Role findRoleById(Integer roleId){
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
