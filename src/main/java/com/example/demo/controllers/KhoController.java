package com.example.demo.controllers;

import com.example.demo.dto.*;
import com.example.demo.services.KhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Kho", description = "API quản lý kho và cập nhật tồn kho")
@RestController
@RequestMapping("/api/kho")
public class KhoController {

    private final KhoService khoService;

    public KhoController(KhoService khoService) {
        this.khoService = khoService;
    }

    @Operation(summary = "Lấy danh sách kho", description = "Trả về danh sách các kho trong hệ thống (phân trang)")
    @GetMapping
    public ResponseEntity<ApiResponses<Page<KhoDTO>>> getAllKho(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<KhoDTO> result = khoService.getAllKho(page, size);
        return ResponseEntity.ok(new ApiResponses<>(true, "Lấy danh sách kho thành công!", result));
    }

    @Operation(summary = "Lấy chi tiết kho", description = "Trả về thông tin chi tiết một kho theo mã")
    @GetMapping("/{maKho}")
    public ResponseEntity<ApiResponses<KhoDTO>> getKhoByMa(@PathVariable Integer maKho) {
        KhoDTO kho = khoService.getKhoByMa(maKho);
        return ResponseEntity.ok(new ApiResponses<>(true, "Lấy thông tin kho thành công!", kho));
    }

    @Operation(summary = "Cập nhật số lượng tồn kho thủ công", description = "Cho phép cập nhật trực tiếp số lượng tồn kho theo mã tồn kho")
    @PutMapping("/ton-kho")
    public ResponseEntity<ApiResponses<TonKhoDTO>> capNhatTonKho(@RequestBody TonkhoUpdateDTO tonkhoUpdateDTO) {
        TonKhoDTO result = khoService.capNhatSoLuongTonKho(tonkhoUpdateDTO);
        return ResponseEntity.ok(new ApiResponses<>(true, "Cập nhật tồn kho thành công!", result));
    }

    @Operation(summary = "Cập nhật kho từ phiếu nhập", description = "Cập nhật tồn kho dựa trên danh sách sản phẩm từ phiếu nhập")
    @PostMapping("/cap-nhat-tu-phieu-nhap")
    public ResponseEntity<ApiResponses<KhoDTO>> capNhatTuPhieuNhap(@RequestBody PhieuNhapDTO phieuNhapDTO) {
        KhoDTO result = khoService.capNhatTonKhoTuPhieuNhap(phieuNhapDTO);
        return ResponseEntity.ok(new ApiResponses<>(true, "Cập nhật kho từ phiếu nhập thành công!", result));
    }
}
