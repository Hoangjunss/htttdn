package com.example.demo.mapper;

import com.example.demo.dto.NhanVienDTO;
import com.example.demo.entities.NhanVien;
import org.springframework.stereotype.Component;

@Component
public class NhanVienMapper {

    public NhanVien toEntity(NhanVienDTO nhanVienDTO) {
        return NhanVien.builder()
                .ma(nhanVienDTO.getMa())
                .hoTen(nhanVienDTO.getHoTen())
                .chucVu(nhanVienDTO.getChucVu())
                .email(nhanVienDTO.getEmail())
                .diaChi(nhanVienDTO.getDiaChi())
                .gioiTinh(nhanVienDTO.getGioiTinh())
                .luongTheoGio(nhanVienDTO.getLuongTheoGio())
                .tiLeHoaHong(nhanVienDTO.getTiLeHoaHong())
                .chucVu(nhanVienDTO.getChucVu())
                .soDienThoai(nhanVienDTO.getSoDienThoai())
                .ngaySinh(nhanVienDTO.getNgaySinh())
                .build();
    }

    public NhanVienDTO toDTO(NhanVien nhanVien) {
        return NhanVienDTO.builder()
                .ma(nhanVien.getMa())
                .hoTen(nhanVien.getHoTen())
                .chucVu(nhanVien.getChucVu())
                .email(nhanVien.getEmail())
                .diaChi(nhanVien.getDiaChi())
                .gioiTinh(nhanVien.getGioiTinh())
                .luongTheoGio(nhanVien.getLuongTheoGio())
                .tiLeHoaHong(nhanVien.getTiLeHoaHong())
                .chucVu(nhanVien.getChucVu())
                .soDienThoai(nhanVien.getSoDienThoai())
                .ngaySinh(nhanVien.getNgaySinh())
                .build();
    }
}
