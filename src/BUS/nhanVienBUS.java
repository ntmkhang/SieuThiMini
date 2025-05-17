package BUS;

import java.util.ArrayList;

import DAO.nhanVienDAO;
import DTO.nhanVienDTO;

public class nhanVienBUS {
    private nhanVienDAO nhanVienDAO;

    public nhanVienBUS (){
        nhanVienDAO = new nhanVienDAO();
    }

    public ArrayList<nhanVienDTO> getAllNhanVien (){
        return nhanVienDAO.getAllNhanVien();
    }

    public nhanVienDTO getNhanVienByMaNV (int maNV){
        return nhanVienDAO.getNhanVienbyMaNV(maNV);
    }

    public String getTenNVbyMaNV (int maNV){
        return nhanVienDAO.getTenNhanVienbyMaNV(maNV);
    }

     public boolean getThemNV(nhanVienDTO nhanvien){
        if(nhanVienDAO.KTraNhanVienTonTai(nhanvien.getMaNV())){
            return false;
        }
        return nhanVienDAO.ThemNV(nhanvien);
    }

    public boolean getXoaNV(int MaNV){
        return nhanVienDAO.XoaNV(MaNV);
    }

    public boolean getSuaNV(nhanVienDTO nhanvien){
        return nhanVienDAO.SuaNV(nhanvien);
    }

    public Integer getMaNV(){
        return nhanVienDAO.getMaNV();
    }

    public boolean getKTraNhanVienTonTai(int MaNV){
        return nhanVienDAO.KTraNhanVienTonTai(MaNV);
    }

    public nhanVienDTO getNVBangMaNV(int MaNV){
        return nhanVienDAO.getNVBangMaNV(MaNV);
    }

    public ArrayList<nhanVienDTO> getAllNV(){
        return nhanVienDAO.getAllNV();
    }

    public ArrayList<nhanVienDTO> getTKiemNVTheoTen(String keyword){
        return nhanVienDAO.TKiemNhanVienTheoTen(keyword);
    }

    public boolean kiemTraSoDienThoai(String soDienThoai){
        return nhanVienDAO.kiemTraSoDienThoai(soDienThoai);
    }

    public String getTenNVByMaNV(int MaNV){
        return nhanVienDAO.getTenNVByMaNV(MaNV);
    }
}
