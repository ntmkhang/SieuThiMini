package DTO;

public class LoaiSanPhamDTO {
        private int maLoai;
        private String tenLoai;
        private String moTa;
        private Boolean isDeleted;
    
        // Constructor
        public LoaiSanPhamDTO(int maLoai, String tenLoai, String moTa, boolean isDeleted) {
            this.maLoai = maLoai;
            this.tenLoai = tenLoai;
            this.moTa = moTa;
            this.isDeleted = isDeleted;
        }
        
        public LoaiSanPhamDTO(){
            
        }
        // Getters and Setters
        public int getMaLoai() {
            return maLoai;
        }
    
        public void setMaLoai(int maLoai) {
            this.maLoai = maLoai;
        }
    
        public String getTenLoai() {
            return tenLoai;
        }
    
        public void setTenLoai(String tenLoai) {
            this.tenLoai = tenLoai;
        }
    
        public String getMoTa() {
            return moTa;
        }
    
        public void setMoTa(String moTa) {
            this.moTa = moTa;
        }
    
        public boolean isDeleted() {
            return isDeleted;
        }
    
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
          
}
