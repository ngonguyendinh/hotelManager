package com.ttkt.qlks.controller;

import com.ttkt.qlks.dto.HoaDonDto;
import com.ttkt.qlks.form.hoadon.CreateHoaDonForm;
import com.ttkt.qlks.service.hoadon.HoaDonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController@AllArgsConstructor
public class HoaDonController {
    private HoaDonService hoaDonService;
    @PostMapping("api/v1/eaut/hoadon")
    public HoaDonDto create(@RequestBody CreateHoaDonForm form){
        return hoaDonService.create(form);
    }
    @GetMapping("api/v1/eaut/hoadon")
    public Page<HoaDonDto> findAll(Pageable pageable){
        return hoaDonService.findAll(pageable);
    }
    @GetMapping("api/v1/eaut/hoadon/{maHoaDon}")
    public HoaDonDto findById(@PathVariable("maHoaDon") Integer maHoaDon){
        return hoaDonService.findById(maHoaDon);
    }
}
