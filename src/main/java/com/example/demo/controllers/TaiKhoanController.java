package com.example.demo.controllers;

import com.example.demo.dto.*;
import com.example.demo.services.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;

    @Autowired
    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<TaiKhoanDTO>> signUp(@RequestBody TaiKhoanCreateDTO taiKhoanCreateDTO) {
        TaiKhoanDTO taiKhoanDTO = taiKhoanService.signUp(taiKhoanCreateDTO);
        ApiResponse<TaiKhoanDTO> response = new ApiResponse<>(true, "Đăng ký thành công", taiKhoanDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<AuthenticationDTO>> signIn(@RequestBody DangNhap dangNhap) {
        AuthenticationDTO authenticationDTO = taiKhoanService.signIn(dangNhap);
        ApiResponse<AuthenticationDTO> response = new ApiResponse<>(true, "Đăng nhập thành công", authenticationDTO);
        return ResponseEntity.ok(response);
    }

}
