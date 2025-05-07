package com.example.demo.controllers;

import com.example.demo.dto.ApiResponses;
import com.example.demo.dto.DonHangCreateDTO;
import com.example.demo.dto.DonHangDTO;
import com.example.demo.entities.DonHang;
import com.example.demo.services.DonHangService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Tag(name = "Đơn Hàng", description = "API quản lý đơn hàng")
@RestController
@RequestMapping("/api/don-hang")
public class DonHangController {

    private final DonHangService donHangService;

    public DonHangController(DonHangService donHangService) {
        this.donHangService = donHangService;
    }

    @Operation(summary = "Tạo đơn hàng", description = "Tạo mới một đơn hàng trong hệ thống.")
    @PostMapping
    public ResponseEntity<ApiResponses<DonHangDTO>> createDonHang(@RequestBody DonHangCreateDTO dto) {
        DonHangDTO donHang = donHangService.save(dto);
        return ResponseEntity.ok(new ApiResponses<>(true, "Tạo đơn hàng thành công!", donHang));
    }

    @Operation(summary = "Cập nhật đơn hàng", description = "Cập nhật thông tin đơn hàng dựa trên mã.")
    @PutMapping("/{ma}")
    public ResponseEntity<ApiResponses<DonHang>> updateDonHang(@PathVariable Integer ma, @RequestBody DonHang donHang) {
        DonHang updated = donHangService.updateDonHang(ma, donHang);
        return ResponseEntity.ok(new ApiResponses<>(true, "Cập nhật đơn hàng thành công!", updated));
    }

    @Operation(summary = "Tìm kiếm đơn hàng", description = "Tìm kiếm đơn hàng theo mã nhân viên và ngày/tháng/năm.")
    @GetMapping("/search")
    public ResponseEntity<ApiResponses<Page<DonHang>>> searchDonHang(
            @RequestParam(required = false) Integer maNhanVien,
            @RequestParam(required = false) Integer ngay,
            @RequestParam(required = false) Integer thang,
            @RequestParam(required = false) Integer nam,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<DonHang> results = donHangService.searchDonHang(maNhanVien, ngay, thang, nam, PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiResponses<>(true, "Tìm kiếm đơn hàng thành công!", results));
    }

    @Operation(summary = "Thống kê số đơn hàng theo ngày", description = "Trả về tổng số đơn hàng trong ngày.")
    @GetMapping("/count/ngay")
    public ResponseEntity<ApiResponses<Long>> countByNgay(@RequestParam String date) {
        long count = donHangService.countByNgay(LocalDate.parse(date).atStartOfDay());
        return ResponseEntity.ok(new ApiResponses<>(true, "Thống kê theo ngày thành công!", count));
    }

    @Operation(summary = "Thống kê số đơn hàng theo tháng", description = "Trả về tổng số đơn hàng trong tháng.")
    @GetMapping("/count/thang")
    public ResponseEntity<ApiResponses<Long>> countByThang(@RequestParam int year, @RequestParam int month) {
        long count = donHangService.countByThang(year, month);
        return ResponseEntity.ok(new ApiResponses<>(true, "Thống kê theo tháng thành công!", count));
    }

    @Operation(summary = "Thống kê số đơn hàng theo năm", description = "Trả về tổng số đơn hàng trong năm.")
    @GetMapping("/count/nam")
    public ResponseEntity<ApiResponses<Long>> countByNam(@RequestParam int year) {
        long count = donHangService.countByNam(year);
        return ResponseEntity.ok(new ApiResponses<>(true, "Thống kê theo năm thành công!", count));
    }

    @Operation(summary = "Tổng doanh thu theo ngày", description = "Trả về tổng tiền bán được trong ngày.")
    @GetMapping("/sum/ngay")
    public ResponseEntity<ApiResponses<Double>> sumByNgay(@RequestParam String date) {
        Double sum = donHangService.sumByNgay(LocalDate.parse(date).atStartOfDay());
        return ResponseEntity.ok(new ApiResponses<>(true, "Tổng doanh thu theo ngày thành công!", sum));
    }

    @Operation(summary = "Tổng doanh thu theo tháng", description = "Trả về tổng tiền bán được trong tháng.")
    @GetMapping("/sum/thang")
    public ResponseEntity<ApiResponses<Double>> sumByThang(@RequestParam int year, @RequestParam int month) {
        Double sum = donHangService.sumByThang(year, month);
        return ResponseEntity.ok(new ApiResponses<>(true, "Tổng doanh thu theo tháng thành công!", sum));
    }

    @Operation(summary = "Tổng doanh thu theo năm", description = "Trả về tổng tiền bán được trong năm.")
    @GetMapping("/sum/nam")
    public ResponseEntity<ApiResponses<Double>> sumByNam(@RequestParam int year) {
        Double sum = donHangService.sumByNam(year);
        return ResponseEntity.ok(new ApiResponses<>(true, "Tổng doanh thu theo năm thành công!", sum));
    }
}
