package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import DTO.nhanVienDTO;

public class nhanVienDAO {
    private ConnectManager connectManager;

    public nhanVienDAO(){
        connectManager = new ConnectManager();
    }

    public ArrayList<nhanVienDTO> getAllNhanVien (){
        ArrayList<nhanVienDTO> nhanVien = new ArrayList<>();
        String query ="SELECT * FROM NhanVien WHERE Is_Deleted = 0";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                nhanVien.add(new nhanVienDTO(
                    rs.getInt("MaNV"),
                    rs.getString("TenNV"),
                    rs.getBoolean("GioiTinh"),
                    rs.getDate("NgaySinh"),
                    rs.getString("SoDienThoai"),
                    rs.getString("Email"),
                    rs.getString("DiaChi"),
                    rs.getInt("MaChucVu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return nhanVien;
    }

    public nhanVienDTO getNhanVienbyMaNV (int maNV){
        nhanVienDTO nhanVien = null;
        String query ="SELECT * FROM NhanVien WHERE Is_Deleted =0 AND MaNV = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maNV);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                nhanVien = new nhanVienDTO();
                nhanVien.setMaNV(rs.getInt("MaNV"));
                nhanVien.setTenNV(rs.getString("TenNV"));
                nhanVien.setGioiTinh(rs.getBoolean("GioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setSoDienThoai(rs.getString("SoDienThoai"));
                nhanVien.setEmail(rs.getString("Email"));
                nhanVien.setDiaChi(rs.getString("DiaChi"));
                nhanVien.setMaChucVu(rs.getInt("MaChucVu"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }

        return nhanVien;
    }

    public String getTenNhanVienbyMaNV (int maNV){
        String tenNV ="";
        String query ="SELECT TenNV FROM NhanVien WHERE Is_Deleted = 0 AND MaNV = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maNV);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                tenNV = rs.getString("TenNV");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }

        return tenNV;
    }
}
