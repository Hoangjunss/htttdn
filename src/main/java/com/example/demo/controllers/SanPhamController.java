package com.example.demo.controllers;

import com.example.demo.dto.ApiResponses;
import com.example.demo.dto.SanPhamDTO;
import com.example.demo.entities.SanPham;
import com.example.demo.services.SanPhamService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Sản Phẩm", description = "API quản lý sản phẩm")
@RestController
@RequestMapping("/api/san-pham")
public class SanPhamController {
    private final SanPhamService sanPhamService;

    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @Operation(
            summary = "Tìm kiếm sản phẩm",
            description = "Tìm kiếm sản phẩm dựa trên nhiều tiêu chí như tồn kho, cửa hàng, loại sản phẩm và size.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tìm kiếm thành công", content = @Content(schema = @Schema(implementation = SanPhamDTO.class)))
            }
    )
    @GetMapping("/search")
    public ResponseEntity<ApiResponses<List<SanPhamDTO>>> searchSanPham(
            @RequestParam(required = false) Integer tonKhoId,
            @RequestParam(required = false) Integer cuaHangId,
            @RequestParam(required = false) Integer loaiSanPhamId,
            @RequestParam(required = false) Integer sizeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<SanPham> results = sanPhamService.timSanPham(tonKhoId, cuaHangId, loaiSanPhamId, sizeId, page, size);
        List<SanPhamDTO> dtoList = results.getContent().stream().map(SanPhamDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponses<>(true, "Tìm kiếm sản phẩm thành công!", dtoList));
    }

    @Operation(
            summary = "Thêm sản phẩm",
            description = "Tạo một sản phẩm mới trong hệ thống.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tạo sản phẩm thành công", content = @Content(schema = @Schema(implementation = SanPhamDTO.class)))
            }
    )
    @PostMapping
    public ResponseEntity<ApiResponses<SanPhamDTO>> createSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
        SanPham sanPham = sanPhamService.taoSanPham(sanPhamDTO);
        return ResponseEntity.ok(new ApiResponses<>(true, "Tạo sản phẩm thành công!", new SanPhamDTO(sanPham)));
    }

    @Operation(
            summary = "Cập nhật sản phẩm",
            description = "Cập nhật thông tin của một sản phẩm dựa trên ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cập nhật sản phẩm thành công", content = @Content(schema = @Schema(implementation = SanPhamDTO.class)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponses<SanPhamDTO>> updateSanPham(@PathVariable Integer id, @RequestBody SanPhamDTO sanPhamDTO) {
        SanPham sanPham = sanPhamService.capNhatSanPham(id, sanPhamDTO);
        return ResponseEntity.ok(new ApiResponses<>(true, "Cập nhật sản phẩm thành công!", new SanPhamDTO(sanPham)));
    }

    @Operation(
            summary = "Xóa sản phẩm",
            description = "Xóa một sản phẩm khỏi hệ thống bằng ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Xóa sản phẩm thành công")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponses<Void>> deleteSanPham(@PathVariable Integer id) {
        sanPhamService.xoaSanPham(id);
        return ResponseEntity.ok(new ApiResponses<>(true, "Xóa sản phẩm thành công!", null));
    }
}
