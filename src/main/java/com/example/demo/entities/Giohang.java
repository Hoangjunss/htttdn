package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Giohang {
    @Id
    private Integer ma;
    private Double donGia;
    private Integer soLuong;
    private Double tongCong;

    @ManyToOne
    @JoinColumn
    private TaiKhoan taiKhoan;

    @ManyToOne
    @JoinColumn
    private SanPham sanPham;
}
