package com.ttkt.qlks.service.hoadon;

import com.ttkt.qlks.dto.HoaDonDto;
import com.ttkt.qlks.form.hoadon.CreateHoaDonForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface HoaDonService {
    HoaDonDto create(CreateHoaDonForm form);
    public void delete(Integer maHoaDon);
    Page<HoaDonDto> findByNgayNhanPhong(LocalDateTime ngayNhanPhong, Pageable pageable);
    Page<HoaDonDto> findAll(Pageable pageable);
    HoaDonDto findById(Integer maHoaDon);
}
