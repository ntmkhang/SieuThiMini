package DTO;

import java.time.LocalDate;


public class HoaDonDTO {
    private int maHD;
    private LocalDate ngayLap;
    private String hinhThuc;
    private double tongTien;
    // private double tienGiam;
    private double thanhTien;
    private double tienKhachDua;
    private double tienTraLai;
    private int maNV;
    private int maKH;
    private boolean isDeleted;

    // Constructors
    public HoaDonDTO() {}

    public HoaDonDTO(int maHD, LocalDate ngayLap, String hinhThuc, double tongTien,
                    double thanhTien, double tienKhachDua,
                    double tienTraLai, int maNV, int maKH, boolean isDeleted) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.hinhThuc = hinhThuc;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.tienKhachDua = tienKhachDua;
        this.tienTraLai = tienTraLai;
        this.maNV = maNV;
        this.maKH = maKH;
        // this.isDeleted = isDeleted;
    }

    // Getters & Setters
    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(double tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public double getTienTraLai() {
        return tienTraLai;
    }

    public void setTienTraLai(double tienTraLai) {
        this.tienTraLai = tienTraLai;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

