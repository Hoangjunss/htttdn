package com.example.demo.services;

import com.example.demo.dto.AuthenticationDTO;
import com.example.demo.dto.DangNhap;
import com.example.demo.dto.TaiKhoanCreateDTO;
import com.example.demo.dto.TaiKhoanDTO;
import com.example.demo.entities.NhanVien;
import com.example.demo.entities.Role;
import com.example.demo.entities.TaiKhoan;
import com.example.demo.mapper.TaiKhoanMapper;
import com.example.demo.repositories.TaiKhoanRepository;
import com.example.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaiKhoanService {

    private final TaiKhoanRepository taiKhoanRepository;
    private final TaiKhoanMapper taiKhoanMapper;
    private final RoleService roleService;
    private final NhanVienSerivce nhanVienSerivce;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public TaiKhoanService(TaiKhoanRepository taiKhoanRepository,
                           NhanVienSerivce nhanVienSerivce,
                           RoleService roleService,
                           TaiKhoanMapper taiKhoanMapper,
                           PasswordEncoder passwordEncoder,
                           JwtTokenUtil jwtTokenUtil) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.nhanVienSerivce = nhanVienSerivce;
        this.roleService = roleService;
        this.taiKhoanMapper = taiKhoanMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public TaiKhoanDTO signUp(TaiKhoanCreateDTO taiKhoanCreateDTO){
        if (usernameExists(taiKhoanCreateDTO.getTenDangNhap())) {
            throw new RuntimeException("User is already existing");
        }
        Role role = roleService.findRoleById(taiKhoanCreateDTO.getRoleId());
        NhanVien nhanVien = nhanVienSerivce.getById(taiKhoanCreateDTO.getMaNhanVien());
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setMa(getGenerationId());
        taiKhoan.setTenDangNhap(taiKhoanCreateDTO.getTenDangNhap());
        taiKhoan.setMatKhau(taiKhoanCreateDTO.getMatKhau());
        taiKhoan.setQuyen(taiKhoanCreateDTO.getQuyen());
        taiKhoan.setThoiGianTao(LocalDateTime.now());
        taiKhoan.setRole(role);
        taiKhoan.setNhanVien(nhanVien);
        return taiKhoanMapper.toDTO(taiKhoanRepository.save(taiKhoan));
    }

    public AuthenticationDTO signIn(DangNhap account) {


        String name = account.getTenDangNhap().trim().toLowerCase();

        if (!usernameExists(name)) {
            throw new RuntimeException("User not found");
        }

        TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(name).orElseThrow();
        if (!passwordEncoder.matches(account.getPassword(), taiKhoan.getPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        if (!taiKhoan.isAccountNonLocked()) {
            throw new RuntimeException("Account is not locked");
        }
        UserDetails userDetails = (UserDetails) taiKhoan;

        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

        return AuthenticationDTO.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .role(taiKhoan.getRole().getChucVu())
                .build();

    }


    private boolean usernameExists(String username) {
        return taiKhoanRepository.findByTenDangNhap(username).isPresent();
    }

    private Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }

}
