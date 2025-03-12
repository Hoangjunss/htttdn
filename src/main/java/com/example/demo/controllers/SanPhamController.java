package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.SanPhamDTO;
import com.example.demo.entities.SanPham;
import com.example.demo.services.SanPhamService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/san-pham")
public class SanPhamController {
    private final SanPhamService sanPhamService;

    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    // 🔍 Tìm kiếm sản phẩm (có phân trang)
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SanPhamDTO>>> searchSanPham(
            @RequestParam(required = false) Integer tonKhoId,
            @RequestParam(required = false) Integer cuaHangId,
            @RequestParam(required = false) Integer loaiSanPhamId,
            @RequestParam(required = false) Integer sizeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<SanPham> results = sanPhamService.timSanPham(tonKhoId, cuaHangId, loaiSanPhamId, sizeId, page, size);
        List<SanPhamDTO> dtoList = results.getContent().stream().map(SanPhamDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "Tìm kiếm sản phẩm thành công!", dtoList));
    }

    // ➕ Thêm sản phẩm
    @PostMapping
    public ResponseEntity<ApiResponse<SanPhamDTO>> createSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
        SanPham sanPham = sanPhamService.taoSanPham(sanPhamDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Tạo sản phẩm thành công!", new SanPhamDTO(sanPham)));
    }

    // ✏️ Cập nhật sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SanPhamDTO>> updateSanPham(@PathVariable Integer id, @RequestBody SanPhamDTO sanPhamDTO) {
        SanPham sanPham = sanPhamService.capNhatSanPham(id, sanPhamDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật sản phẩm thành công!", new SanPhamDTO(sanPham)));
    }

    // ❌ Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSanPham(@PathVariable Integer id) {
        sanPhamService.xoaSanPham(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xóa sản phẩm thành công!", null));
    }
}
