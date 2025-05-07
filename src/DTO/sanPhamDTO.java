package DTO;


public class sanPhamDTO {
    private int maSP;
    private String tenSP;
    private int maLoai;
    private String moTa;
    private int gia;
    private int soLuong;
    private String hinhAnh;
    private int isDeleted;
    private int thanhTien;

    public sanPhamDTO(int maSP, String tenSP, int maLoai, String moTa, int gia, int soLuong, String hinhAnh,
            int isDeleted) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.moTa = moTa;
        this.gia = gia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.isDeleted = isDeleted;
        this.thanhTien = gia * soLuong;
    }

    public sanPhamDTO() {
    }

    public int getMaSP() {
        return maSP;
    }
    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
    public String getTenSP() {
        return tenSP;
    }
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
        //this.thanhTien = this.gia * this.soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        //this.thanhTien = this.gia * this.soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }
    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    public int getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

   // public int getThanhTien() { return thanhTien; }

    
}
