package DTO;

import java.sql.Timestamp;

public class PhieuNhapDTO {
    private int MaPN;
    private Timestamp NgayLap;
    private int MaNV;
    private int MaNCC;
    private String TrangThai;
    private int Is_Deleted;
    
    public PhieuNhapDTO(){
        
    }

    public int getMaPN() {
        return MaPN;
    }

    public void setMaPN(int MaPN) {
        this.MaPN = MaPN;
    }

    public Timestamp getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Timestamp NgayLap) {
        this.NgayLap = NgayLap;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public int getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(int MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getIs_Deleted() {
        return Is_Deleted;
    }

    public void setIs_Deleted(int Is_Deleted) {
        this.Is_Deleted = Is_Deleted;
    }

    public class tablePNDTO {
        private int MaPN;
        private Timestamp NgayLap;
        private String TenNV;
        private String TenNCC;
        private String TrangThai;
        private int Is_Deleted;

        public tablePNDTO(){

        }

        public int getMaPN() {
            return MaPN;
        }

        public void setMaPN(int MaPN) {
            this.MaPN = MaPN;
        }

        public Timestamp getNgayLap() {
            return NgayLap;
        }

        public void setNgayLap(Timestamp NgayLap) {
            this.NgayLap = NgayLap;
        }

        public String getTenNV() {
            return TenNV;
        }

        public void setTenNV(String TenNV) {
            this.TenNV = TenNV;
        }

        public String getTenNCC() {
            return TenNCC;
        }

        public void setTenNCC(String TenNCC) {
            this.TenNCC = TenNCC;
        }

        public String getTrangThai() {
            return TrangThai;
        }

        public void setTrangThai(String TrangThai) {
            this.TrangThai = TrangThai;
        }

        public int getIs_Deleted() {
            return Is_Deleted;
        }

        public void setIs_Deleted(int Is_Deleted) {
            this.Is_Deleted = Is_Deleted;
        }

    }

    public class tableCTPNDTO{
        private String TenSP;
        private int SoLuong;
        private int GiaNhap;
        private int ThanhTien;

        public tableCTPNDTO(){

        }

        public String getTenSP() {
            return TenSP;
        }

        public void setTenSP(String TenSP) {
            this.TenSP = TenSP;
        }

        public int getSoLuong() {
            return SoLuong;
        }

        public void setSoLuong(int SoLuong) {
            this.SoLuong = SoLuong;
        }

        public int getGiaNhap() {
            return GiaNhap;
        }

        public void setGiaNhap(int GiaNhap) {
            this.GiaNhap = GiaNhap;
        }

        public int getThanhTien() {
            return ThanhTien;
        }

        public void setThanhTien(int ThanhTien) {
            this.ThanhTien = ThanhTien;
        }

    }

    public class tableSPChon{
        private int MaSP;
        private String TenSP;
        private int SoLuong;
        private int GiaNhap;

        public tableSPChon(){

        }

        public int getMaSP() {
            return MaSP;
        }

        public void setMaSP(int MaSP) {
            this.MaSP = MaSP;
        }

        public String getTenSP() {
            return TenSP;
        }

        public void setTenSP(String TenSP) {
            this.TenSP = TenSP;
        }

        public int getSoLuong() {
            return SoLuong;
        }

        public void setSoLuong(int SoLuong) {
            this.SoLuong = SoLuong;
        }

        public int getGiaNhap() {
            return GiaNhap;
        }

        public void setGiaNhap(int GiaNhap) {
            this.GiaNhap = GiaNhap;
        }


    }


}
