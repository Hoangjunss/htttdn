package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SanPham {
    @Id
    private Integer ma;
    private String tenSanPham;
    private String hinhSanPham;
    private String gioiTinh;
    private String moTa;
    private String trangThai;
    private LocalDateTime ngayTao;
    private Double gia;

    @ManyToOne
    @JoinColumn
    private LoaiSanPham loaiSanPham;

    @ManyToOne
    @JoinColumn
    private Size size;

    //@ManyToOne
    //@JoinColumn
    //private TonKho tonKho;
}
