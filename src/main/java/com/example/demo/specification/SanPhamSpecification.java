package com.example.demo.specification;

import com.example.demo.entities.SanPham;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SanPhamSpecification {
    public static Specification<SanPham> filterSanPham(Integer tonKhoId, Integer cuaHangId, Integer loaiSanPhamId, Integer sizeId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (tonKhoId != null) {
                predicates.add(criteriaBuilder.equal(root.get("tonKho").get("ma"), tonKhoId));
            }
            if (cuaHangId != null) {
                predicates.add(criteriaBuilder.equal(root.get("tonKho").get("cuaHang").get("ma"), cuaHangId));
            }
            if (loaiSanPhamId != null) {
                predicates.add(criteriaBuilder.equal(root.get("loaiSanPham").get("ma"), loaiSanPhamId));
            }
            if (sizeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("size").get("ma"), sizeId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
