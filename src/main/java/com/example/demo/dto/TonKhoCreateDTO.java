package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TonKhoCreateDTO {
    private Integer soLuong;
    private Integer maKho;
    private Integer maSanPham;
}
