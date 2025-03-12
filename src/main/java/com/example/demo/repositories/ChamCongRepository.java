package com.example.demo.repositories;

import com.example.demo.entities.ChamCong;
import com.example.demo.entities.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChamCongRepository extends JpaRepository<ChamCong,Integer>, JpaSpecificationExecutor<ChamCong> {
    ChamCong findByNhanVienAndThoiGianRaIsNull(NhanVien nhanVien);
    Page<ChamCong> findAll(Specification<ChamCong> spec, Pageable pageable);
}
