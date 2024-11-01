package com.ttkt.qlks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter@Getter@Entity@Table(name = "hoaDon")
public class HoaDon {
    @Id@Column(name = "ma_hoa_don")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maHoaDon;

    @Column(name = "ngay_tra_phong",nullable = false)
    private LocalDateTime thoiGianTraPhong;
    @Column(name ="tong_tien",nullable = false)
    private float TongTien;

    @OneToOne
    @JoinColumn(name = "ma_dat_phong", referencedColumnName = "ma_dat_phong")
    private DatPhong datPhong;

    @ManyToOne
    @JoinColumn(name = "ma_thanh_toan",referencedColumnName = "ma_thanh_toan")
    private ThanhToan thanhToan;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien",referencedColumnName = "ma_nhan_vien")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private NhanVien nhanVien;

    @ManyToMany(fetch = FetchType.EAGER)@JoinTable(name = "hoa_don_dich_vu",
            joinColumns = @JoinColumn(name="ma_hoa_don"),
            inverseJoinColumns = @JoinColumn(name="ma_dich_vu")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<DichVu> dichVus;

    public void setThanhToan(ThanhToan.LoaiThanhToan loaiThanhToan) {
    }
}
