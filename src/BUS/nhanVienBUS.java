package BUS;

import DAO.NhanVienDAO;

public class NhanVienBUS {
 private NhanVienDAO _nhanVienDAO = new NhanVienDAO();

 public String getTenNvByID(int maNV){
    return _nhanVienDAO.getTenNhanVienByID(maNV);
 }
}
