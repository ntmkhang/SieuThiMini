package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.TaiKhoanDTO;

public class LoginDAO {
    private ConnectManager connectManager;
    
    public LoginDAO () {
        connectManager= new ConnectManager();
    }
    public TaiKhoanDTO checkLogin(String tenTK, String matKhau) {
        String query = "SELECT MaTK, TenTK FROM TaiKhoan WHERE TenTK = ? AND MatKhau = ?";

        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, tenTK);
            pstmt.setString(2, matKhau);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new TaiKhoanDTO(rs.getInt("MaTK"), rs.getString("TenTK"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi  đăng nhập: " + e.getMessage());
        }
        return null;
    }

    public boolean isAdmin (String TenTK, String MatKhau){
        String query = "SELECT MaTK FROM TaiKhoan WHERE TenTK = ? AND MatKhau = ? AND MaQuyen = 1";

        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, TenTK);
            preparedStatement.setString(2, MatKhau);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally{
            connectManager.closeConnection();
        }
    }

    public int getMaNVbyTenDangNhap (String username){
        int maNV =-1;
        String query = "SELECT MaNV FROM TaiKhoan WHERE TenTK = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                maNV = rs.getInt("MaNV");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return maNV;
    }

    public String getTenNVbyMaNV (int maNV){
        String tenNV = "";
        String query = "SELECT TenNV FROM NhanVien WHERE MaNV = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maNV);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tenNV = rs.getString("TenNV");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return tenNV;
    }
}
