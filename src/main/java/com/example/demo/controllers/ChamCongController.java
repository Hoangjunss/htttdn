package com.example.demo.controllers;

import com.example.demo.dto.ApiResponses;
import com.example.demo.dto.ChamCongDTO;
import com.example.demo.entities.ChamCong;
import com.example.demo.services.ChamCongService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Chấm Công", description = "API quản lý chấm công nhân viên")
@RestController
@RequestMapping("/api/cham-cong")
public class ChamCongController {
    private final ChamCongService chamCongService;

    public ChamCongController(ChamCongService chamCongService) {
        this.chamCongService = chamCongService;
    }

    @Operation(
            summary = "Chấm công vào",
            description = "Ghi nhận thời gian nhân viên chấm công vào.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chấm công vào thành công", content = @Content(schema = @Schema(implementation = ChamCongDTO.class)))
            }
    )
    @PostMapping("/check-in")
    public ResponseEntity<ApiResponses<ChamCongDTO>> checkIn() {
        ChamCong chamCong = chamCongService.checkIn();
        ChamCongDTO dto = new ChamCongDTO(chamCong);
        return ResponseEntity.ok(new ApiResponses<>(true, "Chấm công vào thành công!", dto));
    }

    @Operation(
            summary = "Chấm công ra",
            description = "Ghi nhận thời gian nhân viên chấm công ra.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chấm công ra thành công", content = @Content(schema = @Schema(implementation = ChamCongDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Không tìm thấy dữ liệu chấm công")
            }
    )
    @PostMapping("/check-out")
    public ResponseEntity<ApiResponses<ChamCongDTO>> checkOut() {
        ChamCong chamCong = chamCongService.checkOut();
        if (chamCong == null) {
            return ResponseEntity.badRequest().body(new ApiResponses<>(false, "Không tìm thấy dữ liệu chấm công!", null));
        }
        ChamCongDTO dto = new ChamCongDTO(chamCong);
        return ResponseEntity.ok(new ApiResponses<>(true, "Chấm công ra thành công!", dto));
    }

    @Operation(
            summary = "Tìm kiếm chấm công",
            description = "Tìm kiếm dữ liệu chấm công theo nhân viên, cửa hàng và ngày.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tìm kiếm thành công", content = @Content(schema = @Schema(implementation = ChamCongDTO.class)))
            }
    )
    @GetMapping("/search")
    public ResponseEntity<ApiResponses<List<ChamCongDTO>>> searchChamCong(
            @RequestParam(required = false) Integer nhanVienId,
            @RequestParam(required = false) Integer cuaHangId,
            @RequestParam(required = false) String ngay,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        LocalDate date = (ngay != null) ? LocalDate.parse(ngay) : null;
        Page<ChamCong> results = chamCongService.timChamCong(nhanVienId, cuaHangId, date, page, size);
        List<ChamCongDTO> dtoList = results.getContent().stream().map(ChamCongDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponses<>(true, "Tìm kiếm thành công!", dtoList));
    }
}