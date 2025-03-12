package com.example.demo.dto;

import com.example.demo.entities.ChamCong;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ChamCongDTO {
    private Integer ma;
    private LocalDateTime thoiGianVao;
    private LocalDateTime thoiGianRa;
    private Integer tongGioLam;
    private String trangThai;
    private String tenNhanVien;
    private String tenCuaHang;

    public ChamCongDTO(ChamCong chamCong) {
        this.ma = chamCong.getMa();
        this.thoiGianVao = chamCong.getThoiGianVao();
        this.thoiGianRa = chamCong.getThoiGianRa();
        this.tongGioLam = chamCong.getTongGioLam();
        this.trangThai = chamCong.getTrangThai();
        this.tenNhanVien = chamCong.getNhanVien().getHoTen();
        this.tenCuaHang = chamCong.getNhanVien().getCuaHang().getTenCuaHang();
    }

}
