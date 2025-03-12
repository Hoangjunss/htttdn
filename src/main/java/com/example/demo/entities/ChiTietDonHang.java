package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChiTietDonHang {
    @Id
    private Integer ma;
    private Double donGia;
    private Integer soLuong;

    @ManyToOne
    @JoinColumn
    private DonHang donHang;

    @ManyToOne
    @JoinColumn
    private SanPham sanPham;
}
