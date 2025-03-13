package com.example.demo.mapper;

import com.example.demo.dto.ChiTietPhieuNhapDTO;
import com.example.demo.dto.PhieuNhapDTO;
import com.example.demo.entities.ChiTietPhieuNhap;
import com.example.demo.entities.PhieuNhapKho;
import com.example.demo.entities.SanPham;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhieuNhapKhoMapper {
    public ChiTietPhieuNhap toChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTietPhieuNhapDTO, PhieuNhapKho phieuNhapKho, SanPham sanPham){
        return ChiTietPhieuNhap.builder()
                .ma(chiTietPhieuNhapDTO.getMa())
                .giaNhap(chiTietPhieuNhapDTO.getGiaNhap())
                .giaBan(chiTietPhieuNhapDTO.getGiaBan())
                .soLuong(chiTietPhieuNhapDTO.getSoLuong())
                .phieuNhapKho(phieuNhapKho)
                .sanPham(sanPham)
                .build();
    }

    public ChiTietPhieuNhapDTO toChiTietPhieuNhapDTO(ChiTietPhieuNhap chiTietPhieuNhap){
        return ChiTietPhieuNhapDTO.builder()
                .ma(chiTietPhieuNhap.getMa())
                .giaNhap(chiTietPhieuNhap.getGiaNhap())
                .giaBan(chiTietPhieuNhap.getGiaBan())
                .soLuong(chiTietPhieuNhap.getSoLuong())
                .tenSanPhamDTO(chiTietPhieuNhap.getSanPham().getTenSanPham())
                .maSanPham(chiTietPhieuNhap.getSanPham().getMa())
                .build();
    }

    public List<ChiTietPhieuNhapDTO> toChiTietPhieuNhapDtoList(List<ChiTietPhieuNhap> chiTietPhieuNhaps){
        return chiTietPhieuNhaps.stream()
               .map(this::toChiTietPhieuNhapDTO)
               .collect(Collectors.toList());
    }

    public PhieuNhapDTO toPhieuNhapDTO(PhieuNhapKho phieuNhapKho, List<ChiTietPhieuNhap> chiTietPhieuNhap) {
        return PhieuNhapDTO.builder()
                .ma(phieuNhapKho.getMa())
                .tongGiaNhap(phieuNhapKho.getTongGiaNhap())
                .thoiGianNhap(phieuNhapKho.getThoiGianNhap())
                .tenNhanVien(phieuNhapKho.getNhanVien().getHoTen())
                .tenNhaCungCap(phieuNhapKho.getNhaCungCap().getTenNhaCungCap())
                .chiTietPhieuNhaps(toChiTietPhieuNhapDtoList(chiTietPhieuNhap))
                .build();
    }

}
