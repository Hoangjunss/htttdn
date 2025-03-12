package com.example.demo.dto;

import com.example.demo.entities.BangLuong;
import lombok.Data;

@Data
public class BangLuongDTO {
    private Integer ma;
    private Integer thangTinhLuong;
    private Integer namTinhluong;
    private Double luongCoBan;
    private Double thucNhan;
    private Integer tongHoaHong;
    private Integer tongGioLam;
    private Integer quyTinhLuong;
    private Double khauTru;
    private Integer nhanVienId;
    private Integer cuaHangId;

    public BangLuongDTO(BangLuong bangLuong) {
        this.ma = bangLuong.getMa();
        this.thangTinhLuong = bangLuong.getThangTinhLuong();
        this.namTinhluong = bangLuong.getNamTinhluong();
        this.luongCoBan = bangLuong.getLuongCoBan();
        this.thucNhan = bangLuong.getThucNhan();
        this.tongHoaHong = bangLuong.getTongHoaHong();
        this.tongGioLam = bangLuong.getTongGioLam();
        this.quyTinhLuong = bangLuong.getQuyTinhLuong();
        this.khauTru = bangLuong.getKhauTru();
        this.nhanVienId = bangLuong.getNhanVien().getMa();
        this.cuaHangId = bangLuong.getNhanVien().getCuaHang().getMa();
    }
}
