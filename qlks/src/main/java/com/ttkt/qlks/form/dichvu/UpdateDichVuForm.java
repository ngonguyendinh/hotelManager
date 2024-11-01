package com.ttkt.qlks.form.dichvu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class UpdateDichVuForm {
    @NotBlank(message = "ten dich vu khong duoc de trong")
    private String tenDichVu;
    @NotBlank(message = "gia dich vu khong duoc de trong")
    private float giaDichVu;
    @Pattern(regexp = "DOAN|DOUONG",message = "loai dich vu chi co do an va do uong")
    private String loaiDichVu;
}
