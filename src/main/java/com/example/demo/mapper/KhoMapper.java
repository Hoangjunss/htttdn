package com.example.demo.mapper;

import com.example.demo.dto.KhoDTO;
import com.example.demo.dto.TonKhoDTO;
import com.example.demo.entities.Kho;
import com.example.demo.entities.TonKho;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KhoMapper {

    public TonKhoDTO toToKhoDTO(TonKho tonKho){
        return TonKhoDTO.builder()
                .ma(tonKho.getMa())
                .soLuong(tonKho.getSoLuong())
                .tenSanPham(tonKho.getSanPham().getTenSanPham())
                .build();
    }

    public List<TonKhoDTO> toTonKhoDTOList(List<TonKho> tonKhos){
        return tonKhos.stream()
               .map(this::toToKhoDTO)
               .collect(java.util.stream.Collectors.toList());
    }

    public KhoDTO toKhoDTO(Kho kho, List<TonKhoDTO> tonKhoDTOList){
        return KhoDTO.builder()
               .ma(kho.getMa())
               .tenKho(kho.getTenKho())
                .diaChi(kho.getDiaChi())
                .tonKhoDTOS(tonKhoDTOList)
                .maCuaHang(kho.getCuaHang().getMa())
               .build();
    }
}
