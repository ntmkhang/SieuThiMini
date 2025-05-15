package BUS;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



import DAO.sanPhamDAO;
import DTO.CThoaDonDTO;
import DTO.sanPhamDTO;

public class sanPhamBUS {
    sanPhamDAO sanPhamDAO;

    public sanPhamBUS (){
        sanPhamDAO = new sanPhamDAO();
    }

    public ArrayList<sanPhamDTO> getAllSanPham (){
        return sanPhamDAO.getAllSanPham();
    }

    public ArrayList<sanPhamDTO> timSanPhamTheoTenvaLoaiSanPham (String tenSP, String loaiSP){
        return sanPhamDAO.timSanPhamTheoTenVaLoaiSP(tenSP, loaiSP);
    }

    public ArrayList<sanPhamDTO> timSanPhamTheoTen (String tenSP){
        return sanPhamDAO.timSanPhamTheoTen(tenSP);
    }

    public ArrayList<sanPhamDTO> timSanPhamTheoLoai (String loaiSP){
        return sanPhamDAO.timSanPhamTheoLoai(loaiSP);
    }

    public boolean capNhatSoLuongSanPham (int maSP, int soLuongBan){
        return sanPhamDAO.capNhatSoLuongSanPham(maSP, soLuongBan);
    }

    public int getMaSPbyTenSP (String tenSP){
        return sanPhamDAO.getMaSPbyTenSP(tenSP);
    }

    public String getTenSPbyMaSP (int maSP){
        return sanPhamDAO.getTenSPbyMaSp(maSP);
    }

 
     

    public List<sanPhamDTO> timKiem(String key){
        if (key == null) {
            return sanPhamDAO.getAllList();
        }
        return sanPhamDAO.getAllList().stream()
                .filter(sp ->sp.getTenSP().toLowerCase().contains(key.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean ThemSP(sanPhamDTO sp){
        return sanPhamDAO.ThemSP(sp);
    }

    public boolean XoaSP(int id){
        return sanPhamDAO.XoaSP(id);
    }

    public boolean SuaSP(int id, sanPhamDTO sp){
        return sanPhamDAO.SuaSP(id, sp);
    }

    public sanPhamDTO TimKiemTheoId(int id){
        return sanPhamDAO.TimKiemTheoId(id);
    }

    public boolean capNhatTrangThai(int id, int isDeleted){
        return sanPhamDAO.capNhatTrangThaiSanPham(id, isDeleted);
    }

    public List<sanPhamDTO> getALL(){
        return sanPhamDAO.getAllList();
    }
}

