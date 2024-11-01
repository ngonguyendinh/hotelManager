package com.ttkt.qlks.service.dichvu;

import com.ttkt.qlks.dto.DichVuDto;
import com.ttkt.qlks.form.dichvu.CreateDichVuForm;
import com.ttkt.qlks.form.dichvu.UpdateDichVuForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DichVuService {
    DichVuDto create(CreateDichVuForm form);
    Page<DichVuDto> findByLoaiDichVu(String loaiDichVu, Pageable pageable);
    DichVuDto update(UpdateDichVuForm form,Integer maPhong);
    void delete(Integer maPhong);
}
