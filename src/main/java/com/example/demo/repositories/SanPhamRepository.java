package com.example.demo.repositories;

import com.example.demo.entities.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanPhamRepository extends JpaRepository<SanPham,Integer> {
    Page<SanPham> findAll(Specification<SanPham> spec, Pageable pageable);
}
