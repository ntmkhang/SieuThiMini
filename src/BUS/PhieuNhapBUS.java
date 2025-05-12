package BUS;

import DAO.PhieuNhapDAO;
import DTO.CTPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.sanPhamDTO;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PhieuNhapBUS {
    PhieuNhapDAO phieunhapDAO = new PhieuNhapDAO();

    public String ThemPN(PhieuNhapDTO phieunhap){
        if(phieunhapDAO.ThemPN(phieunhap)){
            return "Thêm thành công";
        }
        return "Thêm không thành công";
    }

    public void ThemCTPN(CTPhieuNhapDTO ctphieunhap){
        phieunhapDAO.ThemCTPN(ctphieunhap);
    }

    public String XoaPN(int MaPN){
        if(phieunhapDAO.XoaPN(MaPN)){
            return "Xoá phiếu nhập thành công";
        }
        return "Xoá phiếu nhập không thành công";
    }

    public boolean XoaCTPN(int MaPN){
        return phieunhapDAO.XoaCTPN(MaPN);
    }

    public String getCapNhatPN(PhieuNhapDTO phieunhap){
        if(phieunhapDAO.CapNhatPN(phieunhap)){
            return "Cập nhật thành công";
        }
        return "Cập nhật không thành công";
    }

    public boolean getCapNhatSL(int MaPN){
        return phieunhapDAO.CapNhatSL(MaPN);
    }

    public String getDuyetPN(int MaPN){
        if(phieunhapDAO.DuyetPN(MaPN)){
            return "Duyệt thành công";
        }
        return "Duyệt không thành công";
    }

    public PhieuNhapDTO getTKiemPN(int MaPN){
        return phieunhapDAO.TKiemPN(MaPN);
    }

    public int createCodeNCC(){
        return phieunhapDAO.createCodeNCC();
    }

    public ArrayList<sanPhamDTO> getAllSP(){
        return phieunhapDAO.getAllSP();
    }

    public ArrayList<PhieuNhapDTO> getAllPN(){
        return phieunhapDAO.getAllPN();
    }

    public ArrayList<CTPhieuNhapDTO> getAllCTPN(int MaPN){
        return phieunhapDAO.getAllCTPN(MaPN);
    }

    public ArrayList<PhieuNhapDTO.tablePNDTO> TKiemTheoTenNCC(String keyword){
        return phieunhapDAO.TKiemTheoTenNCC(keyword);
    }

    public ArrayList<PhieuNhapDTO.tablePNDTO> TKiemTenNCCTheoNgay(String keyword, Timestamp ngayBD, Timestamp ngayKT){
        return phieunhapDAO.TKiemTenNCCTheoNgay(keyword, ngayBD, ngayKT);
    }

    public ArrayList<sanPhamDTO> searchSpArray(String keyword){
        return phieunhapDAO.searchSpArray(keyword);
    }

    public ArrayList<PhieuNhapDTO.tableSPChon> getAllSPChon(int MaPN){
        return phieunhapDAO.getAllSPChon(MaPN);
    }

    public ArrayList<PhieuNhapDTO.tablePNDTO> getAllRow(){
        return phieunhapDAO.getAllRow();
    }

    public ArrayList<PhieuNhapDTO.tableCTPNDTO> getAllRowCT(int MaPN){
        return phieunhapDAO.getAllRowCT(MaPN);
    }
}
