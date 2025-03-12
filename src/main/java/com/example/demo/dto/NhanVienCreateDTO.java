package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienCreateDTO {
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String email;
    private String soDienThoai;
    private Float tiLeHoaHong;
    private Double luongTheoGio;
    private String chucVu;
    private Integer maCuaHang;
}
