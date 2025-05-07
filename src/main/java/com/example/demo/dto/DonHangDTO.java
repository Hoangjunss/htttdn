package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class DonHangDTO {
        private Integer maDonHang;
        private LocalDateTime thoiGianBan;
        private Double tongGiaTri;
        private Double giaGiam;
        private String trangThai;
        private String phuongThuc;
        private List<ChiTietDonHangDTO> chiTietDonHang;
        private String tenNhanVIen;
        private String tenCuaHang;
    }


