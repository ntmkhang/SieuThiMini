package DTO;

public class CTHoaDonDTO {

        private int maHD;        // MaHD
        private int maSP;        // MaSP
        private int soLuong;     // SoLuong
        private double giaBan;   // GiaBan
    
        // Constructor mặc định
        public CTHoaDonDTO() {}
    
        // Constructor đầy đủ
        public CTHoaDonDTO(int maHD, int maSP, int soLuong, double giaBan) {
            this.maHD = maHD;
            this.maSP = maSP;
            this.soLuong = soLuong;
            this.giaBan = giaBan;
        }
    
        // Getter and Setter cho MaHD
        public int getMaHD() {
            return maHD;
        }
    
        public void setMaHD(int maHD) {
            this.maHD = maHD;
        }
    
        // Getter and Setter cho MaSP
        public int getMaSP() {
            return maSP;
        }
    
        public void setMaSP(int maSP) {
            this.maSP = maSP;
        }
    
        // Getter and Setter cho SoLuong
        public int getSoLuong() {
            return soLuong;
        }
    
        public void setSoLuong(int soLuong) {
            this.soLuong = soLuong;
        }
    
        // Getter and Setter cho GiaBan
        public double getGiaBan() {
            return giaBan;
        }
    
        public void setGiaBan(double giaBan) {
            this.giaBan = giaBan;
        }
    
        
}
     

