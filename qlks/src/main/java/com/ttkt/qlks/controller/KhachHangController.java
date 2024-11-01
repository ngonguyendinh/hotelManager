package com.ttkt.qlks.controller;

import com.ttkt.qlks.dto.KhachHangDto;
import com.ttkt.qlks.form.khachhang.CreateKhachHangForm;
import com.ttkt.qlks.form.khachhang.FindKH;
import com.ttkt.qlks.form.khachhang.UpdateKhachHangForm;
import com.ttkt.qlks.service.khachhang.KhachHangService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class KhachHangController {
    private KhachHangService service;
    @GetMapping("api/v1/eaut/khachhang")
    public Page<KhachHangDto> findAll(Pageable pageable){
        return service.findAll(pageable);
    }
    @PostMapping("api/v1/eaut/khachhang")
     KhachHangDto create(@RequestBody@Valid CreateKhachHangForm form){
        return service.create(form);
    }
    @GetMapping("api/v1/eaut/khachhang/{tenkh}")
    Page<KhachHangDto> findByTenKhachHangLike(@PathVariable("tenkh") String tenKhachHang, Pageable pageable){
        return service.findByTenKhachHangLike(tenKhachHang,pageable);
    }
    @PutMapping("api/v1/eaut/khachhang")
    KhachHangDto update(@RequestBody UpdateKhachHangForm updateForm){
        return service.update(updateForm);
    }
    @PostMapping("api/v1/eaut/khachhang/cccd")
    KhachHangDto findByCccd(@RequestBody FindKH findKH){
        return service.findByCccd(findKH);
    }
    @DeleteMapping("api/v1/eaut/khachhang")
    void delete(@RequestBody FindKH findKH){
        service.deleteById(findKH);
    }

}
