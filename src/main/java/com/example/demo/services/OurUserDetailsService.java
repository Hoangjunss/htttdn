package com.example.demo.services;

import com.example.demo.repositories.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OurUserDetailsService implements UserDetailsService {
    private  final TaiKhoanRepository taiKhoanRepository;

    @Autowired
    public OurUserDetailsService(TaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }


    /**
     * @param username the username identifying the user whose data is required.
     * @return username
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return taiKhoanRepository.findByTenDangNhap(username)
                .orElseThrow(()-> new UsernameNotFoundException("Account not found with username: " + username));
    }
}
