package com.ttkt.qlks.service.phong;

import com.ttkt.qlks.dto.PhongDto;
import com.ttkt.qlks.entity.Phong;
import com.ttkt.qlks.form.phong.CreatePhongForm;
import com.ttkt.qlks.form.phong.UpdatePhongForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhongService {
    PhongDto create(CreatePhongForm createPhongForm);
    Page<PhongDto> findAll(Pageable pageable);
    Page<PhongDto> findByLoaiPhongAndTrangThaiPhong(Phong.LoaiPhong loaiPhong, Phong.TrangThaiPhong trangThaiPhong, Pageable pageable);
    PhongDto update(UpdatePhongForm updatePhongForm, Integer maPhong);
    void delete(Integer maPhong);
}
