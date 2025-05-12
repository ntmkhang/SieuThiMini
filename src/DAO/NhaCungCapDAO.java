package DAO;

import DTO.NhaCungCapDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NhaCungCapDAO {
    private ConnectManager connectManager;

    public NhaCungCapDAO(){
        connectManager = new ConnectManager();
    }

    public boolean themNCC(NhaCungCapDTO ncc){
        boolean check = false;
        String query = "INSERT INTO NhaCungCap (TenNCC, DiaChi, Sdt, NguoiLienHe, Is_Deleted) VALUE (?, ?, ?, ?, ?)";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, ncc.getTenNCC());
            stmt.setString(2, ncc.getDiaChi());
            stmt.setString(3, ncc.getSoDT());
            stmt.setString(4, ncc.getNguoiLienHe());
            stmt.setInt(5, ncc.getIs_Deleted());
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

    public boolean deleteNCC(NhaCungCapDTO ncc){
        boolean check = false;
        String query = "UPDATE NhaCungCap SET Is_Deleted = ? Where MaNCC = ?";
        try {
            connectManager.openConnection();
            Connection  connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setInt(2, ncc.getMaNCC());
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

    public boolean updateNCC(NhaCungCapDTO ncc){
        boolean check = false;
        String query = "UPDATE NhaCungCap SET TenNCC = ?, DiaChi = ?, Sdt = ?, NguoiLienHe = ? Where MaNCC = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, ncc.getTenNCC());
            stmt.setString(2, ncc.getDiaChi());
            stmt.setString(3, ncc.getSoDT());
            stmt.setString(4, ncc.getSoDT());
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

    public boolean findNCC(String sdt, int MaNCC){
        boolean check = false;
        String query = "Select COUNT(*) From NhaCungCap Where Sdt = ? AND MaNCC != ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, sdt);
            stmt.setInt(2, MaNCC);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                if(rs.getInt(1) > 0){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return check;
    }

    public int findMaNCC(String sdt){
        int codeCreated = -1;
        String query = "Select MaNCC From NhaCungCap Where Sdt = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            codeCreated = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return codeCreated;
    }

    public int createCodeNCC(){
        int code = -1;
        String query = "Select COALESCE(MAX(MaNCC), 0) + 1 AS newCode From NhaCungCap";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                code = rs.getInt("newCode");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return code;
    }

    public ArrayList<NhaCungCapDTO> getAllNCC(){
        ArrayList<NhaCungCapDTO> tcncc = new ArrayList<NhaCungCapDTO>();
        String query = "Select * From NhaCungCap Where Is_Deleted != 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                NhaCungCapDTO ncc = new NhaCungCapDTO();
                ncc.setMaNCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setDiaChi(rs.getString(3));
                ncc.setSoDT(rs.getString(4));
                ncc.setNguoiLienHe(rs.getString(5));
                ncc.setIs_Deleted(rs.getInt(6));
                tcncc.add(ncc);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return tcncc;
    }

    public ArrayList<NhaCungCapDTO> searchArrayNCC(String keyword){
        ArrayList<NhaCungCapDTO> tkncc = new ArrayList<NhaCungCapDTO>();
        String query = "Select * From NhaCungCap Where TenNCC LIKE ? OR Sdt LIKE ? AND Is_Deleted != 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                NhaCungCapDTO ncc = new NhaCungCapDTO();
                ncc.setMaNCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setDiaChi(rs.getString(3));
                ncc.setSoDT(rs.getString(4));
                ncc.setNguoiLienHe(rs.getString(5));
                ncc.setIs_Deleted(rs.getInt(6));
                tkncc.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return tkncc;
    }
}
