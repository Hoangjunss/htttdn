package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<ChiTietDonHangDTO> chiTietDonHang;
    private Integer maNhanVien;
    private Integer maCuaHang;
}
