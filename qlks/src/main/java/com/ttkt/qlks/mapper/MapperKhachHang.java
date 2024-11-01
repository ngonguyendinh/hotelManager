package com.ttkt.qlks.mapper;

import com.ttkt.qlks.dto.KhachHangDto;
import com.ttkt.qlks.entity.KhachHang;
import com.ttkt.qlks.form.khachhang.CreateKhachHangForm;
import com.ttkt.qlks.form.khachhang.UpdateKhachHangForm;

public class MapperKhachHang {
    public static KhachHang map(CreateKhachHangForm form){
        var khachHang = new KhachHang();
        khachHang.setTenKhachHang(form.getTenKhachHang());
        khachHang.setEmail(form.getEmail());
        khachHang.setDiaChi(form.getDiaChi());
        khachHang.setSdt(form.getSdt());
        khachHang.setCccd(form.getCccd());
        return khachHang;
    }
    public static KhachHangDto map(KhachHang khachHang){
        var dto = new KhachHangDto();
        dto.setTenKhachHang(khachHang.getTenKhachHang());
        dto.setDiaChi(khachHang.getDiaChi());
        dto.setEmail(khachHang.getEmail());
        dto.setSdt(khachHang.getSdt());
        dto.setLanDenCuoiCung(khachHang.getLanDenCuoiCung());
        return dto;
    }


    public static void map(UpdateKhachHangForm updateForm, KhachHang khachHang) {
        khachHang.setTenKhachHang(updateForm.getTenKhachHang());
        khachHang.setEmail(updateForm.getEmail());
        khachHang.setDiaChi(updateForm.getDiaChi());
        khachHang.setSdt(updateForm.getSdt());

    }
}
