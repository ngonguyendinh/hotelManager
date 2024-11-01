package com.ttkt.qlks.service.datphong;

import com.ttkt.qlks.dto.DatPhongDto;
import com.ttkt.qlks.entity.Phong;
import com.ttkt.qlks.form.datphong.CreateDatPhongForm;
import com.ttkt.qlks.mapper.MapperDatPhong;
import com.ttkt.qlks.repository.DatPhongRepository;
import com.ttkt.qlks.repository.KhachHangRepository;
import com.ttkt.qlks.repository.PhongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service@AllArgsConstructor
public class DatPhongServiceImp implements DatPhongService{
    private DatPhongRepository datPhongRepository;
    private KhachHangRepository khachHangRepository;
    private PhongRepository phongRepository;
    @Override
    public DatPhongDto create(CreateDatPhongForm form) {
        var datPhong = MapperDatPhong.map(form);
        var kh = khachHangRepository.findByCccd(form.getCccd());
        datPhong.setKhachHang(kh);
        var phong = phongRepository.findBySoPhong(form.getSoPhong());
        datPhong.setPhong(phong);
        phong.setTrangThaiPhong(String.valueOf(Phong.TrangThaiPhong.HET));
        var saveDatPhong = datPhongRepository.save(datPhong);
        return MapperDatPhong.map(saveDatPhong);
    }
}
