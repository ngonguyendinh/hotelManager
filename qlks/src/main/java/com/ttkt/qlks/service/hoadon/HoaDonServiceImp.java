package com.ttkt.qlks.service.hoadon;

import com.ttkt.qlks.dto.HoaDonDto;
import com.ttkt.qlks.entity.DichVu;
import com.ttkt.qlks.form.hoadon.CreateHoaDonForm;
import com.ttkt.qlks.mapper.MapperHoaDon;
import com.ttkt.qlks.repository.DatPhongRepository;
import com.ttkt.qlks.repository.DichVuRepository;
import com.ttkt.qlks.repository.HoaDonRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service@AllArgsConstructor
public class HoaDonServiceImp implements HoaDonService{
    private HoaDonRepository hoaDonRepository;
    private DichVuRepository dichVuRepository;
    private DatPhongRepository datPhongRepository;
    @Override
    public HoaDonDto create(CreateHoaDonForm form) {
        float tongTien = 0;
        var hoaDon = MapperHoaDon.map(form);
         var maDichVu = form.getMaDichVu();
        List<DichVu> dichVus = new ArrayList<>();
        List<String> tenDichVuList = new ArrayList<>();
        //lay ra dich vu su dung va tinh tien
        for (Integer mdv : maDichVu) {
            var dichVu = dichVuRepository.findByMaDichVu(mdv);
            dichVus.add(dichVu);
            tenDichVuList.add(dichVu.getTenDichVu());
            tongTien +=dichVu.getGiaDichVu();
        }
        var datphong =datPhongRepository.findByMaDatPhong(form.getMaDatPhong());
        var thoiGianNhan = datphong.getThoiGianDat();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");
        var thoiGianTra= LocalDateTime.parse(form.getNgayTraPhong(),dinhDang);
        hoaDon.setThoiGianTraPhong(thoiGianTra);
        //lấy ra thời gian sử dụng
        var gioSuDung = ChronoUnit.HOURS.between(thoiGianNhan,thoiGianTra);
        var phutSuDung = ChronoUnit.MINUTES.between(thoiGianNhan,thoiGianTra)%60;
        tongTien +=(datphong.getPhong().getGiaPhong()/60)*phutSuDung;
        tongTien += (gioSuDung*(datphong.getPhong().getGiaPhong()));// tổng tiền phải thanh toán
        hoaDon.setTongTien(tongTien);
        datphong.getKhachHang().setLanDenCuoiCung(thoiGianTra);
        hoaDon.setDichVus(dichVus);
        hoaDon.setDatPhong(datphong);
        var save= hoaDonRepository.save(hoaDon);
        var dto = MapperHoaDon.map(save);
        dto.setThoiGianSuDung(gioSuDung+":"+phutSuDung);
        dto.setTenDichVu(tenDichVuList);
        return  dto;
    }

    @Override
    public Page<HoaDonDto> findAll(Pageable pageable) {
        return hoaDonRepository.findAll(pageable).map(MapperHoaDon::map);
    }

    @Override
    public HoaDonDto findById(Integer maHoaDon) {
        var hoaDon = hoaDonRepository.findByMaHoaDon(maHoaDon);
        var dichvu = hoaDon.getDichVus();
        List<String> tenDichVuList = new ArrayList<>();
        for (DichVu dichVu : dichvu) {
            tenDichVuList.add(dichVu.getTenDichVu());
        }
        var thoiGianNhan = hoaDon.getDatPhong().getThoiGianDat();
        var thoiGianTra = hoaDon.getThoiGianTraPhong();
        // thời gian sử dụng
        var gioSuDung = ChronoUnit.HOURS.between(thoiGianNhan,thoiGianTra);
        var phutSuDung = ChronoUnit.MINUTES.between(thoiGianNhan,thoiGianTra)%60;
        var dto = MapperHoaDon.map(hoaDon);
        dto.setTenDichVu(tenDichVuList);
        dto.setThoiGianSuDung(gioSuDung+":"+phutSuDung);
        return dto;
    }

    @Override
    public Page<HoaDonDto> findByNgayNhanPhong(LocalDateTime ngayNhanPhong, Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Integer maHoaDon) {
        hoaDonRepository.deleteById(maHoaDon);
    }
}
