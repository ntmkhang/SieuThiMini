package DTO;


public class AccountDTO {
    private int MaTK;
    private String TenTK;
    private String MatKhau;
    private int MaQuyen;
    private int MaNV;
    private int IsDeleted;

    public AccountDTO(){
        
    }

    public AccountDTO(int MaTK, String TenTK, String MatKhau, int MaQuyen, int MaNV, int IsDeleted) {
        this.MaTK = MaTK;
        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
        this.MaQuyen = MaQuyen;
        this.MaNV = MaNV;
        this.IsDeleted = IsDeleted;
    }

    public int getMaTK() {
        return MaTK;
    }

    public void setMaTK(int MaTK) {
        this.MaTK = MaTK;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(int MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }

    
    
}
