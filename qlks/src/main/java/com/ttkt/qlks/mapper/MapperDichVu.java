package com.ttkt.qlks.mapper;

import com.ttkt.qlks.dto.DichVuDto;
import com.ttkt.qlks.entity.DichVu;
import com.ttkt.qlks.form.dichvu.CreateDichVuForm;
import com.ttkt.qlks.form.dichvu.UpdateDichVuForm;

public class MapperDichVu {
    public static DichVu map(CreateDichVuForm form){
        var dichVu = new DichVu();
        dichVu.setTenDichVu(form.getTenDichVu());
        dichVu.setGiaDichVu(form.getGiaDichVu());
        dichVu.setLoaiDichVu(form.getLoaiDichVu());
        return dichVu;
    }
    public static DichVuDto map(DichVu dichVu){
        var dichVuDto = new DichVuDto();
        dichVuDto.setTenDichVu(dichVu.getTenDichVu());
        dichVuDto.setGiaDichVu(dichVu.getGiaDichVu());
        dichVuDto.setLoaiDichVu(dichVu.getLoaiDichVu());
        return dichVuDto;
    }
    public static void map(UpdateDichVuForm form, DichVu dichVu){
        dichVu.setTenDichVu(form.getTenDichVu());
        dichVu.setGiaDichVu(form.getGiaDichVu());
        dichVu.setLoaiDichVu(form.getLoaiDichVu());
    }
}
