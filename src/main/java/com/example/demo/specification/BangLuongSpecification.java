package com.example.demo.specification;

import com.example.demo.entities.BangLuong;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BangLuongSpecification {
    public static Specification<BangLuong> filter(Integer nhanVienId, Integer cuaHangId, Integer thang, Integer nam) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nhanVienId != null) {
                predicates.add(criteriaBuilder.equal(root.get("nhanVien").get("ma"), nhanVienId));
            }
            if (cuaHangId != null) {
                predicates.add(criteriaBuilder.equal(root.get("nhanVien").get("cuaHang").get("ma"), cuaHangId));
            }
            if (thang != null) {
                predicates.add(criteriaBuilder.equal(root.get("thangTinhLuong"), thang));
            }
            if (nam != null) {
                predicates.add(criteriaBuilder.equal(root.get("namTinhluong"), nam));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
