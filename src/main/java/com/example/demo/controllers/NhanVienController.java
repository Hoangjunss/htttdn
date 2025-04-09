package com.example.demo.controllers;

import com.example.demo.dto.ApiResponses;
import com.example.demo.dto.NhanVienCreateDTO;
import com.example.demo.dto.NhanVienDTO;
import com.example.demo.dto.NhanVienUpdateDTO;
import com.example.demo.services.NhanVienService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Nhân Viên", description = "API quản lý nhân viên")
@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienController {

    private final NhanVienService nhanVienService;

    public NhanVienController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }

    @Operation(
            summary = "Lấy thông tin nhân viên theo ID",
            description = "Trả về thông tin chi tiết của nhân viên theo mã định danh.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lấy thông tin thành công", content = @Content(schema = @Schema(implementation = NhanVienDTO.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponses<NhanVienDTO>> getNhanVien(@PathVariable Integer id) {
        NhanVienDTO dto = nhanVienService.getNhanVienDTOById(id);
        return ResponseEntity.ok(new ApiResponses<>(true, "Lấy thông tin nhân viên thành công!", dto));
    }

    @Operation(
            summary = "Tạo mới nhân viên",
            description = "Thêm một nhân viên mới vào hệ thống.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tạo nhân viên thành công", content = @Content(schema = @Schema(implementation = NhanVienDTO.class)))
            }
    )
    @PostMapping
    public ResponseEntity<ApiResponses<NhanVienDTO>> createNhanVien(@RequestBody NhanVienCreateDTO dto) {
        NhanVienDTO created = nhanVienService.createNhanVien(dto);
        return ResponseEntity.ok(new ApiResponses<>(true, "Tạo nhân viên thành công!", created));
    }

    @Operation(
            summary = "Cập nhật nhân viên",
            description = "Cập nhật thông tin của một nhân viên dựa trên ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cập nhật nhân viên thành công", content = @Content(schema = @Schema(implementation = NhanVienDTO.class)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponses<NhanVienDTO>> updateNhanVien(@PathVariable Integer id, @RequestBody NhanVienUpdateDTO dto) {
        dto.setMa(id);
        NhanVienDTO updated = nhanVienService.updateNhanVien(dto);
        return ResponseEntity.ok(new ApiResponses<>(true, "Cập nhật nhân viên thành công!", updated));
    }

    @Operation(
            summary = "Xóa nhân viên",
            description = "Xóa nhân viên khỏi hệ thống dựa theo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Xóa nhân viên thành công")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponses<Void>> deleteNhanVien(@PathVariable Integer id) {
        // Optional: hiện chưa có xóa thật sự trong service
        return ResponseEntity.ok(new ApiResponses<>(true, "Chưa triển khai xóa nhân viên!", null));
    }
}
