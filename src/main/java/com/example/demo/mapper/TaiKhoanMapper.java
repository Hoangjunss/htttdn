package com.example.demo.mapper;

import com.example.demo.dto.RoleDTO;
import com.example.demo.dto.TaiKhoanDTO;
import com.example.demo.entities.Role;
import com.example.demo.entities.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaiKhoanMapper {
    private final NhanVienMapper nhanVienMapper;

    @Autowired
    public TaiKhoanMapper(NhanVienMapper nhanVienMapper) {
        this.nhanVienMapper = nhanVienMapper;
    }

    public TaiKhoan toEntity(TaiKhoanDTO taiKhoanDTO){
        return TaiKhoan.builder()
                .ma(taiKhoanDTO.getMa())
                .tenDangNhap(taiKhoanDTO.getTenDangNhap())
                .matKhau(taiKhoanDTO.getMatKhau())
                .quyen(taiKhoanDTO.getQuyen())
                .thoiGianTao(taiKhoanDTO.getThoiGianTao())
                .role(toEntity(taiKhoanDTO.getRoleDTO()))
                .nhanVien(nhanVienMapper.toEntity(taiKhoanDTO.getNhanVienDTO()))
                .build();
    }

    public TaiKhoanDTO toDTO(TaiKhoan taiKhoan){
        return TaiKhoanDTO.builder()
                .ma(taiKhoan.getMa())
                .tenDangNhap(taiKhoan.getTenDangNhap())
                .matKhau(taiKhoan.getMatKhau())
                .quyen(taiKhoan.getQuyen())
                .thoiGianTao(taiKhoan.getThoiGianTao())
                .roleDTO(toDTO(taiKhoan.getRole()))
                .nhanVienDTO(nhanVienMapper.toDTO(taiKhoan.getNhanVien()))
                .build();
    }

    public Role toEntity(RoleDTO roleDTO){
        return Role.builder()
                .ma(roleDTO.getMa())
                .chucVu(roleDTO.getChucVu())
                .build();
    }

    public RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .ma(role.getMa())
                .chucVu(role.getChucVu())
                .build();
    }

}
