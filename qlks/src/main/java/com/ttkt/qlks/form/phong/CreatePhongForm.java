package com.ttkt.qlks.form.phong;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class CreatePhongForm {
    private int soPhong;
    private float giaPhong;
    @Pattern(regexp = "VIP|THUONG")
    private String loaiPhong;

}
