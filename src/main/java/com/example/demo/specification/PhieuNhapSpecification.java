package com.example.demo.specification;

import com.example.demo.entities.PhieuNhapKho;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapSpecification {
    public static Specification<PhieuNhapKho> filter(Integer maNhanVien, Integer thang, Integer nam, Integer maNhaCungCap) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo nhân viên
            if (maNhanVien != null) {
                predicates.add(criteriaBuilder.equal(root.get("nhanVien").get("ma"), maNhanVien));
            }

            // Lọc theo nhà cung cấp
            if (maNhaCungCap != null) {
                predicates.add(criteriaBuilder.equal(root.get("nhaCungCap").get("ma"), maNhaCungCap));
            }

            // Lọc theo tháng và năm
            if (thang != null && nam != null) {
                LocalDateTime start = YearMonth.of(nam, thang).atDay(1).atStartOfDay();
                LocalDateTime end = YearMonth.of(nam, thang).atEndOfMonth().atTime(23, 59, 59);
                predicates.add(criteriaBuilder.between(root.get("thoiGianNhap"), start, end));
            } else if (nam != null) { // Lọc theo năm
                LocalDateTime start = LocalDate.of(nam, 1, 1).atStartOfDay();
                LocalDateTime end = LocalDate.of(nam, 12, 31).atTime(23, 59, 59);
                predicates.add(criteriaBuilder.between(root.get("thoiGianNhap"), start, end));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
