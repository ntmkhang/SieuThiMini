package DAO;

import java.sql.*;
import java.util.ArrayList;

import DTO.CTPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.sanPhamDTO;

public class PhieuNhapDAO {
    private ConnectManager connectManager;

    public PhieuNhapDAO(){
        connectManager = new ConnectManager();
    }

    public boolean ThemPN(PhieuNhapDTO phieunhap){
        boolean check = false;
        String query = "Insert INTO PhieuNhap ( NgayLap, MaNV, MaNCC, TrangThai, Is_Deleted) Values (?,?,?,?,0)";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setTimestamp(1, phieunhap.getNgayLap());
            stmt.setInt(2, phieunhap.getMaNV());
            stmt.setInt(3, phieunhap.getMaNCC());
            stmt.setString(4, phieunhap.getTrangThai());
            if(stmt.executeUpdate() >= 1){
                check = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return check;
    }

    public boolean ThemCTPN(CTPhieuNhapDTO ctpn){
        boolean check = false;
        String query = "Insert INTO CTPhieuNhap (MaPN, MaSP, SoLuong, Gianhap) Values (?, ?, ?, ?)";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, ctpn.getMaPN());
            stmt.setInt(2, ctpn.getMaSP());
            stmt.setInt(3, ctpn.getSoLuong());
            stmt.setInt(4, ctpn.getGiaNhap());
            if(stmt.executeUpdate() >= 1){
                check = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return check;
    }

    public boolean XoaPN(int MaPN){
        boolean check = false;
        String query = "Delete From PhieuNhap Where MaPN = ?";
        if(XoaCTPN(MaPN)){
            try {
                connectManager.openConnection();
                Connection connection = connectManager.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, MaPN);
                if(stmt.executeUpdate() >= 1){
                    check = true;
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally{
                connectManager.closeConnection();
            }
        }
        return check;
    }

    public boolean XoaCTPN(int MaPN){
        boolean check = false;
        String query = "Delete From CTPhieuNhap Where MaPN = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt  = connection.prepareStatement(query);
            stmt.setInt(1, MaPN);
            if(stmt.executeUpdate() >= 1){
                check = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return check;
    }

    public boolean CapNhatPN(PhieuNhapDTO phieunhap){
        boolean check = false;
        String query = "UPDATE PhieuNhap SET MaNCC = ? Where MaPN = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, phieunhap.getMaPN());
            stmt.setInt(2, phieunhap.getMaNCC());
            if(stmt.executeUpdate() >= 1){
                check = false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return check;
    }

    public boolean CapNhatSL(int MaPN){
        boolean check = false;
        String query = "UPDATE SanPham SET SoLuong = SoLuong " + 
                        "(Select SoLuong From CTPhieuNhap Where CTPhieuNhap.MaSP = SanPham.MaSP AND CTPhieuNhap.MaPN = ?) " + 
                        "Where EXISTS " + 
                        "(Select 1 From CTPhieuNhap Where CTPhieuNhap.MaSP = SanPham.MaSP AND CTPhieuNhap.MaPN = ?)";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaPN);
            stmt.setInt(2, MaPN);
            if(stmt.executeUpdate() >= 1){
                check = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return check;
    }

    public boolean DuyetPN(int MaPN){
        boolean check = false;
        String query = "UPDATE PhieuNhap SET TrangThai = 1 Where MaPN = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaPN);
            if(stmt.executeUpdate() >= 1){
                check = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return check;
    }

    public PhieuNhapDTO TKiemPN(int MaPN){
        PhieuNhapDTO pn = new PhieuNhapDTO();
        String query = "Select * From PhieuNhap Where MaPN = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaPN);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                pn.setMaPN(rs.getInt(1));
                pn.setNgayLap(rs.getTimestamp(2));
                pn.setMaNV(rs.getInt(3));
                pn.setMaNCC(rs.getInt(4));
                pn.setTrangThai(rs.getInt(5) == 0 ? "Chờ xử lý" : "Hoàn Thành");
                pn.setIs_Deleted(rs.getInt(6));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return pn;
    }

    public int createCodeNCC(){
        int code = -1;
        String query = "Select COALESCE(MAX(MaPN), 0) + 1 AS newCode From PhieuNhap";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                code = rs.getInt("newCode");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return code;
    }

    public ArrayList<sanPhamDTO> getAllSP(){
        ArrayList<sanPhamDTO> slsp = new ArrayList<sanPhamDTO>();
        String query = "Select MaSP, TenSP, SoLuong, Is_Deleted From SanPham Where Is_Deleted != 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                sanPhamDTO sp = new sanPhamDTO();
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setIsDeleted(rs.getInt("Is_Deleted"));
                slsp.add(sp);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return slsp;
    }

    public ArrayList<PhieuNhapDTO> getAllPN(){
        ArrayList<PhieuNhapDTO> slpn = new ArrayList<PhieuNhapDTO>();
        String query = "Select * From PhieuNhap Where Is_Deleted != 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                PhieuNhapDTO pn = new PhieuNhapDTO();
                pn.setMaPN(rs.getInt(1));
                pn.setNgayLap(rs.getTimestamp(2));
                pn.setMaNV(rs.getInt(3));
                pn.setMaNCC(rs.getInt(4));
                pn.setTrangThai(rs.getString(5));
                pn.setIs_Deleted(rs.getInt(6));
                slpn.add(pn);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return slpn;
    }

    public ArrayList<CTPhieuNhapDTO> getAllCTPN(int MaPN){
        ArrayList<CTPhieuNhapDTO> slct = new ArrayList<CTPhieuNhapDTO>();
        String query = "Select * From CTPhieuNhap Where MaPN = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO();
                ctpn.setMaPN(rs.getInt(1));
                ctpn.setMaSP(rs.getInt(2));
                ctpn.setSoLuong(rs.getInt(3));
                ctpn.setGiaNhap(rs.getInt(4));
                slct.add(ctpn);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return slct;
    }

    public ArrayList<PhieuNhapDTO.tablePNDTO> TKiemTheoTenNCC(String keyword){
        ArrayList<PhieuNhapDTO.tablePNDTO>  dstk = new ArrayList<PhieuNhapDTO.tablePNDTO>();
        String query = "Select pn.MaPN, pn.NgayLap, nv.TenNV, ncc.TenNCC, TrangThai, Is_Deleted " +
                        "From PhieuNhap p JOIN NhaCungCap ncc ON p.MaNCC = ncc.MaNCC JOIN NhanVien nv ON p.MaNV = nv.MaNV " + 
                        "Where ncc.TenNCC LIKE ? AND Is_Deleted != 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                PhieuNhapDTO pn = new PhieuNhapDTO();
                PhieuNhapDTO.tablePNDTO row = pn.new tablePNDTO();
                row.setMaPN(rs.getInt(1));
                row.setNgayLap(rs.getTimestamp(2));
                row.setTenNV(rs.getString(3));
                row.setTenNCC(rs.getString(4));
                row.setTrangThai(rs.getInt(5) == 0 ? "Chờ xử lý" : "Hoàn Thành");
                row.setIs_Deleted(rs.getInt(6));
                dstk.add(row);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return dstk;
    }

    public ArrayList<PhieuNhapDTO.tablePNDTO> TKiemTenNCCTheoNgay(String keyword, Timestamp ngayBD, Timestamp ngayKT){
        ArrayList<PhieuNhapDTO.tablePNDTO> tktn = new ArrayList<PhieuNhapDTO.tablePNDTO>();
        String query = "Select pn.NgayTao, pn.MaPN, nv.TenNV, ncc.TenNCC, TrangThai, Is_Deleted " + 
                        "From PhieuNhap pn JOIN NhaCungCap ncc ON pn.MaNCC = ncc.MaNCC " + 
                        "JOIN NhanVien nv ON pn.MaNV = nv.MaNV " + 
                        "Where pn.Is_Deleted = 0 AND ncc.TenNCC LIKE ? ";
        
        if(ngayBD != null && ngayKT != null){
            query += "AND NgayLap BETWEEN ? AND ?";
        } else if(ngayBD != null){
            query += "AND NgayLap >= ?";
        } else if(ngayKT != null){
            query += "AND NgayLap <= ?";
        }

        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            if(ngayBD != null && ngayKT != null){
                stmt.setTimestamp(2, ngayBD);
                stmt.setTimestamp(3, ngayKT);
            } else if(ngayBD != null){
                stmt.setTimestamp(2, ngayBD);
            } else if(ngayKT != null){
                stmt.setTimestamp(2, ngayKT);
            }

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                PhieuNhapDTO pn = new PhieuNhapDTO();
                PhieuNhapDTO.tablePNDTO row = pn.new tablePNDTO();
                row.setMaPN(rs.getInt(1));
                row.setNgayLap(rs.getTimestamp(2));
                row.setTenNV(rs.getString(3));
                row.setTenNCC(rs.getString(4));
                row.setTrangThai(rs.getInt(5) == 0 ? "Chờ xử lý" : "Hoàn thành");
                row.setIs_Deleted(rs.getInt(6));
                tktn.add(row);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return tktn;
    }

    public ArrayList<sanPhamDTO> searchSpArray(String keyword){
        ArrayList<sanPhamDTO> tksp = new ArrayList<sanPhamDTO>();
        String query = "Select MaSP, TenSP, SoLuong, Is_Deleted From SanPham Where TenSP LIKE ? AND Is_Deleted != 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                sanPhamDTO sp = new sanPhamDTO();
                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setSoLuong(rs.getInt(3));
                sp.setIsDeleted(rs.getInt(4));
                tksp.add(sp);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return tksp;
    }

    public ArrayList<PhieuNhapDTO.tableSPChon> getAllSPChon(int MaPN){
        ArrayList<PhieuNhapDTO.tableSPChon> slspc = new ArrayList<PhieuNhapDTO.tableSPChon>();
        String query = "Select sp.MaSP, sp.TenSP, ctpn.SoLuong, ctpn.GiaNhap " +
                        "From CTPhieuNhap ctpn JOIN SanPham sp ON ctpn.MaSP = sp.MaSP " +
                        "Where ctpn.MaPN = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaPN);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                PhieuNhapDTO pn = new PhieuNhapDTO();
                PhieuNhapDTO.tableSPChon row = pn.new tableSPChon();
                row.setMaSP(rs.getInt(1));
                row.setTenSP(rs.getString(2));
                row.setSoLuong(rs.getInt(3));
                row.setGiaNhap(rs.getInt(4));
                slspc.add(row);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return slspc;
    }

    public ArrayList<PhieuNhapDTO.tablePNDTO> getAllRow() {
        ArrayList<PhieuNhapDTO.tablePNDTO> dsTable = new ArrayList<PhieuNhapDTO.tablePNDTO>();
        String query = "Select p.MaPN, p.NgayLap, nv.TenNV, ncc.TenNCC, TrangThai, p.Is_Deleted " +
                "From PhieuNhap p join NhaCungCap ncc on p.MaNCC = ncc.MaNCC join NhanVien nv on p.MaNV = nv.MaNV";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(6) != 1) {
                    PhieuNhapDTO pn = new PhieuNhapDTO();
                    PhieuNhapDTO.tablePNDTO row = pn.new tablePNDTO();
                    row.setMaPN(rs.getInt(1));
                    row.setNgayLap(rs.getTimestamp(2));
                    row.setTenNV(rs.getString(3));
                    row.setTenNCC(rs.getString(4));
                    row.setTrangThai(rs.getInt(5) == 0 ? "Chờ Xử Lý" : "Hoàn Thành");
                    row.setIs_Deleted(rs.getInt(6));
                    dsTable.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return dsTable;
    }

    public ArrayList<PhieuNhapDTO.tableCTPNDTO> getAllRowCT(int MaPN){
        ArrayList<PhieuNhapDTO.tableCTPNDTO> dsctpn = new ArrayList<PhieuNhapDTO.tableCTPNDTO>();
        String query = "Select sp.TenSP, ctpn.SoLuong, ctpn.GiaNhap, (ctpn.SoLuong * ctpn.GiaNhap) AS ThanhTien " + 
                        "From CTPhieuNhap ctpn JOIN SanPham sp ON ctpn.MaSP = sp.MaSP " + 
                        "Where ctpn.MaPN = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaPN);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                PhieuNhapDTO ctpn = new PhieuNhapDTO();
                PhieuNhapDTO.tableCTPNDTO row = ctpn.new tableCTPNDTO();
                row.setTenSP(rs.getString(1));
                row.setSoLuong(rs.getInt(2));
                row.setGiaNhap(rs.getInt(3));
                row.setThanhTien(rs.getInt(4));
                dsctpn.add(row);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return dsctpn;
    }

}
