package com.ttkt.qlks.service.nhanvien;

import com.ttkt.qlks.dto.NhanVienDto;
import com.ttkt.qlks.entity.NhanVien;
import com.ttkt.qlks.form.nhanvien.CreateNhanVienForm;
import com.ttkt.qlks.form.nhanvien.UpdateNhanVienForm;
import com.ttkt.qlks.form.nhanvien.UpdatePasswordForm;
import com.ttkt.qlks.mapper.MapperNhanVien;
import com.ttkt.qlks.repository.NhanVienRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service@AllArgsConstructor
public class NhanVienServiceImp implements NhanVienService, UserDetailsService {
    private NhanVienRepository nhanVienRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public NhanVienDto create(CreateNhanVienForm form) {
        var nhanVien = MapperNhanVien.map(form);
        var passwordEnd = passwordEncoder.encode(form.getPassword());
        nhanVien.setPassword(passwordEnd);
        nhanVien.setChucVu(String.valueOf(NhanVien.ChucVu.NHANVIEN));
        var save = nhanVienRepository.save(nhanVien);
        return MapperNhanVien.map(save);
    }

    @Override
    public void delete(Integer maNhanVien) {
        nhanVienRepository.deleteById(maNhanVien);
    }

    @Override
    public Page<NhanVienDto> findAll(Pageable pageable) {
        return nhanVienRepository.findAll(pageable).map(MapperNhanVien::map);
    }

    @Override
    public Page<NhanVienDto> findByTenNhanVienLike(String tenNhanVien, Pageable pageable) {
        return nhanVienRepository.findByTenNhanVienLike(tenNhanVien,pageable).map(MapperNhanVien::map);
    }

    @Override
    public NhanVienDto update(UpdateNhanVienForm form,Integer maNhanVien) {
        var nhanVien =nhanVienRepository.findById(maNhanVien).get();
        MapperNhanVien.map(nhanVien,form);
        var saveNhanVien = nhanVienRepository.save(nhanVien);
        return MapperNhanVien.map(saveNhanVien);
    }

    @Override
    public NhanVienDto updatePassword(UpdatePasswordForm form, Integer maNhanVien) {
        var nhanVien = nhanVienRepository.findById(maNhanVien).get();
        if(nhanVien.getPassword().equals(passwordEncoder.encode(form.getOldPassword()))){
            nhanVien.setPassword(passwordEncoder.encode(form.getNewPassword()));
            nhanVienRepository.save(nhanVien);
        }
        return MapperNhanVien.map(nhanVien);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var nhanVien = nhanVienRepository.findByEmail(email);
        if(nhanVien == null){
            throw new UsernameNotFoundException(email);
        }
        var authorities = new ArrayList<GrantedAuthority>();
        var role = nhanVien.getChucVu();
        var authority = new SimpleGrantedAuthority(role.toString());
        authorities.add(authority);
        return new User(email, nhanVien.getPassword(), authorities);
    }
}
