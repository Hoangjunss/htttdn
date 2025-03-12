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
public class BangLuong {
    @Id
    private Integer ma;
    private Integer thangTinhLuong;
    private Integer namTinhluong;
    private Double luongCoBan;
    private Double thucNhan;
    private Integer tongHoaHong;
    private Integer tongGioLam;
    private Integer quyTinhLuong;
    private Double khauTru;

    @ManyToOne
    @JoinColumn
    private NhanVien nhanVien;
}
