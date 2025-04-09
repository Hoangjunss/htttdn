package com.example.demo.controllers;



import com.example.demo.dto.*;
import com.example.demo.entities.PhieuNhapKho;

import com.example.demo.services.PhieuNhapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Phiếu Nhập", description = "API quản lý phiếu nhập kho")
@RestController
@RequestMapping("/api/phieu-nhap")
public class PhieuNhapController {

    private final PhieuNhapService phieuNhapService;

    public PhieuNhapController(PhieuNhapService phieuNhapService) {
        this.phieuNhapService = phieuNhapService;
    }

    @Operation(summary = "Tạo phiếu nhập", description = "Tạo một phiếu nhập mới.", responses = {
            @ApiResponse(responseCode = "200", description = "Tạo phiếu nhập thành công", content = @Content(schema = @Schema(implementation = PhieuNhapDTO.class)))
    })
    @PostMapping
    public ResponseEntity<ApiResponses<PhieuNhapDTO>> createPhieuNhap(@RequestBody PhieuNhapCreateDTO createDTO) {
        PhieuNhapDTO result = phieuNhapService.taoPhieuNhap(createDTO);
        return ResponseEntity.ok(new ApiResponses<>(true, "Tạo phiếu nhập thành công!", result));
    }

    @Operation(summary = "Cập nhật phiếu nhập", description = "Cập nhật thông tin của một phiếu nhập.", responses = {
            @ApiResponse(responseCode = "200", description = "Cập nhật thành công", content = @Content(schema = @Schema(implementation = PhieuNhapDTO.class)))
    })
    @PutMapping
    public ResponseEntity<ApiResponses<PhieuNhapDTO>> updatePhieuNhap(@RequestBody PhieuNhapUpdateDTO updateDTO) {
        PhieuNhapDTO result = phieuNhapService.updatePhieuNhap(updateDTO);
        return ResponseEntity.ok(new ApiResponses<>(true, "Cập nhật phiếu nhập thành công!", result));
    }

    @Operation(summary = "Cập nhật chi tiết phiếu nhập", description = "Cập nhật thông tin chi tiết của phiếu nhập.", responses = {
            @ApiResponse(responseCode = "200", description = "Cập nhật chi tiết thành công", content = @Content(schema = @Schema(implementation = PhieuNhapDTO.class)))
    })
    @PutMapping("/chi-tiet")
    public ResponseEntity<ApiResponses<PhieuNhapDTO>> updateChiTietPhieuNhap(@RequestBody List<ChiTietPhieuNhapUpdateDTO> updateDTOS) {
        PhieuNhapDTO result = phieuNhapService.updateChiTietPhieuNhap(updateDTOS);
        return ResponseEntity.ok(new ApiResponses<>(true, "Cập nhật chi tiết phiếu nhập thành công!", result));
    }

    @Operation(summary = "Lấy tất cả phiếu nhập", description = "Lấy danh sách phiếu nhập phân trang.", responses = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công", content = @Content(schema = @Schema(implementation = PhieuNhapDTO.class)))
    })
    @GetMapping
    public ResponseEntity<ApiResponses<Page<PhieuNhapDTO>>> getAllPhieuNhap(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<PhieuNhapDTO> result = phieuNhapService.getAllPhieuNhap(page, size);
        return ResponseEntity.ok(new ApiResponses<>(true, "Lấy danh sách phiếu nhập thành công!", result));
    }

    @Operation(summary = "Lấy phiếu nhập theo ID", description = "Lấy chi tiết phiếu nhập theo mã.", responses = {
            @ApiResponse(responseCode = "200", description = "Lấy thành công", content = @Content(schema = @Schema(implementation = PhieuNhapDTO.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponses<PhieuNhapDTO>> getById(@PathVariable Integer id) {
        PhieuNhapDTO result = phieuNhapService.getById(id);
        return ResponseEntity.ok(new ApiResponses<>(true, "Lấy phiếu nhập thành công!", result));
    }

    @Operation(summary = "Xoá phiếu nhập", description = "Xoá một phiếu nhập theo mã.", responses = {
            @ApiResponse(responseCode = "200", description = "Xoá thành công")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponses<Void>> deletePhieuNhap(@PathVariable Integer id) {
        phieuNhapService.deletePhieuNhapKho(id);
        return ResponseEntity.ok(new ApiResponses<>(true, "Xoá phiếu nhập thành công!", null));
    }

    @Operation(summary = "Xoá chi tiết phiếu nhập", description = "Xoá một chi tiết phiếu nhập theo mã.", responses = {
            @ApiResponse(responseCode = "200", description = "Xoá chi tiết thành công")
    })
    @DeleteMapping("/chi-tiet/{id}")
    public ResponseEntity<ApiResponses<Void>> deleteChiTietPhieuNhap(@PathVariable Integer id) {
        phieuNhapService.deleteChiTietPhieuNhapKho(id);
        return ResponseEntity.ok(new ApiResponses<>(true, "Xoá chi tiết phiếu nhập thành công!", null));
    }

    @Operation(summary = "Tìm kiếm phiếu nhập", description = "Tìm kiếm phiếu nhập theo tháng, năm, mã nhân viên, mã nhà cung cấp.", responses = {
            @ApiResponse(responseCode = "200", description = "Tìm kiếm thành công", content = @Content(schema = @Schema(implementation = PhieuNhapDTO.class)))
    })
    @GetMapping("/search")
    public ResponseEntity<ApiResponses<Page<PhieuNhapKho>>> searchPhieuNhap(
            @RequestParam(required = false) Integer maNhanVien,
            @RequestParam(required = false) Integer thang,
            @RequestParam(required = false) Integer nam,
            @RequestParam(required = false) Integer maNhaCungCap,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<PhieuNhapKho> result = phieuNhapService.searchPhieuNhapKho(maNhanVien, thang, nam, maNhaCungCap, PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiResponses<>(true, "Tìm kiếm phiếu nhập thành công!", result));
    }
}
