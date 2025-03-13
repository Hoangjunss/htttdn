package com.example.demo.dto;

import com.example.demo.entities.Kho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KhoDTO {
    private Integer ma;
    private String tenKho;
    private String diaChi;
    private Integer maCuaHang;
    private List<TonKhoDTO> tonKhoDTOS;

    public KhoDTO(Kho kho) {
        this.ma = kho.getMa();
        this.tenKho = kho.getTenKho();
        this.diaChi = kho.getDiaChi();
        this.maCuaHang = kho.getCuaHang().getMa();
    }
}
