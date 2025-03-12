package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoanCreateDTO {
    private LocalDateTime thoiGianTao;
    private String tenDangNhap;
    private String matKhau;
    private String trangThai;
    private String quyen;
    private Integer maNhanVien;
    private Integer roleId;
}
