package com.example.demo.services;

import com.example.demo.dto.SanPhamDTO;
import com.example.demo.entities.SanPham;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.Error;
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
    private final ImageService imageService;

    public SanPhamService(SanPhamRepository sanPhamRepository, LoaiSanPhamRepository loaiSanPhamRepository,
                          SizeRepository sizeRepository, TonKhoRepository tonKhoRepository, ImageService imageService) {
        this.sanPhamRepository = sanPhamRepository;
        this.loaiSanPhamRepository = loaiSanPhamRepository;
        this.sizeRepository = sizeRepository;
        this.tonKhoRepository = tonKhoRepository;
        this.imageService = imageService;
    }

    public Page<SanPham> timSanPham(Integer tonKhoId, Integer cuaHangId, Integer loaiSanPhamId, Integer sizeId, int page, int size) {
        try {
            Specification<SanPham> spec = SanPhamSpecification.filterSanPham(tonKhoId, cuaHangId, loaiSanPhamId, sizeId);
            Pageable pageable = PageRequest.of(page, size);
            return sanPhamRepository.findAll(spec, pageable);
        } catch (Exception e) {
            throw new CustomException(Error.SANPHAM_NOT_FOUND);
        }
    }

    @Transactional
    public SanPham taoSanPham(SanPhamDTO dto) {
        try {
            // Validate DTO fields
            validateSanPhamFields(dto);

            SanPham sanPham = new SanPham();
            sanPham.setMa(getGenerationId());
            sanPham.setTenSanPham(dto.getTenSanPham());

            try {
                sanPham.setHinhSanPham(imageService.saveImage(dto.getHinhSanPham()));
            } catch (Exception e) {
                throw new CustomException(Error.SANPHAM_INVALID_IMAGE);
            }

            sanPham.setGioiTinh(dto.getGioiTinh());
            sanPham.setMoTa(dto.getMoTa());
            sanPham.setTrangThai(dto.getTrangThai());
            sanPham.setNgayTao(LocalDateTime.now());

            sanPham.setLoaiSanPham(loaiSanPhamRepository.findById(dto.getLoaiSanPhamId())
                    .orElseThrow(() -> new CustomException(Error.LOAISANPHAM_NOT_FOUND)));

            sanPham.setSize(sizeRepository.findById(dto.getSizeId())
                    .orElseThrow(() -> new CustomException(Error.SIZE_NOT_FOUND)));

            // Uncomment if needed
            // sanPham.setTonKho(tonKhoRepository.findById(dto.getTonKhoId())
            //         .orElseThrow(() -> new CustomException(Error.TONKHO_NOT_FOUND)));

            return sanPhamRepository.save(sanPham);
        } catch (CustomException ce) {
            throw ce; // Re-throw if it's already our custom exception
        } catch (Exception e) {
            throw new CustomException(Error.SANPHAM_UNABLE_TO_SAVE);
        }
    }

    private Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }

    @Transactional
    public SanPham capNhatSanPham(Integer ma, SanPhamDTO dto) {
        try {
            // Validate fields
            validateSanPhamFields(dto);

            SanPham sanPham = sanPhamRepository.findById(ma)
                    .orElseThrow(() -> new CustomException(Error.SANPHAM_NOT_FOUND));

            sanPham.setTenSanPham(dto.getTenSanPham());

            // Update image if provided
            if (dto.getHinhSanPham() != null && !dto.getHinhSanPham().isEmpty()) {
                try {
                    sanPham.setHinhSanPham(imageService.saveImage(dto.getHinhSanPham()));
                } catch (Exception e) {
                    throw new CustomException(Error.SANPHAM_INVALID_IMAGE);
                }
            }

            sanPham.setGioiTinh(dto.getGioiTinh());
            sanPham.setMoTa(dto.getMoTa());
            sanPham.setTrangThai(dto.getTrangThai());

            sanPham.setLoaiSanPham(loaiSanPhamRepository.findById(dto.getLoaiSanPhamId())
                    .orElseThrow(() -> new CustomException(Error.LOAISANPHAM_NOT_FOUND)));

            sanPham.setSize(sizeRepository.findById(dto.getSizeId())
                    .orElseThrow(() -> new CustomException(Error.SIZE_NOT_FOUND)));

            // Uncomment if needed
            // sanPham.setTonKho(tonKhoRepository.findById(dto.getTonKhoId())
            //        .orElseThrow(() -> new CustomException(Error.TONKHO_NOT_FOUND)));

            return sanPhamRepository.save(sanPham);
        } catch (CustomException ce) {
            throw ce; // Re-throw if it's already our custom exception
        } catch (Exception e) {
            throw new CustomException(Error.SANPHAM_UNABLE_TO_UPDATE);
        }
    }

    @Transactional
    public void xoaSanPham(Integer ma) {
        try {
            if (!sanPhamRepository.existsById(ma)) {
                throw new CustomException(Error.SANPHAM_NOT_FOUND);
            }
            sanPhamRepository.deleteById(ma);
        } catch (CustomException ce) {
            throw ce; // Re-throw if it's already our custom exception
        } catch (Exception e) {
            throw new CustomException(Error.SANPHAM_UNABLE_TO_DELETE);
        }
    }

    // Validation methods
    private void validateSanPhamFields(SanPhamDTO dto) {
        if (dto.getTenSanPham() == null || dto.getTenSanPham().trim().isEmpty()) {
            throw new CustomException(Error.SANPHAM_INVALID_NAME);
        }

        if (dto.getMoTa() == null || dto.getMoTa().trim().isEmpty()) {
            throw new CustomException(Error.SANPHAM_INVALID_DESCRIPTION);
        }

        if (dto.getTrangThai() == null) {
            throw new CustomException(Error.SANPHAM_INVALID_STATUS);
        }

        // Validate gender if needed
        if (dto.getGioiTinh() == null || !(dto.getGioiTinh().equalsIgnoreCase("Nam") ||
                dto.getGioiTinh().equalsIgnoreCase("Nữ") ||
                dto.getGioiTinh().equalsIgnoreCase("Unisex"))) {
            throw new CustomException(Error.SANPHAM_INVALID_GENDER);
        }

        // LoaiSanPham validation
        if (dto.getLoaiSanPhamId() == null) {
            throw new CustomException(Error.LOAISANPHAM_NOT_FOUND);
        }

        // Size validation
        if (dto.getSizeId() == null) {
            throw new CustomException(Error.SIZE_NOT_FOUND);
        }
    }
}