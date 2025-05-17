package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

import java.util.ArrayList;

public class NhaCungCapBUS {
    private NhaCungCapDAO nhacungcapDAO;

    public NhaCungCapBUS(){
        nhacungcapDAO =  new NhaCungCapDAO();
    }

    public String themNCC(NhaCungCapDTO ncc){
        if(nhacungcapDAO.findNCC(ncc.getSoDT(), ncc.getMaNCC())){
            return "Có nhà cung cấp đã xài sdt này rồi!";
        }
        if(nhacungcapDAO.themNCC(ncc)){
            return "Thêm thành công!";
        }
        return "Thêm nhà cung cấp không thành công!";
    }

    public String xoaNCC(NhaCungCapDTO ncc){
        if(nhacungcapDAO.deleteNCC(ncc)){
            return "Xóa thành công!";
        }
        return "Xóa không thành công!";
    }

    public String updateNCC(NhaCungCapDTO ncc){
        if(nhacungcapDAO.updateNCC(ncc)){
            return "Cập nhật thành công";
        }
        return "Cập nhật không thành công";
    }
    public int taoMaNCC(){
        return nhacungcapDAO.createCodeNCC();
    }

    public int findMaNCC(String sdt){
        return nhacungcapDAO.findMaNCC(sdt);
    }

    public ArrayList<NhaCungCapDTO> getAllNCC(){
        return nhacungcapDAO.getAllNCC();
    }

    public boolean  ktSdt(String sdt, int MaNCC){
        return nhacungcapDAO.findNCC(sdt, MaNCC);
    }

    public ArrayList<NhaCungCapDTO> searchArrayNCC(String keyword){
        return nhacungcapDAO.searchArrayNCC(keyword);
    }
}
