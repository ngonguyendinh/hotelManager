package com.ttkt.qlks.service.dichvu;

import com.ttkt.qlks.dto.DichVuDto;
import com.ttkt.qlks.entity.DichVu;
import com.ttkt.qlks.form.dichvu.CreateDichVuForm;
import com.ttkt.qlks.form.dichvu.UpdateDichVuForm;
import com.ttkt.qlks.mapper.MapperDichVu;
import com.ttkt.qlks.repository.DichVuRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DichVuServiceImp implements DichVuService{
    private DichVuRepository repository;

    @Override
    public DichVuDto create(CreateDichVuForm form) {
        var dichVu = MapperDichVu.map(form);
        var saveDV = repository.save(dichVu);
        return MapperDichVu.map(saveDV);
    }

    @Override
    public Page<DichVuDto> findByLoaiDichVu(String loaiDichVu, Pageable pageable) {
        return repository.findByLoaiDichVu(DichVu.LoaiDichVu.valueOf(loaiDichVu),pageable).map(MapperDichVu::map);
    }

    @Override
    public DichVuDto update(UpdateDichVuForm form,Integer maDichVu) {
        var phong = repository.findById(maDichVu).get();
        MapperDichVu.map(form,phong);
        return MapperDichVu.map(repository.save(phong));
    }

    @Override
    public void delete(Integer maDichVu) {
        repository.deleteById(maDichVu);
    }

}
