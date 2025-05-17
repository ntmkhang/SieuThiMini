package BUS;

import DAO.TaiKhoanDAO;
import DTO.AccountDTO;

import java.util.ArrayList;

public class TaiKhoanBUS {
    private final TaiKhoanDAO taikhoanDAO;

    public TaiKhoanBUS(){
        taikhoanDAO = new TaiKhoanDAO();
    }

    public boolean themTaiKhoan(AccountDTO taikhoan){
        if(!taikhoanDAO.KtraNhanVienTonTai(taikhoan.getMaNV())){
            return false;
        }
        return taikhoanDAO.themTaiKhoan(taikhoan);
    }

    public boolean suaTaiKhoan(AccountDTO taikhoan){
        return taikhoanDAO.suaTaiKhoan(taikhoan);
    }

    public boolean xoaTaiKhoan(int MaTK){
        return taikhoanDAO.xoaTaiKhoan(MaTK);
    }

    public boolean KtraTaiKhoanTonTai(int MaTK){
        return taikhoanDAO.KtraTaiKhoanTonTai(MaTK);
    }

    public boolean KtraNhanVienTonTai(int MaNV){
        return taikhoanDAO.KtraNhanVienTonTai(MaNV);
    }

    public ArrayList<AccountDTO> getAllAccounts(){
        return taikhoanDAO.getAllAccounts();
    }

    public ArrayList<AccountDTO> TKiemTaiKhoanTheoTen(String keyword){
        return taikhoanDAO.TKiemTaiKhoanTheoTen(keyword);
    }
}
