package com.ttkt.qlks.specification;

import com.ttkt.qlks.entity.NhanVien;
import com.ttkt.qlks.form.nhanvien.NhanVienFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import java.util.LinkedList;
import jakarta.persistence.criteria.Predicate;

public class NhanVienSpecification {
    public static Specification<NhanVien> buildSpec(NhanVienFilterForm form){
        return form == null ? null : (root, query, builder) -> {
            var predicates = new LinkedList<Predicate>();
            var search = form.getSreach();
            if(StringUtils.hasText(search)){
                var pattern = "%"+search.trim()+"%";
                var predicate = builder.like(root.get("tenNhanVien"),pattern);
                predicates.add(predicate);
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
