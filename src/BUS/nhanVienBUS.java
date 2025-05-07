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
}
