package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.ChamCongDTO;
import com.example.demo.entities.ChamCong;
import com.example.demo.services.ChamCongService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cham-cong")
public class ChamCongController {
    private final ChamCongService chamCongService;

    public ChamCongController(ChamCongService chamCongService) {
        this.chamCongService = chamCongService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<ChamCongDTO>> checkIn() {
        ChamCong chamCong = chamCongService.checkIn();
        ChamCongDTO dto = new ChamCongDTO(chamCong);
        return ResponseEntity.ok(new ApiResponse<>(true, "Chấm công vào thành công!",dto));
    }

    @PostMapping("/check-out")
    public ResponseEntity<ApiResponse<ChamCongDTO>> checkOut() {
        ChamCong chamCong = chamCongService.checkOut();
        if (chamCong == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false,"Không tìm thấy dữ liệu chấm công!",null));
        }
        ChamCongDTO dto = new ChamCongDTO(chamCong);
        return ResponseEntity.ok(new ApiResponse<>(true, "Chấm công ra thành công!",dto));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ChamCongDTO>>> searchChamCong(
            @RequestParam(required = false) Integer nhanVienId,
            @RequestParam(required = false) Integer cuaHangId,
            @RequestParam(required = false) String ngay,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        LocalDate date = (ngay != null) ? LocalDate.parse(ngay) : null;
        Page<ChamCong> results = chamCongService.timChamCong(nhanVienId, cuaHangId, date, page, size);

        List<ChamCongDTO> dtoList = results.getContent().stream().map(ChamCongDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "Tìm kiếm thành công!",dtoList));
    }
}
