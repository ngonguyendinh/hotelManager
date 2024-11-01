package com.ttkt.qlks;

import com.ttkt.qlks.entity.NhanVien;
import com.ttkt.qlks.repository.NhanVienRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication@AllArgsConstructor
public class QlksApplication implements CommandLineRunner {
	private PasswordEncoder passwordEncoder;
	private NhanVienRepository nhanVienRepository;
	public static void main(String[] args) {
		SpringApplication.run(QlksApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		var nhanVien = new NhanVien();
		nhanVien.setTenNhanVien("Ngọ");
		nhanVien.setEmail("ngo@gmail.com");
		nhanVien.setSdt("0123456789");
		var passend =passwordEncoder.encode("admin");
		nhanVien.setPassword(passend);
		nhanVien.setDiaChi("Vĩnh Phúc");
		nhanVien.setChucVu(String.valueOf(NhanVien.ChucVu.QUANLY));
		nhanVienRepository.save(nhanVien);
	}
}
