package com.example.demo.services;

import com.example.demo.dto.*;
import com.example.demo.entities.Kho;
import com.example.demo.entities.SanPham;
import com.example.demo.entities.TonKho;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.Error;
import com.example.demo.mapper.KhoMapper;
import com.example.demo.repositories.KhoRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.repositories.TonKhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class KhoService {
    private final KhoRepository khoRepository;
    private final TonKhoRepository tonKhoRepository;
    private final SanPhamRepository sanPhamRepository;
    private final KhoMapper khoMapper;

    @Autowired
    public KhoService(KhoRepository khoRepository,
                      TonKhoRepository tonKhoRepository,
                      SanPhamRepository sanPhamRepository,
                      KhoMapper khoMapper) {
        this.khoRepository = khoRepository;
        this.tonKhoRepository = tonKhoRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.khoMapper = khoMapper;
    }

    private Kho getKhoById(Integer maKho){
        return khoRepository.findById(maKho)
                .orElseThrow(()-> new CustomException(Error.KHO_NOT_FOUND));
    }

    private SanPham getSanPhamById(Integer maSanPham){
        return sanPhamRepository.findById(maSanPham)
                .orElseThrow(()-> new CustomException(Error.SANPHAM_NOT_FOUND));
    }

    private TonKho getTonKhoById(Integer maTonKho){
        return tonKhoRepository.findById(maTonKho)
                .orElseThrow(()-> new CustomException(Error.TONKHO_NOT_FOUND));
    }

    private TonKho getTonKhoByKhoAndSP(Kho kho, SanPham sanPham){
        return tonKhoRepository.findTonKhoByKhoAndSanPham(kho, sanPham);
    }

    public KhoDTO capNhatTonKhoTuPhieuNhap(PhieuNhapDTO phieuNhapDTO){
        Integer maCuaHang = khoRepository.findCuaHangByPhieuNhap(phieuNhapDTO.getMa());


        Kho kho = khoRepository.findKhoByMaCuaHang(maCuaHang);

        List<TonKhoDTO> tonKhoDTOS = phieuNhapDTO.getChiTietPhieuNhaps()
                .stream()
                .map(pn -> {
                    SanPham sanPham = getSanPhamById(pn.getMaSanPham());
                    TonKho tonKho = getTonKhoByKhoAndSP(kho, sanPham);
                    if(tonKho!=null){
                        tonKho.setSoLuong(tonKho.getSoLuong() + pn.getSoLuong());
                        tonKhoRepository.save(tonKho);
                    }else{
                        tonKho = TonKho.builder()
                               .ma(getGenerationId())
                               .soLuong(pn.getSoLuong())
                               .kho(kho)
                               .sanPham(sanPham)
                               .build();
                        tonKhoRepository.save(tonKho);
                    }
                    return khoMapper.toToKhoDTO(tonKho);
                }).collect(Collectors.toList());

        return khoMapper.toKhoDTO(kho, tonKhoDTOS);

    }

    public TonKhoDTO capNhatSoLuongTonKho(TonkhoUpdateDTO tonkhoUpdateDTO){
        TonKho tonKho = getTonKhoById(tonkhoUpdateDTO.getMa());

        if(tonKho!=null){
            tonKho.setSoLuong(tonkhoUpdateDTO.getSoLuong());
            tonKhoRepository.save(tonKho);
        }else{
            throw new RuntimeException("ton kho not found");
        }
        return khoMapper.toToKhoDTO(tonKho);
    }

    public Page<KhoDTO> getAllKho(int page, int size){
        Page<Kho> pageKho = khoRepository.findAll(PageRequest.of(page, size));

        return pageKho.map(kho -> KhoDTO.builder()
                        .ma(kho.getMa())
                        .tenKho(kho.getTenKho())
                        .diaChi(kho.getDiaChi())
                        .maCuaHang(kho.getCuaHang().getMa())
                        .tonKhoDTOS(null)
                        .build()
                );
    }

    public KhoDTO getKhoByMa(Integer maKho){
        Kho kho = getKhoById(maKho);
        List<TonKhoDTO> tonKhoDTOS = tonKhoRepository.findTonKhoByKho(kho)
               .stream()
               .map(khoMapper::toToKhoDTO)
               .collect(Collectors.toList());
        return khoMapper.toKhoDTO(kho, tonKhoDTOS);
    }

    private Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
