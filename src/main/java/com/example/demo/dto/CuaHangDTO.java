package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuaHangDTO {
    private Integer ma;
    private String tenCuaHang;
    private String diaChi;
    private Integer maNVQuanLy;
}
