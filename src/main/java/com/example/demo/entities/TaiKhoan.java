package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaiKhoan implements UserDetails {
    @Id
    private Integer ma;
    private LocalDateTime thoiGianTao;
    private String tenDangNhap;
    private String matKhau;
    private Boolean trangThai;
    private String quyen;

    @OneToOne
    @JoinColumn
    private NhanVien nhanVien;

    @OneToOne
    @JoinColumn
    private Role role;


    /**
     * @return list permissions user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getChucVu()));
    }

    /**
     * @return password
     */
    @Override
    public String getPassword() {
        return matKhau;
    }

    /**
     * @return username
     */
    @Override
    public String getUsername() {
        return tenDangNhap;
    }

    /**
     * @return NonExpired
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * @return locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return !this.trangThai;
    }

    /**
     * @return Expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * @return Enabled
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
