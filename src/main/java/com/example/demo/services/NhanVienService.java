package com.example.demo.services;

import com.example.demo.dto.CuaHangDTO;
import com.example.demo.dto.NhanVienCreateDTO;
import com.example.demo.dto.NhanVienDTO;
import com.example.demo.dto.NhanVienUpdateDTO;
import com.example.demo.entities.NhanVien;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.Error;
import com.example.demo.repositories.CuaHangRepository;
import com.example.demo.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;
    private final CuaHangRepository cuaHangRepository;

    @Autowired
    public NhanVienService(NhanVienRepository nhanVienRepository, CuaHangRepository cuaHangRepository) {
        this.nhanVienRepository = nhanVienRepository;
        this.cuaHangRepository = cuaHangRepository;
    }

    public Boolean isEnabledNhanVien(Integer id) {
        return nhanVienRepository.findById(id).isPresent();
    }

    public NhanVien getById(Integer id) {
        return nhanVienRepository.findById(id)
                .orElseThrow(() -> new CustomException(Error.NHANVIEN_NOT_FOUND));
    }

    public NhanVienDTO getNhanVienDTOById(Integer id) {
        NhanVien nhanVien = getById(id);
        return mapToDTO(nhanVien);
    }

    public NhanVienDTO createNhanVien(NhanVienCreateDTO dto) {
        try {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMa(getGenerationId());
            mapFromCreateDTO(nhanVien, dto);

            nhanVienRepository.save(nhanVien);
            return mapToDTO(nhanVien);
        } catch (Exception e) {
            throw new CustomException(Error.NHANVIEN_UNABLE_TO_SAVE);
        }
    }

    public NhanVienDTO updateNhanVien(NhanVienUpdateDTO dto) {
        try {
            NhanVien nhanVien = getById(dto.getMa());
            mapFromUpdateDTO(nhanVien, dto);
            nhanVienRepository.save(nhanVien);
            return mapToDTO(nhanVien);
        } catch (CustomException ce) {
            throw ce; // Rethrow CustomException if it's already a custom exception
        } catch (Exception e) {
            throw new CustomException(Error.NHANVIEN_UNABLE_TO_UPDATE);
        }
    }

    // =============================
    // Helpers for mapping
    // =============================

    private void mapFromCreateDTO(NhanVien nv, NhanVienCreateDTO dto) {
        // Validate fields
        validateNhanVienFields(dto.getHoTen(), dto.getEmail(), dto.getSoDienThoai(), dto.getTiLeHoaHong(), dto.getLuongTheoGio(), dto.getChucVu());

        nv.setHoTen(dto.getHoTen());
        try {
            nv.setNgaySinh(String.valueOf(LocalDate.parse(dto.getNgaySinh())));
        } catch (Exception e) {
            throw new CustomException(Error.NHANVIEN_INVALID_BIRTH_DATE);
        }
        nv.setGioiTinh(dto.getGioiTinh());
        nv.setDiaChi(dto.getDiaChi());
        nv.setEmail(dto.getEmail());
        nv.setSoDienThoai(dto.getSoDienThoai());
        nv.setTiLeHoaHong(dto.getTiLeHoaHong());
        nv.setLuongTheoGio(dto.getLuongTheoGio());
        nv.setChucVu(dto.getChucVu());
        nv.setCuaHang(cuaHangRepository.findById(dto.getMaCuaHang())
                .orElseThrow(() -> new CustomException(Error.CUAHANG_NOT_FOUND)));
    }

    private void mapFromUpdateDTO(NhanVien nv, NhanVienUpdateDTO dto) {
        // Validate only fields that are being updated
        if (dto.getHoTen() != null && !dto.getHoTen().isEmpty()) {
            if (!isValidName(dto.getHoTen())) {
                throw new CustomException(Error.NHANVIEN_INVALID_NAME);
            }
            nv.setHoTen(dto.getHoTen());
        }

        if (dto.getNgaySinh() != null && !dto.getNgaySinh().isEmpty()) {
            try {
                nv.setNgaySinh(String.valueOf(LocalDate.parse(dto.getNgaySinh())));
            } catch (Exception e) {
                throw new CustomException(Error.NHANVIEN_INVALID_BIRTH_DATE);
            }
        }

        if (dto.getGioiTinh() != null && !dto.getGioiTinh().isEmpty()) {
            if (!isValidGender(dto.getGioiTinh())) {
                throw new CustomException(Error.NHANVIEN_INVALID_GENDER);
            }
            nv.setGioiTinh(dto.getGioiTinh());
        }

        if (dto.getDiaChi() != null && !dto.getDiaChi().isEmpty()) {
            if (!isValidAddress(dto.getDiaChi())) {
                throw new CustomException(Error.NHANVIEN_INVALID_ADDRESS);
            }
            nv.setDiaChi(dto.getDiaChi());
        }

        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            if (!isValidEmail(dto.getEmail())) {
                throw new CustomException(Error.NHANVIEN_INVALID_EMAIL);
            }
            nv.setEmail(dto.getEmail());
        }

        if (dto.getSoDienThoai() != null && !dto.getSoDienThoai().isEmpty()) {
            if (!isValidPhone(dto.getSoDienThoai())) {
                throw new CustomException(Error.NHANVIEN_INVALID_PHONE);
            }
            nv.setSoDienThoai(dto.getSoDienThoai());
        }

        if (dto.getTiLeHoaHong() != null) {
            if (!isValidCommission(dto.getTiLeHoaHong())) {
                throw new CustomException(Error.NHANVIEN_INVALID_COMMISSION);
            }
            nv.setTiLeHoaHong(dto.getTiLeHoaHong());
        }

        if (dto.getLuongTheoGio() != null) {
            if (!isValidHourlyWage(dto.getLuongTheoGio())) {
                throw new CustomException(Error.NHANVIEN_INVALID_HOURLY_WAGE);
            }
            nv.setLuongTheoGio(dto.getLuongTheoGio());
        }

        if (dto.getChucVu() != null && !dto.getChucVu().isEmpty()) {
            if (!isValidPosition(dto.getChucVu())) {
                throw new CustomException(Error.NHANVIEN_INVALID_POSITION);
            }
            nv.setChucVu(dto.getChucVu());
        }

        if (dto.getMaCuaHang() != null) {
            nv.setCuaHang(
                    cuaHangRepository.findById(dto.getMaCuaHang())
                            .orElseThrow(() -> new CustomException(Error.CUAHANG_NOT_FOUND))
            );
        }
    }

    // Validation methods
    private void validateNhanVienFields(String hoTen, String email, String soDienThoai, Float tiLeHoaHong, Double luongTheoGio, String chucVu) {
        if (!isValidName(hoTen)) {
            throw new CustomException(Error.NHANVIEN_INVALID_NAME);
        }

        if (!isValidEmail(email)) {
            throw new CustomException(Error.NHANVIEN_INVALID_EMAIL);
        }

        if (!isValidPhone(soDienThoai)) {
            throw new CustomException(Error.NHANVIEN_INVALID_PHONE);
        }

        if (!isValidCommission(tiLeHoaHong)) {
            throw new CustomException(Error.NHANVIEN_INVALID_COMMISSION);
        }

        if (!isValidHourlyWage(luongTheoGio)) {
            throw new CustomException(Error.NHANVIEN_INVALID_HOURLY_WAGE);
        }

        if (!isValidPosition(chucVu)) {
            throw new CustomException(Error.NHANVIEN_INVALID_POSITION);
        }
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        // Basic email validation
        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private boolean isValidPhone(String phone) {
        // Basic phone validation - adjust according to your requirements
        return phone != null && phone.matches("^[0-9]{10,12}$");
    }

    private boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    private boolean isValidGender(String gender) {
        return gender != null && (gender.equalsIgnoreCase("Nam") || gender.equalsIgnoreCase("Nữ") || gender.equalsIgnoreCase("Khác"));
    }

    private boolean isValidCommission(Float commission) {
        return commission != null && commission >= 0 && commission <= 100;
    }

    private boolean isValidHourlyWage(Double wage) {
        return wage != null && wage > 0;
    }

    private boolean isValidPosition(String position) {
        return position != null && !position.trim().isEmpty();
    }

    private NhanVienDTO mapToDTO(NhanVien nv) {
        NhanVienDTO dto = new NhanVienDTO();
        dto.setMa(nv.getMa());
        dto.setHoTen(nv.getHoTen());
        dto.setNgaySinh(nv.getNgaySinh().toString());
        dto.setGioiTinh(nv.getGioiTinh());
        dto.setDiaChi(nv.getDiaChi());
        dto.setEmail(nv.getEmail());
        dto.setSoDienThoai(nv.getSoDienThoai());
        dto.setTiLeHoaHong(nv.getTiLeHoaHong());
        dto.setLuongTheoGio(nv.getLuongTheoGio());
        dto.setChucVu(nv.getChucVu());

        CuaHangDTO chDTO = new CuaHangDTO();
        chDTO.setMa(nv.getCuaHang().getMa());
        chDTO.setTenCuaHang(nv.getCuaHang().getTenCuaHang());
        // Bạn có thể map thêm thông tin cửa hàng tại đây

        dto.setCuaHangDTO(chDTO);
        return dto;
    }

    private Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}