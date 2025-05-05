package com.example.demo.services;

import com.example.demo.entities.ChamCong;
import com.example.demo.entities.NhanVien;
import com.example.demo.entities.TaiKhoan;
import com.example.demo.repositories.ChamCongRepository;
import com.example.demo.specification.ChamCongSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ChamCongService {
    @Autowired
    private ChamCongRepository chamCongRepository;

    // Chấm công vào
    public ChamCong checkIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaiKhoan account = (TaiKhoan) authentication.getPrincipal();
        NhanVien nhanVien =account.getNhanVien();
        ChamCong chamCong = new ChamCong();
        chamCong.setMa(getGenerationId());
        chamCong.setNhanVien(nhanVien);
        chamCong.setThoiGianVao(LocalDateTime.now());
        chamCong.setTrangThai("Đã vào");
        return chamCongRepository.save(chamCong);
    }

    // Chấm công ra
    public ChamCong checkOut() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaiKhoan account = (TaiKhoan) authentication.getPrincipal();
        NhanVien nhanVien =account.getNhanVien();
        ChamCong chamCong = chamCongRepository.findByNhanVienAndThoiGianRaIsNull(nhanVien);
        chamCong.setThoiGianRa(LocalDateTime.now());
        long hoursWorked = Duration.between(chamCong.getThoiGianVao(), chamCong.getThoiGianRa()).toHours();
        int tongGioLam = (int) hoursWorked;
        chamCong.setTongGioLam(tongGioLam);

        chamCong.setTrangThai("Hoàn thành");
        return chamCongRepository.save(chamCong);
    }
    public Page<ChamCong> timChamCong(Integer nhanVienId, Integer cuaHangId, LocalDate ngay, int page, int size) {
        Specification<ChamCong> spec = ChamCongSpecification.filterChamCong(nhanVienId, cuaHangId, ngay);
        Pageable pageable = PageRequest.of(page, size); // Phân trang
        return chamCongRepository.findAll(spec, pageable);
    }
    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        // Use most significant bits and ensure it's within the integer range
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
