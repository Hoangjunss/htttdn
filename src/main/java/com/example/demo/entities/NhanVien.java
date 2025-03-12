package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NhanVien {
    @Id
    private Integer ma;
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String email;
    private String soDienThoai;
    private Float tiLeHoaHong;
    private Double luongTheoGio;
    private String chucVu;

    @ManyToOne
    @JoinColumn
    private CuaHang cuaHang;
}
