package com.example.demo.repositories;

import com.example.demo.entities.PhieuNhapKho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhieuNhapKhoRepository extends JpaRepository<PhieuNhapKho, Integer>, JpaSpecificationExecutor<PhieuNhapKho> {
}
