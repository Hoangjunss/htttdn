package com.example.demo.dto;

import com.example.demo.entities.SanPham;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTO {
    private Integer ma;
    private String tenSanPham;
    private String hinhSanPham;
    private String gioiTinh;
    private String moTa;
    private String trangThai;
    private LocalDateTime ngayTao;
    private Integer loaiSanPhamId;
    private Integer sizeId;
    private Integer tonKhoId;

    public SanPhamDTO(SanPham sanPham) {
        this.ma = sanPham.getMa();
        this.tenSanPham = sanPham.getTenSanPham();
        this.hinhSanPham = sanPham.getHinhSanPham();
        this.gioiTinh = sanPham.getGioiTinh();
        this.moTa = sanPham.getMoTa();
        this.trangThai = sanPham.getTrangThai();
        this.ngayTao = sanPham.getNgayTao();
        this.loaiSanPhamId = sanPham.getLoaiSanPham().getMa();
        this.sizeId = sanPham.getSize().getMa();
        //this.tonKhoId = sanPham.getTonKho().getMa();
    }
}
