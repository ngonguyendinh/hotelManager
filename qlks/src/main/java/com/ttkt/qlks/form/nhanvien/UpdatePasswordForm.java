package com.ttkt.qlks.form.nhanvien;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter@Getter
public class UpdatePasswordForm {
    private String oldPassword;
    private String newPassword;
}
