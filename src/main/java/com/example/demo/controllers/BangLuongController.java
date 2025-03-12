package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.BangLuongDTO;
import com.example.demo.entities.BangLuong;
import com.example.demo.services.BangLuongService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bang-luong")
public class BangLuongController {
    private final BangLuongService bangLuongService;

    public BangLuongController(BangLuongService bangLuongService) {
        this.bangLuongService = bangLuongService;
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<BangLuongDTO>>> searchBangLuong(
            @RequestParam(required = false) Integer nhanVienId,
            @RequestParam(required = false) Integer cuaHangId,
            @RequestParam(required = false) Integer thang,
            @RequestParam(required = false) Integer nam,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<BangLuong> results = bangLuongService.timBangLuong(nhanVienId, cuaHangId, thang, nam, page, size);

        List<BangLuongDTO> dtoList = results.getContent().stream().map(BangLuongDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "Tìm kiếm bảng lương thành công!",dtoList));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<BangLuongDTO>> createBangLuong(@RequestBody BangLuongDTO dto) {
        BangLuong bangLuong = bangLuongService.taoBangLuong(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Tạo bảng lương thành công!",new BangLuongDTO(bangLuong)));
    }

    @PutMapping("/update/{ma}")
    public ResponseEntity<ApiResponse<BangLuongDTO>> updateBangLuong(@PathVariable Integer ma, @RequestBody BangLuongDTO dto) {
        BangLuong bangLuong = bangLuongService.capNhatBangLuong(ma, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật bảng lương thành công!",new BangLuongDTO(bangLuong)));
    }
}
