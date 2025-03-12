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
public class TaiKhoanDTO {
    private Integer ma;
    private LocalDateTime thoiGianTao;
    private String tenDangNhap;
    private String matKhau;
    private String trangThai;
    private String quyen;
    private NhanVienDTO nhanVienDTO;
    private RoleDTO roleDTO;
}
