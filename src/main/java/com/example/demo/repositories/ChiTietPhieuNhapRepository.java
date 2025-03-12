package com.example.demo.repositories;

import com.example.demo.entities.ChiTietPhieuNhap;
import com.example.demo.entities.PhieuNhapKho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietPhieuNhapRepository extends JpaRepository<ChiTietPhieuNhap, Integer> {
    List<ChiTietPhieuNhap> findAllByPhieuNhapKho(PhieuNhapKho phieuNhapKho);
    void deleteAllByPhieuNhapKho(PhieuNhapKho phieuNhapKho);
}
