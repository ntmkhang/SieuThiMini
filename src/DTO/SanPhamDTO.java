package DTO;

public class SanPhamDTO {
        private int maSP;
        private String tenSP;
        private String moTa;
        private int soLuong;
        private String hinhAnh;
        private double giaBan;
        private int maLoai;    // là khóa ngoại, nhưng chỉ cần lưu mã ở đây
        private boolean isDeleted;
    
        // Constructor
        public SanPhamDTO(int maSP, String tenSP, String moTa, int soLuong,
                       String hinhAnh, double giaBan, int maLoai, boolean isDeleted) {
            this.maSP = maSP;
            this.tenSP = tenSP;
            this.moTa = moTa;
            this.soLuong = soLuong;
            this.hinhAnh = hinhAnh;
            this.giaBan = giaBan;
            this.maLoai = maLoai;
            this.isDeleted = isDeleted;
        }

        public SanPhamDTO(){
            
        };
    
        // Getters & Setters
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
    
        public String getMoTa() {
            return moTa;
        }
    
        public void setMoTa(String moTa) {
            this.moTa = moTa;
        }
    
        public int getSoLuong() {
            return soLuong;
        }
    
        public void setSoLuong(int soLuong) {
            this.soLuong = soLuong;
        }
    
        public String getHinhAnh() {
            return hinhAnh;
        }
    
        public void setHinhAnh(String hinhAnh) {
            this.hinhAnh = hinhAnh;
        }
    
        public double getGiaBan() {
            return giaBan;
        }
    
        public void setGiaBan(double giaBan) {
            this.giaBan = giaBan;
        }
    
        public int getMaLoai() {
            return maLoai;
        }
    
        public void setMaLoai(int maLoai) {
            this.maLoai = maLoai;
        }
    
        public boolean isDeleted() {
            return isDeleted;
        }
    
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    
    
}
