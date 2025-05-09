package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhieuNhapCreateDTO {
    private Double tongGiaNhap;
    private Integer maNhaCungCap;
    private Integer maNhanVien;
    private List<ChiTietPhieuNhapCreateDTO> chiTietPhieuNhapCreateDTOS;
}
