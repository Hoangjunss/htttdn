package com.example.demo.services;

import com.example.demo.dto.BangLuongDTO;
import com.example.demo.entities.BangLuong;
import com.example.demo.entities.NhanVien;
import com.example.demo.entities.TaiKhoan;
import com.example.demo.repositories.BangLuongRepository;
import com.example.demo.specification.BangLuongSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BangLuongService {
    private final BangLuongRepository bangLuongRepository;

    public BangLuongService(BangLuongRepository bangLuongRepository) {
        this.bangLuongRepository = bangLuongRepository;
    }

    public Page<BangLuong> timBangLuong(Integer nhanVienId, Integer cuaHangId, Integer thang, Integer nam, int page, int size) {
        Specification<BangLuong> spec = BangLuongSpecification.filter(nhanVienId, cuaHangId, thang, nam);
        Pageable pageable = PageRequest.of(page, size);
        return bangLuongRepository.findAll(spec, pageable);
    }
    @Transactional
    public BangLuong taoBangLuong(BangLuongDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaiKhoan account = (TaiKhoan) authentication.getPrincipal();
        NhanVien nhanVien =account.getNhanVien();
        BangLuong bangLuong = new BangLuong();
        bangLuong.setNhanVien(nhanVien);
        bangLuong.setThangTinhLuong(dto.getThangTinhLuong());
        bangLuong.setNamTinhluong(dto.getNamTinhluong());
        bangLuong.setLuongCoBan(dto.getLuongCoBan());
        bangLuong.setTongGioLam(dto.getTongGioLam());
        bangLuong.setTongHoaHong(dto.getTongHoaHong());
        bangLuong.setQuyTinhLuong(dto.getQuyTinhLuong());
        bangLuong.setKhauTru(dto.getKhauTru());
        bangLuong.setThucNhan(tinhThucNhan(dto));

        return bangLuongRepository.save(bangLuong);
    }

    @Transactional
    public BangLuong capNhatBangLuong(Integer ma, BangLuongDTO dto) {
        BangLuong bangLuong = bangLuongRepository.findById(ma)
                .orElseThrow(() -> new RuntimeException("Bảng lương không tồn tại"));

        bangLuong.setLuongCoBan(dto.getLuongCoBan());
        bangLuong.setTongGioLam(dto.getTongGioLam());
        bangLuong.setTongHoaHong(dto.getTongHoaHong());
        bangLuong.setQuyTinhLuong(dto.getQuyTinhLuong());
        bangLuong.setKhauTru(dto.getKhauTru());
        bangLuong.setThucNhan(tinhThucNhan(dto));

        return bangLuongRepository.save(bangLuong);
    }

    private Double tinhThucNhan(BangLuongDTO dto) {
        return dto.getLuongCoBan() + (dto.getTongHoaHong() * 1.0) - dto.getKhauTru();
    }
}
