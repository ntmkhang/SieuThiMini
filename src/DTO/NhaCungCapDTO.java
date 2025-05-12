package DTO;

public class NhaCungCapDTO {
    private int MaNCC;
    private String TenNCC;
    private String DiaChi;
    private String SoDT;
    private String NguoiLienHe;
    private int Is_Deleted;

    public NhaCungCapDTO(){

    }

    public int getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(int maNCC) {
        MaNCC = maNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String tenNCC) {
        TenNCC = tenNCC;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }

    public String getNguoiLienHe() {
        return NguoiLienHe;
    }

    public void setNguoiLienHe(String nguoiLienHe) {
        NguoiLienHe = nguoiLienHe;
    }

    public int getIs_Deleted() {
        return Is_Deleted;
    }

    public void setIs_Deleted(int is_Deleted) {
        Is_Deleted = is_Deleted;
    }

    @Override
    public String toString(){
        return TenNCC;
    }
}
