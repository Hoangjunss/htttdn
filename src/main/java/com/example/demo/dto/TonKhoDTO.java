package com.example.demo.dto;

import com.example.demo.entities.TonKho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TonKhoDTO {
    private Integer ma;
    private Integer soLuong;
    private String tenSanPham;

    public TonKhoDTO(TonKho tonKho){
        this.ma = tonKho.getMa();
        this.soLuong = tonKho.getSoLuong();
        this.tenSanPham = tonKho.getSanPham().getTenSanPham();
    }
}
