package com.ttkt.qlks.controller;

import com.ttkt.qlks.dto.NhanVienDto;
import com.ttkt.qlks.form.nhanvien.CreateNhanVienForm;
import com.ttkt.qlks.service.nhanvien.NhanVienService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController@AllArgsConstructor
public class NhanVienController {
    private NhanVienService nhanVienService;
    @PostMapping("api/v1/eaut/nhanvien")
    public NhanVienDto create(@RequestBody CreateNhanVienForm form){
        return nhanVienService.create(form);
    }
    @GetMapping("api/v1/eaut/nhanvien")
    public Page<NhanVienDto> findAll(Pageable pageable){
        return nhanVienService.findAll(pageable);
    }
    @GetMapping("api/v1/eaut/nhanvien/tenNV")
    public Page<NhanVienDto> findByTenNhanVienLike(@PathVariable("tenNV")String tenNhanVien, Pageable pageable){
        return nhanVienService.findByTenNhanVienLike(tenNhanVien,pageable);
    }
}
