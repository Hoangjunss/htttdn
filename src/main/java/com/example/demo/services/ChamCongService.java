package com.example.demo.services;

import com.example.demo.entities.BangLuong;
import com.example.demo.entities.ChamCong;
import com.example.demo.entities.NhanVien;
import com.example.demo.entities.TaiKhoan;
import com.example.demo.repositories.BangLuongRepository;
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
    @Autowired
    private BangLuongRepository bangLuongRepository;

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

    public ChamCong checkOut() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaiKhoan account = (TaiKhoan) authentication.getPrincipal();
        NhanVien nhanVien = account.getNhanVien();
        ChamCong chamCong = chamCongRepository.findByNhanVienAndThoiGianRaIsNull(nhanVien);
        chamCong.setThoiGianRa(LocalDateTime.now());
        long hoursWorked = Duration.between(chamCong.getThoiGianVao(), chamCong.getThoiGianRa()).toHours();
        int tongGioLam = (int) hoursWorked;
        chamCong.setTongGioLam(tongGioLam);

        chamCong.setTrangThai("Hoàn thành");

        // Lấy tháng và năm hiện tại để tìm hoặc tạo bảng lương
        LocalDateTime now = LocalDateTime.now();
        Integer thangHienTai = now.getMonthValue();
        Integer namHienTai = now.getYear();

        // Tìm bảng lương của nhân viên trong tháng và năm hiện tại
        BangLuong bangLuong = bangLuongRepository.findByNhanVienAndNamTinhluongAndThangTinhLuong(
                nhanVien, namHienTai, thangHienTai);

        if (bangLuong != null) {
            Integer tongGioLamCu = bangLuong.getTongGioLam() != null ? bangLuong.getTongGioLam() : 0;
            bangLuong.setTongGioLam(tongGioLamCu + tongGioLam);
            bangLuong.setThucNhan(nhanVien.getLuongTheoGio()*(tongGioLamCu+tongGioLam) + bangLuong.getTongHoaHong());
            bangLuongRepository.save(bangLuong);
        } else {
            BangLuong bangLuongMoi = new BangLuong();
            bangLuongMoi.setMa(getGenerationId());
            bangLuongMoi.setNhanVien(nhanVien);
            bangLuongMoi.setThangTinhLuong(thangHienTai);
            bangLuongMoi.setNamTinhluong(namHienTai);
            bangLuongMoi.setTongGioLam(tongGioLam);

            bangLuongMoi.setLuongCoBan(nhanVien.getLuongTheoGio()); // Sẽ được cập nhật sau
            bangLuongMoi.setThucNhan(nhanVien.getLuongTheoGio()*tongGioLam);
            bangLuongMoi.setTongHoaHong(0);
            bangLuongMoi.setQuyTinhLuong(thangHienTai); // Mặc định là tháng hiện tại
            bangLuongMoi.setKhauTru(0.0);

            bangLuongRepository.save(bangLuongMoi);
        }

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
