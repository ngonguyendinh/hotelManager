package com.ttkt.qlks.form.khachhang;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Setter
@Getter
public class CreateKhachHangForm {
    @NotBlank(message = "tên không được để trống")
    @Length(message = "tên chỉ được chứa 50 ký tự",max = 50)
    private String tenKhachHang;
    @NotBlank(message = "địa chỉ không được để trống")
    @Length(message = "địa chỉ chỉ được chứa 150 ký tự",max = 50)
    private String diaChi;
    @NotBlank(message = "email không được để trống")
    @Length(message = "email chỉ được chứa 50 ký tự",max = 50)
    @Email
    private String email;
    @NotBlank(message = "sdt không được để trống")
    @Length(message = "sdt chỉ được chứa 10 chữ số",max = 10)
    @Digits(integer = 10,fraction = 0,message = "chỉ được viết chữ số")
    private String sdt;

    @NotBlank(message = "CCCD không được để trống")
    @Length(message = "CCCD chỉ được chứa 12 chữ số",max = 12)
    @Digits(integer = 12,fraction = 0,message = "chỉ được viết chữ số")
    private String cccd;
}
