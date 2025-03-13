package com.example.demo.services;

import com.example.demo.entities.DonHang;
import com.example.demo.repositories.DonHangRepository;
import com.example.demo.specification.DonHangSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DonHangService {
    private final DonHangRepository repository;

    public DonHangService(DonHangRepository repository) {
        this.repository = repository;
    }

    // Cập nhật đơn hàng
    public DonHang updateDonHang(Integer ma, DonHang newDonHang) {
        Optional<DonHang> optionalDonHang = repository.findById(ma);
        if (optionalDonHang.isPresent()) {
            DonHang existingDonHang = optionalDonHang.get();
            existingDonHang.setThoiGianBan(newDonHang.getThoiGianBan());
            existingDonHang.setTongGiaTri(newDonHang.getTongGiaTri());
            existingDonHang.setGiaGiam(newDonHang.getGiaGiam());
            existingDonHang.setTrangThai(newDonHang.getTrangThai());
            existingDonHang.setPhuongThuc(newDonHang.getPhuongThuc());
            return repository.save(existingDonHang);
        }
        return null;
    }

    // Tìm kiếm đơn hàng
    public Page<DonHang> searchDonHang(Integer maNhanVien, Integer ngay, Integer thang, Integer nam, Pageable pageable) {
        Specification<DonHang> spec = DonHangSpecification.filter(maNhanVien, ngay, thang, nam);
        return repository.findAll(spec, pageable);
    }

    // Tổng số đơn hàng
    public long countByNgay(LocalDateTime date) {
        return repository.countByNgay(date);
    }

    public long countByThang(int year, int month) {
        return repository.countByThang(year, month);
    }

    public long countByNam(int year) {
        return repository.countByNam(year);
    }

    // Tổng số tiền
    public Double sumByNgay(LocalDateTime date) {
        return repository.sumByNgay(date);
    }

    public Double sumByThang(int year, int month) {
        return repository.sumByThang(year, month);
    }

    public Double sumByNam(int year) {
        return repository.sumByNam(year);
    }
}
