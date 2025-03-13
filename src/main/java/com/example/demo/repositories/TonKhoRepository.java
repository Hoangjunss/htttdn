package com.example.demo.repositories;

import com.example.demo.entities.Kho;
import com.example.demo.entities.SanPham;
import com.example.demo.entities.TonKho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TonKhoRepository extends JpaRepository<TonKho,Integer> {

    @Override
    Optional<TonKho> findById(Integer integer);

    TonKho findTonKhoByKhoAndSanPham(Kho kho, SanPham sanPham);

    List<TonKho> findTonKhoByKho(Kho kho);
}
