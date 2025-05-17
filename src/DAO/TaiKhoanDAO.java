package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.AccountDTO;

public class TaiKhoanDAO {
    private ConnectManager connectManager;

    public TaiKhoanDAO(){
        connectManager = new ConnectManager();
    }

    public boolean themTaiKhoan(AccountDTO taikhoan){
        String query = "INSERT INTO TaiKhoan (TenNV, MatKhau, MaNV, MaQuyen, Is_Deleted) VALUE (?, ?, ?, ?, ?)";
        try{
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, taikhoan.getTenTK());
            stmt.setString(2, taikhoan.getMatKhau());
            stmt.setInt(3, taikhoan.getMaNV());
            stmt.setInt(4, taikhoan.getMaQuyen());
            stmt.setInt(5, taikhoan.getIsDeleted());
            return stmt.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return false;
    }

    public boolean suaTaiKhoan(AccountDTO taikhoan){
        String query = "UPDATE TaiKhoan SET TenTK = ?, MatKhau = ?, MaNV = ?, MaQuyen = ?, Is_Deleted = ? Where MaTK = ?";
        try{
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, taikhoan.getTenTK());
            stmt.setString(2, taikhoan.getMatKhau());
            stmt.setInt(3, taikhoan.getMaNV());
            stmt.setInt(4, taikhoan.getMaQuyen());
            stmt.setInt(5, taikhoan.getIsDeleted());
            stmt.setInt(6, taikhoan.getMaTK());
            return stmt.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
            
        } finally{
            connectManager.closeConnection();
        }
        return false;
    }

    public boolean xoaTaiKhoan(int MaTK){
        String query = "DELETE From TaiKhoan Where MaTK = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaTK);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally{
            connectManager.closeConnection();
        }
        return false;
    }

    public boolean KtraTaiKhoanTonTai(int MaNV){
        String query = "Select COUNT(*) AS count From TaiKhoan Where MaNV = ? AND Is_Deleted != 1";
        try{
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getInt("count") > 0;
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return false;
    }

    public boolean KtraNhanVienTonTai(int MaNV){
        String query = "Select 1 From NhanVien Where MaNV = ? AND Is_Deleted != 1";
        try{
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaNV);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return false;
    }

    public ArrayList<AccountDTO> getAllAccounts(){
        ArrayList<AccountDTO> taikhoan = new ArrayList<>();
        String query = "Select * From TaiKhoan Where Is_Deleted != 1";
        try{
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                AccountDTO tk = new AccountDTO();
                tk.setMaTK(rs.getInt(1));
                tk.setTenTK(rs.getString(2));
                tk.setMatKhau(rs.getString(3));
                tk.setMaQuyen(rs.getInt(4));
                tk.setMaNV(rs.getInt(5));
                tk.setIsDeleted(rs.getInt(6));
                taikhoan.add(tk);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return taikhoan;
    }

    public ArrayList<AccountDTO> TKiemTaiKhoanTheoTen(String keyword){
        ArrayList<AccountDTO> taikhoan = new ArrayList<>();
        String query = "Select * From TaiKhoan Where TenTK LIKE ? AND Is_Deleted != 1";
        try{
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AccountDTO tk = new AccountDTO();
                tk.setMaTK(rs.getInt(1));
                tk.setTenTK(rs.getString(2));
                tk.setMatKhau(rs.getString(3));
                tk.setMaQuyen(rs.getInt(4));
                tk.setMaNV(rs.getInt(5));
                tk.setIsDeleted(rs.getInt(6));
                taikhoan.add(tk);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return taikhoan;
    }
    
}
