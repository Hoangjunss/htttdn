package com.example.demo.specification;

import com.example.demo.entities.DonHang;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DonHangSpecification {
    public static Specification<DonHang> filter(Integer maNhanVien, Integer ngay, Integer thang, Integer nam) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (maNhanVien != null) {
                predicates.add(criteriaBuilder.equal(root.get("nhanVien").get("ma"), maNhanVien));
            }

            if (ngay != null && thang != null && nam != null) {
                LocalDateTime start = LocalDate.of(nam, thang, ngay).atStartOfDay();
                LocalDateTime end = start.plusDays(1).minusSeconds(1);
                predicates.add(criteriaBuilder.between(root.get("thoiGianBan"), start, end));
            } else if (thang != null && nam != null) {
                LocalDateTime start = YearMonth.of(nam, thang).atDay(1).atStartOfDay();
                LocalDateTime end = YearMonth.of(nam, thang).atEndOfMonth().atTime(23, 59, 59);
                predicates.add(criteriaBuilder.between(root.get("thoiGianBan"), start, end));
            } else if (nam != null) {
                LocalDateTime start = LocalDate.of(nam, 1, 1).atStartOfDay();
                LocalDateTime end = LocalDate.of(nam, 12, 31).atTime(23, 59, 59);
                predicates.add(criteriaBuilder.between(root.get("thoiGianBan"), start, end));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
