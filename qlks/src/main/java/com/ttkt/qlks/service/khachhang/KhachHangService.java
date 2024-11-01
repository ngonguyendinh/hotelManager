package com.ttkt.qlks.service.khachhang;

import com.ttkt.qlks.dto.KhachHangDto;
import com.ttkt.qlks.form.khachhang.CreateKhachHangForm;
import com.ttkt.qlks.form.khachhang.FindKH;
import com.ttkt.qlks.form.khachhang.UpdateKhachHangForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface KhachHangService {
    KhachHangDto create (CreateKhachHangForm form);
    Page<KhachHangDto> findByTenKhachHangLike(String tenKhachHang, Pageable pageable);
    void deleteById(FindKH findKH);
    KhachHangDto update(UpdateKhachHangForm updateForm);
    Page<KhachHangDto> findAll(Pageable pageable);
    KhachHangDto findByCccd(FindKH findKH);

}
