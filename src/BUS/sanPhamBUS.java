package BUS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

public class SanPhamBUS {
    private SanPhamDAO _sanphamDAO = new SanPhamDAO();

    public List<SanPhamDTO> getAll(){
        return new ArrayList<>(_sanphamDAO.getAllList());
    }

    public List<SanPhamDTO> timKiem(String key){
        if (key == null) {
            return _sanphamDAO.getAllList();
        }
        return _sanphamDAO.getAllList().stream()
                .filter(sp ->sp.getTenSP().toLowerCase().contains(key.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean ThemSP(SanPhamDTO sp){
        return _sanphamDAO.ThemSP(sp);
    }

    public boolean XoaSP(int id){
        return _sanphamDAO.XoaSP(id);
    }

    public boolean SuaSP(int id, SanPhamDTO sp){
        return _sanphamDAO.SuaSP(id, sp);
    }

    public SanPhamDTO TimKiemTheoId(int id){
        return _sanphamDAO.TimKiemTheoId(id);
    }

    public boolean capNhatTrangThai(int id, Boolean isDeleted){
        return _sanphamDAO.capNhatTrangThaiSanPham(id, isDeleted);
    }

}
