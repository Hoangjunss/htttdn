package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class    PhieuNhapKho {
    @Id
    private Integer ma;
    private LocalDateTime thoiGianNhap;
    private Double tongGiaNhap;

    @ManyToOne
    @JoinColumn
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JoinColumn
    private NhanVien nhanVien;
}
