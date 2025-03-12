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
public class ChiTietPhieuNhap {
    @Id
    private Integer ma;
    private Double giaNhap;
    private Double giaBan;
    private Integer soLuong;

    @ManyToOne
    @JoinColumn
    private PhieuNhapKho phieuNhapKho;

    @ManyToOne
    @JoinColumn
    private SanPham sanPham;
}
