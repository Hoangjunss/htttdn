package com.example.demo.services;

import com.example.demo.dto.NhanVienDTO;
import com.example.demo.entities.NhanVien;
import com.example.demo.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NhanVienSerivce {
    private final NhanVienRepository nhanVienRepository;

    @Autowired
    public NhanVienSerivce(NhanVienRepository nhanVienRepository) {
        this.nhanVienRepository = nhanVienRepository;
    }

    public Boolean isEnabledNhanVien(Integer id) {
        return nhanVienRepository.findById(id).isPresent();
    }

    public NhanVien getById(Integer id){
        return nhanVienRepository.findById(id).orElseThrow(() -> new RuntimeException("Nhan Vien khong ton tai"));
    }

}
