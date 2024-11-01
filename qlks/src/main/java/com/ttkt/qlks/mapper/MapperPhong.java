package com.ttkt.qlks.mapper;

import com.ttkt.qlks.dto.PhongDto;
import com.ttkt.qlks.entity.Phong;
import com.ttkt.qlks.form.phong.CreatePhongForm;
import com.ttkt.qlks.form.phong.UpdatePhongForm;

public class MapperPhong {
    public static Phong map(CreatePhongForm createPhongForm){
        var phong = new Phong();
        phong.setSoPhong(createPhongForm.getSoPhong());
        phong.setLoaiPhong(createPhongForm.getLoaiPhong());
        phong.setGiaPhong(createPhongForm.getGiaPhong());
        return phong;
    }
    public static PhongDto map(Phong phong){
        var phongDto = new PhongDto();
        phongDto.setSoPhong(phong.getSoPhong());
        phongDto.setLoaiPhong(phong.getLoaiPhong());
        phongDto.setTrangThaiPhong(phong.getTrangThaiPhong());
        phongDto.setGiaPhong(phong.getGiaPhong());
        return phongDto;
    }
    public static Phong map(UpdatePhongForm updatePhongForm, Phong phong){
        phong.setSoPhong(updatePhongForm.getSoPhong());
        phong.setLoaiPhong(updatePhongForm.getLoaiPhong());
        phong.setTrangThaiPhong(updatePhongForm.getTrangThaiPhong());
        phong.setGiaPhong(updatePhongForm.getGiaPhong());
        return phong;
    }
}
