package com.example.demo.services;

import com.example.demo.dto.ChiTietDonHangDTO;
import com.example.demo.dto.DonHangCreateDTO;
import com.example.demo.dto.DonHangDTO;
import com.example.demo.entities.*;
import com.example.demo.repositories.*;
import com.example.demo.specification.DonHangSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonHangService {
    private final DonHangRepository repository;
    private final NhanVienRepository nhanVienRepository;
    private final SanPhamRepository sanPhamRepository;
    private final CuaHangRepository cuaHangRepository;
    private final ChiTietDonHangRepository chiTietDonHangRepository;
    private final BangLuongRepository bangLuongRepository;

    @Autowired
    public DonHangService(DonHangRepository repository,
                          NhanVienRepository nhanVienRepository,
                          SanPhamRepository sanPhamRepository,
                          CuaHangRepository cuaHangRepository, ChiTietDonHangRepository chiTietDonHangRepository, BangLuongRepository bangLuongRepository) {
        this.repository = repository;
        this.nhanVienRepository = nhanVienRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.cuaHangRepository = cuaHangRepository;
        this.chiTietDonHangRepository = chiTietDonHangRepository;
        this.bangLuongRepository = bangLuongRepository;
    }

    @Transactional
    public DonHangDTO save(DonHangCreateDTO dto) {
        NhanVien nhanVien = nhanVienRepository.findById(dto.getMaNhanVien())
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));
        CuaHang cuaHang = cuaHangRepository.findById(dto.getMaCuaHang())
                .orElseThrow(() -> new RuntimeException("Cửa hàng không tồn tại"));

        DonHang donHang = DonHang.builder()
                .ma(getGenerationId())
                .thoiGianBan(LocalDateTime.now())
                .giaGiam(dto.getGiaGiam())
                .trangThai(dto.getTrangThai())
                .phuongThuc(dto.getPhuongThuc())
                .cuaHang(cuaHang)
                .nhanVien(nhanVien)
                .build();

        double tongGiaTri = 0.0;

        DonHang savedDonHang = repository.save(donHang);

        List<ChiTietDonHangDTO> chiTietDonHangDTOList = new ArrayList<>();

        for (ChiTietDonHangDTO ctDTO : dto.getChiTietDonHang()) {
            SanPham sanPham = sanPhamRepository.findById(ctDTO.getMaSanPham())
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

            ChiTietDonHang chiTiet = new ChiTietDonHang();
            chiTiet.setMa(getGenerationId());
            chiTiet.setDonGia(sanPham.getGia());
            chiTiet.setSoLuong(ctDTO.getSoluong());
            chiTiet.setSanPham(sanPham);
            chiTiet.setDonHang(savedDonHang);

            tongGiaTri += chiTiet.getDonGia() * chiTiet.getSoLuong();  // Cập nhật tổng giá trị

            chiTietDonHangRepository.save(chiTiet);

            // Chuyển đổi chi tiết đơn hàng thành ChiTietDonHangDTO và thêm vào danh sách
            ChiTietDonHangDTO chiTietDonHangDTO = new ChiTietDonHangDTO();
            chiTietDonHangDTO.setMaSanPham(sanPham.getMa());
            chiTietDonHangDTO.setSoluong(ctDTO.getSoluong());
            chiTietDonHangDTO.setTongTien(chiTiet.getDonGia() * chiTiet.getSoLuong());

            chiTietDonHangDTOList.add(chiTietDonHangDTO);
        }


        savedDonHang.setTongGiaTri(tongGiaTri);
        DonHang donHang1= repository.save(savedDonHang); // cập nhật lại tổng giá trị
        BangLuong bangLuong = bangLuongRepository.findByNhanVienAndNamTinhluongAndThangTinhLuong(
                nhanVien, LocalDate.now().getYear(), LocalDate.now().getMonthValue());
        double hoaHong = 0.0;
        if (bangLuong != null) {
            hoaHong += donHang1.getTongGiaTri() * (nhanVien.getTiLeHoaHong() / 100.0);
          bangLuong.setTongHoaHong(bangLuong.getTongHoaHong()+hoaHong);
          bangLuong.setThucNhan(bangLuong.getThucNhan() + hoaHong);
            bangLuongRepository.save(bangLuong);
        } else {
            BangLuong bangLuongMoi = new BangLuong();
            bangLuongMoi.setMa(getGenerationId());
            bangLuongMoi.setNhanVien(nhanVien);
            bangLuongMoi.setThangTinhLuong(LocalDate.now().getMonthValue());
            bangLuongMoi.setNamTinhluong(LocalDate.now().getYear());
            bangLuongMoi.setTongGioLam(0);

            bangLuongMoi.setLuongCoBan(nhanVien.getLuongTheoGio()); // Sẽ được cập nhật sau
            bangLuongMoi.setThucNhan(hoaHong);
            bangLuongMoi.setTongHoaHong(hoaHong);
            bangLuongMoi.setQuyTinhLuong(LocalDate.now().getMonthValue()); // Mặc định là tháng hiện tại
            bangLuongMoi.setKhauTru(0.0);

            bangLuongRepository.save(bangLuongMoi);
        }
        DonHangDTO donHangDTO=new DonHangDTO();
        donHangDTO.setMaDonHang(donHang1.getMa());
        donHangDTO.setTenCuaHang(donHang1.getCuaHang().getTenCuaHang());
        donHangDTO.setChiTietDonHang(chiTietDonHangDTOList);
        donHangDTO.setTongGiaTri(donHang1.getTongGiaTri());
        donHangDTO.setTenNhanVIen(donHang1.getNhanVien().getHoTen());
        donHangDTO.setThoiGianBan(donHang1.getThoiGianBan());
        donHangDTO.setPhuongThuc(donHang1.getPhuongThuc());
        donHangDTO.setGiaGiam(donHang1.getGiaGiam());
        return donHangDTO;
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
