package com.ttkt.qlks.controller;

import com.ttkt.qlks.dto.DatPhongDto;
import com.ttkt.qlks.form.datphong.CreateDatPhongForm;
import com.ttkt.qlks.service.datphong.DatPhongService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController@AllArgsConstructor
public class DatPhongController {
    private DatPhongService datPhongService;
    @PostMapping("api/v1/eaut/datphong")
    public DatPhongDto create(@RequestBody CreateDatPhongForm form){return datPhongService.create(form);}
}
