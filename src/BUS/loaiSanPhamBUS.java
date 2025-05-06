package BUS;

import java.util.List;

import DAO.LoaiSPDAO;
import DTO.LoaiSanPhamDTO;

public class LoaiSanPhamBUS {
    private LoaiSPDAO lSpdao = new LoaiSPDAO();
    public List<LoaiSanPhamDTO> getAllLoaiSanPham() {
        return lSpdao.getAllList();
    }

    public String getTenLoaiById(int id) {
        return lSpdao.getName(id);
    }

    public Boolean Them(LoaiSanPhamDTO lDto){
        return lSpdao.themLoai(lDto);
    }

    public Boolean checkID(int id){
        for (LoaiSanPhamDTO loai : lSpdao.getAllList()) {
           if(loai.getMaLoai() == id)
           return true; 
        }
        return false;
    }

    public Boolean Sua(LoaiSanPhamDTO loai){
        return lSpdao.SuaLoaiSP(loai);
    }

    public Boolean Khoa(int id,Boolean isDeleted){
        return lSpdao.khoaPL(id,isDeleted);
    }
}
