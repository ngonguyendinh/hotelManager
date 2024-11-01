package com.ttkt.qlks.service.phong;

import com.ttkt.qlks.dto.PhongDto;
import com.ttkt.qlks.entity.Phong;
import com.ttkt.qlks.form.phong.CreatePhongForm;
import com.ttkt.qlks.form.phong.UpdatePhongForm;
import com.ttkt.qlks.mapper.MapperPhong;
import com.ttkt.qlks.repository.PhongRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service@AllArgsConstructor
public class PhongServiceImp implements PhongService {
    private PhongRepository repository;
    @Override
    public PhongDto create(CreatePhongForm createPhongForm) {
        var phong = MapperPhong.map(createPhongForm);
        phong.setTrangThaiPhong(String.valueOf(Phong.TrangThaiPhong.CON));
        return MapperPhong.map(repository.save(phong));
    }

    @Override
    public Page<PhongDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(MapperPhong::map);
    }

    @Override
    public Page<PhongDto> findByLoaiPhongAndTrangThaiPhong(Phong.LoaiPhong loaiPhong, Phong.TrangThaiPhong trangThaiPhong, Pageable pageable) {
        return repository.findByLoaiPhongAndTrangThaiPhong(loaiPhong,trangThaiPhong,pageable).map(MapperPhong::map);
    }

    @Override
    public PhongDto update(UpdatePhongForm updatePhongForm, Integer maPhong) {
        var phong = repository.findById(maPhong).get();
         MapperPhong.map(updatePhongForm, phong);
        return MapperPhong.map(repository.save(phong));
    }

    @Override
    public void delete(Integer maPhong) {
        repository.deleteById(maPhong);
    }

}
