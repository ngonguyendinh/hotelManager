package com.ttkt.qlks.service.datphong;

import com.ttkt.qlks.dto.DatPhongDto;
import com.ttkt.qlks.form.datphong.CreateDatPhongForm;

public interface DatPhongService {
    DatPhongDto create(CreateDatPhongForm form);
}
