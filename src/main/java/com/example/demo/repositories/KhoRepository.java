package com.example.demo.repositories;

import com.example.demo.entities.Kho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoRepository extends JpaRepository<Kho, Integer> {
    @Query(value = "SELECT nv.cuaHang.ma FROM PhieuNhapKho phk INNER JOIN NhanVien nv ON phk.nhanVien.ma = nv.ma WHERE phk.ma = :maPhieuNhap")
    Integer findCuaHangByPhieuNhap(@Param("maPhieuNhap") Integer maPhieuNhap);

    @Query(value = "SELECT k FROM Kho k WHERE k.cuaHang.ma = :ma")
    Kho findKhoByMaCuaHang(@Param("ma") Integer ma);
}
