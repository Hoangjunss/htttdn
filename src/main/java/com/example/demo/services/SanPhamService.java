package com.example.demo.services;

import com.example.demo.dto.SanPhamDTO;
import com.example.demo.entities.SanPham;
import com.example.demo.repositories.LoaiSanPhamRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.repositories.TonKhoRepository;
import com.example.demo.specification.SanPhamSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SanPhamService {
    private final SanPhamRepository sanPhamRepository;
    private final LoaiSanPhamRepository loaiSanPhamRepository;
    private final SizeRepository sizeRepository;
    private final TonKhoRepository tonKhoRepository;

    public SanPhamService(SanPhamRepository sanPhamRepository, LoaiSanPhamRepository loaiSanPhamRepository,
                          SizeRepository sizeRepository, TonKhoRepository tonKhoRepository) {
        this.sanPhamRepository = sanPhamRepository;
        this.loaiSanPhamRepository = loaiSanPhamRepository;
        this.sizeRepository = sizeRepository;
        this.tonKhoRepository = tonKhoRepository;
    }

    public Page<SanPham> timSanPham(Integer tonKhoId, Integer cuaHangId, Integer loaiSanPhamId, Integer sizeId, int page, int size) {
        Specification<SanPham> spec = SanPhamSpecification.filterSanPham(tonKhoId, cuaHangId, loaiSanPhamId, sizeId);
        Pageable pageable = PageRequest.of(page, size);
        return sanPhamRepository.findAll(spec, pageable);
    }

    @Transactional
    public SanPham taoSanPham(SanPhamDTO dto) {
        SanPham sanPham = new SanPham();
        sanPham.setMa(getGenerationId());
        sanPham.setTenSanPham(dto.getTenSanPham());
        sanPham.setHinhSanPham(dto.getHinhSanPham());
        sanPham.setGioiTinh(dto.getGioiTinh());
        sanPham.setMoTa(dto.getMoTa());
        sanPham.setTrangThai(dto.getTrangThai());
        sanPham.setNgayTao(LocalDateTime.now());

        sanPham.setLoaiSanPham(loaiSanPhamRepository.findById(dto.getLoaiSanPhamId())
                .orElseThrow(() -> new RuntimeException("Loại sản phẩm không tồn tại")));
        sanPham.setSize(sizeRepository.findById(dto.getSizeId())
                .orElseThrow(() -> new RuntimeException("Size không tồn tại")));
        //sanPham.setTonKho(tonKhoRepository.findById(dto.getTonKhoId())
                //.orElseThrow(() -> new RuntimeException("Tồn kho không tồn tại")));

        return sanPhamRepository.save(sanPham);
    }

    @Transactional
    public SanPham capNhatSanPham(Integer ma, SanPhamDTO dto) {
        SanPham sanPham = sanPhamRepository.findById(ma)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        sanPham.setTenSanPham(dto.getTenSanPham());
        sanPham.setHinhSanPham(dto.getHinhSanPham());
        sanPham.setGioiTinh(dto.getGioiTinh());
        sanPham.setMoTa(dto.getMoTa());
        sanPham.setTrangThai(dto.getTrangThai());

        sanPham.setLoaiSanPham(loaiSanPhamRepository.findById(dto.getLoaiSanPhamId())
                .orElseThrow(() -> new RuntimeException("Loại sản phẩm không tồn tại")));
        sanPham.setSize(sizeRepository.findById(dto.getSizeId())
                .orElseThrow(() -> new RuntimeException("Size không tồn tại")));
        //sanPham.setTonKho(tonKhoRepository.findById(dto.getTonKhoId())
                //.orElseThrow(() -> new RuntimeException("Tồn kho không tồn tại")));

        return sanPhamRepository.save(sanPham);
    }

    @Transactional
    public void xoaSanPham(Integer ma) {
        if (!sanPhamRepository.existsById(ma)) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        }
        sanPhamRepository.deleteById(ma);
    }
    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        // Use most significant bits and ensure it's within the integer range
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
