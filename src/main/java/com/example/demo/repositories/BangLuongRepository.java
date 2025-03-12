package com.example.demo.repositories;

import com.example.demo.entities.BangLuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BangLuongRepository extends JpaRepository<BangLuong, Integer>, JpaSpecificationExecutor<BangLuong> {
}
