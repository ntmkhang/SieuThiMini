package BUS;

import java.util.ArrayList;

import DAO.loaiSanPhamDAO;
import DTO.loaiSanPhamDTO;
public class loaiSanPhamBUS {
    private loaiSanPhamDAO loaiSanPhamDAO;

    public loaiSanPhamBUS (){
        loaiSanPhamDAO = new loaiSanPhamDAO();
    }

    public ArrayList<String> getAlLoaiSanPham(){
        return loaiSanPhamDAO.getAllLoaiSanPham();
    }

    public int getMaLoaibyTenLoai(String tenLoai){
        return loaiSanPhamDAO.getMaLoaibyTenLoai(tenLoai);
    }
}
