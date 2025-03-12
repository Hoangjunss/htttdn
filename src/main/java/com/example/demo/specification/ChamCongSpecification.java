package com.example.demo.specification;

import com.example.demo.entities.ChamCong;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ChamCongSpecification {
    public static Specification<ChamCong> filterChamCong(Integer nhanVienId, Integer cuaHangId, LocalDate ngay) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Tìm theo nhân viên
            if (nhanVienId != null) {
                predicates.add(cb.equal(root.get("nhanVien").get("ma"), nhanVienId));
            }

            // Tìm theo cửa hàng
            if (cuaHangId != null) {
                predicates.add(cb.equal(root.get("nhanVien").get("cuaHang").get("ma"), cuaHangId));
            }

            // Tìm theo ngày (so sánh với `thoiGianVao`)
            if (ngay != null) {
                LocalDateTime startOfDay = ngay.atStartOfDay();
                LocalDateTime endOfDay = ngay.atTime(23, 59, 59);
                predicates.add(cb.between(root.get("thoiGianVao"), startOfDay, endOfDay));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
