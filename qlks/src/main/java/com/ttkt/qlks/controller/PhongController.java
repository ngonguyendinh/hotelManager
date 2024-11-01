package com.ttkt.qlks.controller;

import com.ttkt.qlks.dto.PhongDto;
import com.ttkt.qlks.entity.Phong;
import com.ttkt.qlks.form.phong.CreatePhongForm;
import com.ttkt.qlks.form.phong.UpdatePhongForm;
import com.ttkt.qlks.service.phong.PhongService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController@AllArgsConstructor
public class PhongController {
    private PhongService service;
    @PostMapping("api/v1/eaut/phong")
    public PhongDto create(@RequestBody@Valid CreatePhongForm form){
        return service.create(form);
    }
    @GetMapping("api/v1/eaut/phong/{loaiPhong}/{trangThaiPhong}")
    public Page<PhongDto> findByLoaiPhongAndTrangThaiPhong(@PathVariable("loaiPhong") Phong.LoaiPhong loaiPhong,@PathVariable("trangThaiPhong") Phong.TrangThaiPhong trangThaiPhong, Pageable pageable){
        return service.findByLoaiPhongAndTrangThaiPhong(loaiPhong,trangThaiPhong,pageable);
    }
    @GetMapping("api/v1/eaut/phong")
    public Page<PhongDto> findAll(Pageable pageable){
        return service.findAll(pageable);
    }
    @PutMapping("api/v1/eaut/phong/{maPhong}")
    public PhongDto update(@RequestBody@Valid UpdatePhongForm form,@PathVariable("maPhong") Integer maPhong){
        return service.update(form,maPhong);
    }
    @DeleteMapping("api/v1/eaut/phong/{maPhong}")
    public void delete(@PathVariable("maPhong") Integer maPhong){
        service.delete(maPhong);
    }
}
