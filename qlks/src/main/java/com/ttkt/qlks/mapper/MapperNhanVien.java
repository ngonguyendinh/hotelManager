package com.ttkt.qlks.mapper;

import com.ttkt.qlks.dto.NhanVienDto;
import com.ttkt.qlks.entity.NhanVien;
import com.ttkt.qlks.form.nhanvien.CreateNhanVienForm;
import com.ttkt.qlks.form.nhanvien.UpdateNhanVienForm;

public class MapperNhanVien {
    public static NhanVien map(CreateNhanVienForm form){
        var nhanVien = new NhanVien();
        nhanVien.setTenNhanVien(form.getTenNhanVien());
        nhanVien.setEmail(form.getEmail());
        nhanVien.setSdt(form.getSdt());
        nhanVien.setDiaChi(form.getDiaChi());
        return nhanVien;
    }
    public static NhanVienDto map(NhanVien nhanVien){
        var dto = new NhanVienDto();
        dto.setTenNhanVien(nhanVien.getTenNhanVien());
        dto.setSdt(nhanVien.getSdt());
        dto.setEmail(nhanVien.getEmail());
        dto.setDiaChi(nhanVien.getDiaChi());
        dto.setChucVu(nhanVien.getChucVu());
        return dto;
    }
    public static void map(NhanVien nhanVien, UpdateNhanVienForm form){
        nhanVien.setTenNhanVien(form.getTenNhanVien());
        nhanVien.setEmail(form.getEmail());
        nhanVien.setDiaChi(form.getDiaChi());
        nhanVien.setSdt(form.getSdt());
    }
}
