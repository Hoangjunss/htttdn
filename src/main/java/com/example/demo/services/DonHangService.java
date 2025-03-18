package com.example.demo.services;

import com.example.demo.dto.DonHangCreateDTO;
import com.example.demo.entities.CuaHang;
import com.example.demo.entities.DonHang;
import com.example.demo.entities.NhanVien;
import com.example.demo.entities.SanPham;
import com.example.demo.repositories.CuaHangRepository;
import com.example.demo.repositories.DonHangRepository;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.specification.DonHangSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonHangService {
    private final DonHangRepository repository;
    private final NhanVienRepository nhanVienRepository;
    private final SanPhamRepository sanPhamRepository;
    private final CuaHangRepository cuaHangRepository;

    @Autowired
    public DonHangService(DonHangRepository repository,
                          NhanVienRepository nhanVienRepository,
                          SanPhamRepository sanPhamRepository,
                          CuaHangRepository cuaHangRepository) {
        this.repository = repository;
        this.nhanVienRepository = nhanVienRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.cuaHangRepository = cuaHangRepository;
    }

    public DonHang save(DonHangCreateDTO donHangCreateDTO){
        NhanVien nhanVien = nhanVienRepository.findById(donHangCreateDTO.getMaNhanVien())
                .orElseThrow(()-> new RuntimeException("Nhan vien not found"));

        CuaHang cuaHang = cuaHangRepository.findById(donHangCreateDTO.getMaCuaHang())
                .orElseThrow(()-> new RuntimeException("Cua hang not found"));

        SanPham sanPham = sanPhamRepository.findById(donHangCreateDTO.getMaSanPham())
                .orElseThrow(()-> new RuntimeException("San pham not found"));

        DonHang donHang = new DonHang();
        donHang.setMa(getGenerationId());
        donHang.setThoiGianBan(LocalDateTime.now());
        donHang.setTongGiaTri(donHangCreateDTO.getTongGiaTri());
        donHang.setGiaGiam(donHangCreateDTO.getGiaGiam());
        donHang.setTrangThai(donHangCreateDTO.getTrangThai());
        donHang.setPhuongThuc(donHangCreateDTO.getPhuongThuc());
        donHang.setCuaHang(cuaHang);
        donHang.setNhanVien(nhanVien);
        donHang.setSanPham(sanPham);
        return repository.save(donHang);
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

    private Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
