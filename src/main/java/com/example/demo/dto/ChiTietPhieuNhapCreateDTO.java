package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietPhieuNhapCreateDTO {
    private Double giaNhap;
    private Double giaBan;
    private Integer soLuong;
    private Integer maSanPham;
}
