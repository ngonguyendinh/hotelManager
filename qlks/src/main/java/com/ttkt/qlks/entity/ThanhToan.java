package com.ttkt.qlks.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;
@Entity
@Setter@Getter
@Table(name = "thanh_toan")
public class ThanhToan {
    @Id@Column(name = "ma_thanh_toan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maThanhToan;

    @Column(name = "loai_thanh_toan",length = 20,nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LoaiThanhToan loaiThanhToan;

    @Column(name = "ngay_thanh_toan",nullable = false,updatable = false)
    @UpdateTimestamp
    private LocalDate ngayThanhToan;

    @OneToMany(mappedBy = "thanhToan")
    private List<HoaDon> hoaDons;
    public void setLoaiThanhToan(String loaiThanhToan) {
        this.loaiThanhToan = LoaiThanhToan.valueOf(loaiThanhToan);
    }

    public enum LoaiThanhToan{
        TIENMAT,CHUYENKHOAN
    }

}
