package com.example.demo.services;

import com.example.demo.dto.AuthenticationDTO;
import com.example.demo.dto.DangNhap;
import com.example.demo.dto.TaiKhoanCreateDTO;
import com.example.demo.dto.TaiKhoanDTO;
import com.example.demo.entities.NhanVien;
import com.example.demo.entities.Role;
import com.example.demo.entities.TaiKhoan;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.CustomJwtException;
import com.example.demo.exception.Error;
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
    private final NhanVienService nhanVienSerivce;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public TaiKhoanService(TaiKhoanRepository taiKhoanRepository,
                           NhanVienService nhanVienSerivce,
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

    public TaiKhoanDTO signUp(TaiKhoanCreateDTO taiKhoanCreateDTO) {
        try {
            // Validate input data
            validateTaiKhoanData(taiKhoanCreateDTO);

            if (usernameExists(taiKhoanCreateDTO.getTenDangNhap())) {
                throw new CustomException(Error.TAIKHOAN_ALREADY_EXISTS);
            }

            Role role;
            try {
                role = roleService.findRoleById(taiKhoanCreateDTO.getRoleId());
            } catch (Exception e) {
                throw new CustomException(Error.ROLE_NOT_FOUND);
            }

            NhanVien nhanVien;
            try {
                nhanVien = nhanVienSerivce.getById(taiKhoanCreateDTO.getMaNhanVien());
            } catch (Exception e) {
                throw new CustomException(Error.NHANVIEN_NOT_FOUND);
            }

            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setMa(getGenerationId());
            taiKhoan.setTenDangNhap(taiKhoanCreateDTO.getTenDangNhap());
            taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoanCreateDTO.getMatKhau()));
            taiKhoan.setTrangThai(true);
            taiKhoan.setThoiGianTao(LocalDateTime.now());
            taiKhoan.setRole(role);
            taiKhoan.setNhanVien(nhanVien);

            try {
                return taiKhoanMapper.toDTO(taiKhoanRepository.save(taiKhoan));
            } catch (Exception e) {
                throw new CustomException(Error.TAIKHOAN_UNABLE_TO_SAVE);
            }
        } catch (CustomException ce) {
            throw ce; // Re-throw if it's already our custom exception
        } catch (Exception e) {
            throw new CustomException(Error.TAIKHOAN_UNABLE_TO_SAVE);
        }
    }

    public AuthenticationDTO signIn(DangNhap account) {
        try {
            String name = account.getTenDangNhap().trim().toLowerCase();

            TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(name)
                    .orElseThrow(() -> new CustomException(Error.TAIKHOAN_NOT_FOUND));

            if (!passwordEncoder.matches(account.getPassword(), taiKhoan.getPassword())) {
                throw new CustomException(Error.TAIKHOAN_INVALID_PASSWORD);
            }

            // Check if account is locked
            if (!taiKhoan.getTrangThai()) {
                throw new CustomException(Error.ACCOUNT_LOCKED);
            }

            UserDetails userDetails = (UserDetails) taiKhoan;

            try {
                String jwtToken = jwtTokenUtil.generateToken(userDetails);
                String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

                return AuthenticationDTO.builder()
                        .token(jwtToken)
                        .refreshToken(refreshToken)
                        .role(taiKhoan.getRole().getChucVu())
                        .build();
            } catch (Exception e) {
                throw new CustomJwtException(Error.JWT_INVALID, e);
            }
        } catch (CustomException | CustomJwtException ce) {
            throw ce; // Re-throw if it's already our custom exception
        } catch (Exception e) {
            throw new CustomException(Error.UNAUTHORIZED);
        }
    }

    private boolean usernameExists(String username) {
        return taiKhoanRepository.findByTenDangNhap(username).isPresent();
    }

    private Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }

    // Validation methods
    private void validateTaiKhoanData(TaiKhoanCreateDTO dto) {
        // Username validation
        if (dto.getTenDangNhap() == null || dto.getTenDangNhap().trim().isEmpty() ||
                dto.getTenDangNhap().length() < 3 || dto.getTenDangNhap().length() > 50) {
            throw new CustomException(Error.TAIKHOAN_INVALID_USERNAME);
        }

        // Password validation
        if (dto.getMatKhau() == null || dto.getMatKhau().trim().isEmpty() ||
                dto.getMatKhau().length() < 6 || dto.getMatKhau().length() > 100) {
            throw new CustomException(Error.TAIKHOAN_INVALID_PASSWORD);
        }

        // RoleId validation
        if (dto.getRoleId() == null) {
            throw new CustomException(Error.ROLE_NOT_FOUND);
        }

        // NhanVien validation
        if (dto.getMaNhanVien() == null) {
            throw new CustomException(Error.NHANVIEN_NOT_FOUND);
        }
    }
}