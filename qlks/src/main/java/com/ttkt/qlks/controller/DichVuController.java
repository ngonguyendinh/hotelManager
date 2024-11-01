package com.ttkt.qlks.controller;

import com.ttkt.qlks.dto.DichVuDto;
import com.ttkt.qlks.form.dichvu.CreateDichVuForm;
import com.ttkt.qlks.service.dichvu.DichVuService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController@AllArgsConstructor
public class DichVuController {
    private DichVuService dichVuService;
    @PostMapping("api/v1/eaut/dichvu")
    public DichVuDto create(@RequestBody CreateDichVuForm form){
        return dichVuService.create(form);
    }
    @GetMapping("api/v1/eaut/dichvu/{LDV}")
    public Page<DichVuDto> findByLoaiDichVu(@PathVariable("LDV") String loaiDichVu, Pageable pageable){
        return dichVuService.findByLoaiDichVu(loaiDichVu,pageable);
    }
    @DeleteMapping("api/v1/eaut/dichvu/{mdv}")
    public void deleteById(@PathVariable("mdv") Integer mdv){
        dichVuService.delete(mdv);
    }
}
