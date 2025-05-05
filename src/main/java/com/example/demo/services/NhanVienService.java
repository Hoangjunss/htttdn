package com.example.demo.services;

import com.example.demo.dto.CuaHangDTO;
import com.example.demo.dto.NhanVienCreateDTO;
import com.example.demo.dto.NhanVienDTO;
import com.example.demo.dto.NhanVienUpdateDTO;
import com.example.demo.entities.NhanVien;
import com.example.demo.repositories.CuaHangRepository;
import com.example.demo.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service

public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;
    private final CuaHangRepository cuaHangRepository;

    @Autowired
    public NhanVienService(NhanVienRepository nhanVienRepository, CuaHangRepository cuaHangRepository) {
        this.nhanVienRepository = nhanVienRepository;
        this.cuaHangRepository = cuaHangRepository;
    }

    public Boolean isEnabledNhanVien(Integer id) {
        return nhanVienRepository.findById(id).isPresent();
    }

    public NhanVien getById(Integer id) {
        return nhanVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));
    }

    public NhanVienDTO getNhanVienDTOById(Integer id) {
        NhanVien nhanVien = getById(id);
        return mapToDTO(nhanVien);
    }

    public NhanVienDTO createNhanVien(NhanVienCreateDTO dto) {
        NhanVien nhanVien = new NhanVien();

        mapFromCreateDTO(nhanVien, dto);

        nhanVienRepository.save(nhanVien);
        return mapToDTO(nhanVien);
    }

    public NhanVienDTO updateNhanVien(NhanVienUpdateDTO dto) {
        NhanVien nhanVien = getById(dto.getMa());

        mapFromUpdateDTO(nhanVien, dto);

        nhanVienRepository.save(nhanVien);
        return mapToDTO(nhanVien);
    }

    // =============================
    // Helpers for mapping
    // =============================

    private void mapFromCreateDTO(NhanVien nv, NhanVienCreateDTO dto) {
        nv.setMa(getGenerationId());
        nv.setHoTen(dto.getHoTen());
        nv.setNgaySinh(String.valueOf(LocalDate.parse(dto.getNgaySinh())));
        nv.setGioiTinh(dto.getGioiTinh());
        nv.setDiaChi(dto.getDiaChi());
        nv.setEmail(dto.getEmail());
        nv.setSoDienThoai(dto.getSoDienThoai());
        nv.setTiLeHoaHong(dto.getTiLeHoaHong());
        nv.setLuongTheoGio(dto.getLuongTheoGio());
        nv.setChucVu(dto.getChucVu());
        nv.setCuaHang(cuaHangRepository.findById(dto.getMaCuaHang())
                .orElseThrow(() -> new RuntimeException("Cửa hàng không tồn tại")));
    }

    private void mapFromUpdateDTO(NhanVien nv, NhanVienUpdateDTO dto) {
        nv.setHoTen(dto.getHoTen());
        nv.setNgaySinh(String.valueOf(LocalDate.parse(dto.getNgaySinh())));
        nv.setGioiTinh(dto.getGioiTinh());
        nv.setDiaChi(dto.getDiaChi());
        nv.setEmail(dto.getEmail());
        nv.setSoDienThoai(dto.getSoDienThoai());
        nv.setTiLeHoaHong(dto.getTiLeHoaHong());
        nv.setLuongTheoGio(dto.getLuongTheoGio());
        nv.setChucVu(dto.getChucVu());
        nv.setCuaHang(cuaHangRepository.findById(dto.getMaCuaHang())
                .orElseThrow(() -> new RuntimeException("Cửa hàng không tồn tại")));
    }

    private NhanVienDTO mapToDTO(NhanVien nv) {
        NhanVienDTO dto = new NhanVienDTO();
        dto.setMa(nv.getMa());
        dto.setHoTen(nv.getHoTen());
        dto.setNgaySinh(nv.getNgaySinh().toString());
        dto.setGioiTinh(nv.getGioiTinh());
        dto.setDiaChi(nv.getDiaChi());
        dto.setEmail(nv.getEmail());
        dto.setSoDienThoai(nv.getSoDienThoai());
        dto.setTiLeHoaHong(nv.getTiLeHoaHong());
        dto.setLuongTheoGio(nv.getLuongTheoGio());
        dto.setChucVu(nv.getChucVu());

        CuaHangDTO chDTO = new CuaHangDTO();
        chDTO.setMa(nv.getCuaHang().getMa());
        chDTO.setTenCuaHang(nv.getCuaHang().getTenCuaHang());
        // Bạn có thể map thêm thông tin cửa hàng tại đây

        dto.setCuaHangDTO(chDTO);
        return dto;
    }
    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        // Use most significant bits and ensure it's within the integer range
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
