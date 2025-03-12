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
public class DonHang {
    @Id
    private Integer ma;
    private LocalDateTime thoiGianBan;
    private Double tongGiaTri;
    private Double giaGiam;
    private String trangThai;
    private String phuongThuc;

    @ManyToOne
    @JoinColumn
    private NhanVien nhanVien;


}
