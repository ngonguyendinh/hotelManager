package com.ttkt.qlks.service.khachhang;

import com.ttkt.qlks.dto.KhachHangDto;
import com.ttkt.qlks.form.khachhang.CreateKhachHangForm;
import com.ttkt.qlks.form.khachhang.FindKH;
import com.ttkt.qlks.form.khachhang.UpdateKhachHangForm;
import com.ttkt.qlks.mapper.MapperKhachHang;
import com.ttkt.qlks.repository.KhachHangRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KhachHangServiceImp implements KhachHangService {
    private KhachHangRepository repository;
    @Override
    public KhachHangDto create(CreateKhachHangForm form) {
        var khachHang = MapperKhachHang.map(form);
        var saveKhachHang = repository.save(khachHang);
        return MapperKhachHang.map(saveKhachHang);
    }

    @Override
    public Page<KhachHangDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(MapperKhachHang::map);
    }

    @Override
    public KhachHangDto findByCccd(FindKH findKH) {
        var khachHang = repository.findByCccd(findKH.getCccd());
        return MapperKhachHang.map(khachHang);
    }


    @Override
    public Page<KhachHangDto> findByTenKhachHangLike(String tenKhachHang, Pageable pageable) {
        return repository.findByTenKhachHangLike(tenKhachHang,pageable).map(MapperKhachHang::map);
    }

    @Override
    public void deleteById(FindKH findKH) {
        var kh = repository.findByCccd(findKH.getCccd());
        repository.deleteById(kh.getMaKhachHang());
    }

    @Override
    public KhachHangDto update(UpdateKhachHangForm updateForm) {
        var khachHang = repository.findByCccd(updateForm.getCccd());
        MapperKhachHang.map(updateForm,khachHang);
        var saveKH = repository.save(khachHang);
        return MapperKhachHang.map(saveKH);
    }


}
