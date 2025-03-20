package com.example.demo.controllers;

import com.example.demo.dto.ApiResponses;
import com.example.demo.dto.BangLuongDTO;
import com.example.demo.entities.BangLuong;
import com.example.demo.services.BangLuongService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Tag(name = "Bảng Lương", description = "API quản lý bảng lương nhân viên")
@RestController
@RequestMapping("/api/bang-luong")
public class BangLuongController {
    private final BangLuongService bangLuongService;

    public BangLuongController(BangLuongService bangLuongService) {
        this.bangLuongService = bangLuongService;
    }

    @Operation(
            summary = "Tìm kiếm bảng lương",
            description = "Tìm kiếm bảng lương dựa trên các tiêu chí như nhân viên, cửa hàng, tháng và năm.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Thành công", content = @Content(schema = @Schema(implementation = BangLuongDTO.class)))
            }
    )
    @GetMapping("/search")
    public ResponseEntity<ApiResponses<List<BangLuongDTO>>> searchBangLuong(
            @RequestParam(required = false) Integer nhanVienId,
            @RequestParam(required = false) Integer cuaHangId,
            @RequestParam(required = false) Integer thang,
            @RequestParam(required = false) Integer nam,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<BangLuong> results = bangLuongService.timBangLuong(nhanVienId, cuaHangId, thang, nam, page, size);
        List<BangLuongDTO> dtoList = results.getContent().stream().map(BangLuongDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponses<>(true, "Tìm kiếm bảng lương thành công!", dtoList));
    }

    @Operation(
            summary = "Tạo bảng lương",
            description = "Tạo một bảng lương mới từ dữ liệu nhập vào.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tạo thành công", content = @Content(schema = @Schema(implementation = BangLuongDTO.class)))
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ApiResponses<BangLuongDTO>> createBangLuong(
            @RequestBody(description = "Dữ liệu bảng lương cần tạo", required = true) BangLuongDTO dto) {
        BangLuong bangLuong = bangLuongService.taoBangLuong(dto);
        return ResponseEntity.ok(new ApiResponses<>(true, "Tạo bảng lương thành công!", new BangLuongDTO(bangLuong)));
    }

    @Operation(
            summary = "Cập nhật bảng lương",
            description = "Cập nhật thông tin bảng lương dựa trên mã bảng lương.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cập nhật thành công", content = @Content(schema = @Schema(implementation = BangLuongDTO.class)))
            }
    )
    @PutMapping("/update/{ma}")
    public ResponseEntity<ApiResponses<BangLuongDTO>> updateBangLuong(
            @PathVariable Integer ma,
            @RequestBody(description = "Dữ liệu bảng lương cần cập nhật", required = true) BangLuongDTO dto) {
        BangLuong bangLuong = bangLuongService.capNhatBangLuong(ma, dto);
        return ResponseEntity.ok(new ApiResponses<>(true, "Cập nhật bảng lương thành công!", new BangLuongDTO(bangLuong)));
    }
}
