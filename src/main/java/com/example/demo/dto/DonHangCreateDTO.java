package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonHangCreateDTO {
    private LocalDateTime thoiGianBan;
    private Double tongGiaTri;
    private Double giaGiam;
    private String trangThai;
    private String phuongThuc;
    private Integer maSanPham;
    private Integer maNhanVien;
    private Integer maCuaHang;
}
