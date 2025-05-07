package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.LoaiSPDAO;
import DAO.loaiSanPhamDAO;
import DTO.loaiSanPhamDTO;
public class loaiSanPhamBUS {
    private loaiSanPhamDAO loaiSanPhamDAO;
    private LoaiSPDAO lSpdao;

    public loaiSanPhamBUS (){
        loaiSanPhamDAO = new loaiSanPhamDAO();
        lSpdao = new LoaiSPDAO();
    }

    public ArrayList<String> getAllLoaiSanPham(){
        return loaiSanPhamDAO.getAllLoaiSanPham();
    }

    public int getMaLoaibyTenLoai(String tenLoai){
        return loaiSanPhamDAO.getMaLoaibyTenLoai(tenLoai);
    }

   
    public List<loaiSanPhamDTO> getAll() {
        return lSpdao.getAllList();
    }


    public String getTenLoaiById(int id) {
        return lSpdao.getName(id);
    }

    public Boolean Them(loaiSanPhamDTO lDto){
        return lSpdao.themLoai(lDto);
    }

    public Boolean checkID(int id){
        for (loaiSanPhamDTO loai : lSpdao.getAllList()) {
           if(loai.getMaLoai() == id)
           return true; 
        }
        return false;
    }

    public Boolean Sua(loaiSanPhamDTO loai){
        return lSpdao.SuaLoaiSP(loai);
    }

    public Boolean Khoa(int id,int isDeleted){
        return lSpdao.khoaPL(id,isDeleted);
    }
}

