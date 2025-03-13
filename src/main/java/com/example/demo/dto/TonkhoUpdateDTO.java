package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TonkhoUpdateDTO {
    private Integer ma;
    private Integer soLuong;
    private Integer maKho;
    private Integer maSanPham;
}
