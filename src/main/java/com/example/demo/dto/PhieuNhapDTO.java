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
public class PhieuNhapDTO {
    private Integer ma;
    private LocalDateTime thoiGianNhap;
    private Double tongGiaNhap;
    private String tenNhaCungCap;
    private String tenNhanVien;
    private List<ChiTietPhieuNhapDTO> chiTietPhieuNhaps;
}
