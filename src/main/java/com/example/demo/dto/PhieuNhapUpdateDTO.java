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
public class PhieuNhapUpdateDTO {
    private Integer ma;
    private Double tongGiaNhap;
    private Integer maNhaCungCap;
    private Integer maNhanVien;
}
