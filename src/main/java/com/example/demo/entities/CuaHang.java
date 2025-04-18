package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CuaHang {
    @Id
    private Integer ma;
    private String tenCuaHang;
    private String diaChi;
    private Integer maNVQuanLy;
}
