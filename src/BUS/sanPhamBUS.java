package BUS;

import java.lang.reflect.Array;
import java.util.ArrayList;

import DAO.sanPhamDAO;
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
}
