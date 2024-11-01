package com.ttkt.qlks.service.nhanvien;

import com.ttkt.qlks.dto.NhanVienDto;
import com.ttkt.qlks.form.nhanvien.CreateNhanVienForm;
import com.ttkt.qlks.form.nhanvien.UpdateNhanVienForm;
import com.ttkt.qlks.form.nhanvien.UpdatePasswordForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NhanVienService {
    NhanVienDto create(CreateNhanVienForm form);
    Page<NhanVienDto> findAll(Pageable pageable);
    Page<NhanVienDto> findByTenNhanVienLike(String tenNhanVien, Pageable pageable);
    void delete(Integer maNhanVien);
    NhanVienDto update(UpdateNhanVienForm form, Integer maNhanVien);
    NhanVienDto updatePassword(UpdatePasswordForm form, Integer maNhanVien);
}
