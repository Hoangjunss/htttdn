package com.example.demo.services;

import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.mapper.PhieuNhapKhoMapper;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PhieuNhapService {
    private final PhieuNhapKhoRepository phieuNhapKhoRepository;
    private final ChiTietPhieuNhapRepository chiTietPhieuNhapRepository;
    private final NhaCungCapRepository nhaCungCapRepository;
    private final NhanVienRepository nhanVienRepository;
    private final SanPhamRepository sanPhamRepository;
    private final PhieuNhapKhoMapper phieuNhapKhoMapper;

    @Autowired
    public PhieuNhapService(PhieuNhapKhoRepository phieuNhapKhoRepository,
                            ChiTietPhieuNhapRepository chiTietPhieuNhapRepository,
                            NhaCungCapRepository nhaCungCapRepository,
                            NhanVienRepository nhanVienRepository,
                            SanPhamRepository sanPhamRepository,
                            PhieuNhapKhoMapper phieuNhapKhoMapper) {
        this.chiTietPhieuNhapRepository = chiTietPhieuNhapRepository;
        this.phieuNhapKhoRepository = phieuNhapKhoRepository;
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.phieuNhapKhoMapper = phieuNhapKhoMapper;
    }

    public PhieuNhapDTO taoPhieuNhap(PhieuNhapCreateDTO phieuNhapCreateDTO){
        NhaCungCap nhaCungCap = nhaCungCapRepository.findById(phieuNhapCreateDTO.getMaNhaCungCap())
                .orElseThrow(() -> new RuntimeException("NhaCungCap not found"));
        NhanVien nhanVien = nhanVienRepository.findById(phieuNhapCreateDTO.getMaNhanVien())
                .orElseThrow(() -> new RuntimeException("NhanVien not found"));

        PhieuNhapKho phieuNhapKho = new PhieuNhapKho();
        phieuNhapKho.setMa(getGenerationId());
        phieuNhapKho.setNhanVien(nhanVien);
        phieuNhapKho.setNhaCungCap(nhaCungCap);
        phieuNhapKho.setTongGiaNhap(phieuNhapCreateDTO.getTongGiaNhap());
        phieuNhapKho.setThoiGianNhap(LocalDateTime.now());
        PhieuNhapKho phieuNhapKho1 = phieuNhapKhoRepository.save(phieuNhapKho);

        List<ChiTietPhieuNhap> chiTietPhieuNhaps = phieuNhapCreateDTO.getChiTietPhieuNhapCreateDTOS()
                .stream()
                .map(chiTietPhieuNhapCreateDTO -> {
                    ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap();
                    SanPham sanPham = sanPhamRepository.findById(chiTietPhieuNhapCreateDTO.getMaSanPham())
                            .orElseThrow(() -> new RuntimeException("San pham not found"));

                    chiTietPhieuNhap.setMa(getGenerationId());
                    chiTietPhieuNhap.setPhieuNhapKho(phieuNhapKho1);
                    chiTietPhieuNhap.setSanPham(sanPham);
                    chiTietPhieuNhap.setGiaNhap(chiTietPhieuNhapCreateDTO.getGiaNhap());
                    chiTietPhieuNhap.setGiaBan(chiTietPhieuNhapCreateDTO.getGiaBan());
                    chiTietPhieuNhap.setSoLuong(chiTietPhieuNhapCreateDTO.getSoLuong());

                    return chiTietPhieuNhap;
                })
                .collect(Collectors.toList());

        chiTietPhieuNhapRepository.saveAll(chiTietPhieuNhaps);

        return phieuNhapKhoMapper.toPhieuNhapDTO(phieuNhapKho1, chiTietPhieuNhaps);
    }

    public PhieuNhapDTO updatePhieuNhap(PhieuNhapUpdateDTO phieuNhapUpdateDTO){

        PhieuNhapKho phieuNhapKho = phieuNhapKhoRepository.findById(phieuNhapUpdateDTO.getMa())
                .orElseThrow(() -> new RuntimeException("PhieuNhap not found"));

        NhanVien nhanVien = nhanVienRepository.findById(phieuNhapUpdateDTO.getMaNhanVien())
                .orElseThrow(() -> new RuntimeException("NhanVien not found"));

        phieuNhapKho.setNhaCungCap(nhaCungCapRepository.findById(phieuNhapUpdateDTO.getMaNhaCungCap())
                .orElseThrow(() -> new RuntimeException("NhaCungCap not found")));

        phieuNhapKho.setTongGiaNhap(phieuNhapUpdateDTO.getTongGiaNhap());
        phieuNhapKho.setNhanVien(nhanVien);
        phieuNhapKho.setThoiGianNhap(LocalDateTime.now());
        PhieuNhapKho phieuNhapKho1 = phieuNhapKhoRepository.save(phieuNhapKho);

        return PhieuNhapDTO.builder()
                .ma(phieuNhapKho1.getMa())
                .tongGiaNhap(phieuNhapKho1.getTongGiaNhap())
                .thoiGianNhap(phieuNhapKho1.getThoiGianNhap())
                .tenNhanVien(phieuNhapKho1.getNhanVien().getHoTen())
                .tenNhaCungCap(phieuNhapKho1.getNhaCungCap().getTenNhaCungCap())
                .chiTietPhieuNhaps(null)
                .build();
    }

    public PhieuNhapDTO updateChiTietPhieuNhap(List<ChiTietPhieuNhapUpdateDTO> chiTietPhieuNhapUpdateDTOS){
        Double tongTien = 0D;
        List<ChiTietPhieuNhapDTO> chiTietPhieuNhapDTOS = chiTietPhieuNhapUpdateDTOS
                .stream()
                .map(chiTietPhieuNhapUpdateDTO -> {
                    ChiTietPhieuNhap chiTietPhieuNhap = chiTietPhieuNhapRepository.findById(chiTietPhieuNhapUpdateDTO.getMa())
                            .orElseThrow(() -> new RuntimeException("ChiTietPhieuNhap not found"));

                     SanPham sanPham = sanPhamRepository.findById(chiTietPhieuNhapUpdateDTO.getMaSanPham())
                            .orElseThrow(() -> new RuntimeException("San pham not found"));

                     PhieuNhapKho phieuNhapKho = phieuNhapKhoRepository.findById(chiTietPhieuNhapUpdateDTO.getMaPhieuNhap())
                                     .orElseThrow(()-> new RuntimeException("PhieuNhapKho not found"));

                     chiTietPhieuNhap.setSanPham(sanPham);
                     chiTietPhieuNhap.setGiaNhap(chiTietPhieuNhapUpdateDTO.getGiaNhap());
                     chiTietPhieuNhap.setGiaBan(chiTietPhieuNhapUpdateDTO.getGiaBan());
                     chiTietPhieuNhap.setSoLuong(chiTietPhieuNhapUpdateDTO.getSoLuong());
                     chiTietPhieuNhap.setPhieuNhapKho(phieuNhapKho);
                     return phieuNhapKhoMapper.toChiTietPhieuNhapDTO(chiTietPhieuNhapRepository.save(chiTietPhieuNhap));

                })
                .collect(Collectors.toList());

        for(ChiTietPhieuNhapDTO chiTietPhieuNhapDTO: chiTietPhieuNhapDTOS){
            tongTien += chiTietPhieuNhapDTO.getSoLuong() * chiTietPhieuNhapDTO.getGiaNhap();
        }

        PhieuNhapKho phieuNhapKho = phieuNhapKhoRepository.findById(chiTietPhieuNhapUpdateDTOS.getFirst().getMaPhieuNhap())
                .orElseThrow(()-> new RuntimeException("PhieuNhapKho not found"));
        phieuNhapKho.setTongGiaNhap(tongTien);
        phieuNhapKhoRepository.save(phieuNhapKho);

        return PhieuNhapDTO.builder()
                .ma(phieuNhapKho.getMa())
                .tongGiaNhap(phieuNhapKho.getTongGiaNhap())
                .thoiGianNhap(phieuNhapKho.getThoiGianNhap())
                .tenNhanVien(phieuNhapKho.getNhanVien().getHoTen())
                .tenNhaCungCap(phieuNhapKho.getNhaCungCap().getTenNhaCungCap())
                .chiTietPhieuNhaps(chiTietPhieuNhapDTOS)
                .build();
    }

    public Page<PhieuNhapDTO> getAllPhieuNhap(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PhieuNhapKho> phieuNhapKhos = phieuNhapKhoRepository.findAll(pageable);

        return phieuNhapKhos.map(phieuNhapKho -> PhieuNhapDTO.builder()
                .ma(phieuNhapKho.getMa())
                .tongGiaNhap(phieuNhapKho.getTongGiaNhap())
                .thoiGianNhap(phieuNhapKho.getThoiGianNhap())
                .tenNhanVien(phieuNhapKho.getNhanVien().getHoTen())
                .tenNhaCungCap(phieuNhapKho.getNhaCungCap().getTenNhaCungCap())
                .chiTietPhieuNhaps(null)
                .build());
    }

    public PhieuNhapDTO getById(Integer maPhieuNhap){
        PhieuNhapKho phieuNhapKho = phieuNhapKhoRepository.findById(maPhieuNhap)
               .orElseThrow(() -> new RuntimeException("PhieuNhap not found"));

        List<ChiTietPhieuNhap> chiTietPhieuNhaps = chiTietPhieuNhapRepository.findAllByPhieuNhapKho(phieuNhapKho);

        return phieuNhapKhoMapper.toPhieuNhapDTO(phieuNhapKho, chiTietPhieuNhaps);
    }

    public void deletePhieuNhapKho(Integer maPhieuNhapkho){
        if(phieuNhapKhoRepository.findById(maPhieuNhapkho).isPresent()){
            chiTietPhieuNhapRepository.deleteAllByPhieuNhapKho(phieuNhapKhoRepository.findById(maPhieuNhapkho).get());
            phieuNhapKhoRepository.deleteById(maPhieuNhapkho);
        }
        else throw new RuntimeException("PhieuNhap not found");
    }

    public void deleteChiTietPhieuNhapKho(Integer maChiTietPhieuNhapKho){
        if(chiTietPhieuNhapRepository.findById(maChiTietPhieuNhapKho).isPresent())
            chiTietPhieuNhapRepository.deleteById(maChiTietPhieuNhapKho);
        else throw new RuntimeException("ChiTietPhieuNhap not found");
    }


    private Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }


}
