package com.example.demo.repositories;

import com.example.demo.entities.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DonHangRepository extends JpaRepository<DonHang, Integer>, JpaSpecificationExecutor<DonHang> {

    // Tính tổng số đơn hàng theo ngày, tháng, năm
    @Query("SELECT COUNT(d) FROM DonHang d WHERE DATE(d.thoiGianBan) = :date")
    long countByNgay(@Param("date") LocalDateTime date);

    @Query("SELECT COUNT(d) FROM DonHang d WHERE YEAR(d.thoiGianBan) = :year AND MONTH(d.thoiGianBan) = :month")
    long countByThang(@Param("year") int year, @Param("month") int month);

    @Query("SELECT COUNT(d) FROM DonHang d WHERE YEAR(d.thoiGianBan) = :year")
    long countByNam(@Param("year") int year);

    // Tính tổng số tiền (tổng giá trị - giảm giá)
    @Query("SELECT SUM(d.tongGiaTri - d.giaGiam) FROM DonHang d WHERE DATE(d.thoiGianBan) = :date")
    Double sumByNgay(@Param("date") LocalDateTime date);

    @Query("SELECT SUM(d.tongGiaTri - d.giaGiam) FROM DonHang d WHERE YEAR(d.thoiGianBan) = :year AND MONTH(d.thoiGianBan) = :month")
    Double sumByThang(@Param("year") int year, @Param("month") int month);

    @Query("SELECT SUM(d.tongGiaTri - d.giaGiam) FROM DonHang d WHERE YEAR(d.thoiGianBan) = :year")
    Double sumByNam(@Param("year") int year);
}
