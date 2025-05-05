package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.sanPhamDTO;

public class sanPhamDAO {
    private ConnectManager connectManager;

    public sanPhamDAO(){
        connectManager = new ConnectManager();

    }

    public ArrayList<sanPhamDTO> getAllSanPham (){
        ArrayList<sanPhamDTO> sanPham = new ArrayList<>();
        String query ="SELECT * FROM SanPham WHERE Is_Deleted = 0";
        try{
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                sanPham.add(new sanPhamDTO(
                    rs.getInt("MaSP"),
                    rs.getString("TenSP"),
                    rs.getInt("MaLoai"),
                    rs.getString("MoTa"),
                    rs.getInt("GiaBan"),
                    rs.getInt("SoLuong"),
                    rs.getString("HinhANh"),
                    rs.getInt("Is_Deleted")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return sanPham;
    }

    public ArrayList<sanPhamDTO> timSanPhamTheoTenVaLoaiSP(String tenSanPham, String loaiSanPham){
        ArrayList<sanPhamDTO> sanPham = new ArrayList();
        String query ="SELECT * FROM SanPham sp JOIN LoaiSanPham lsp on sp.MaLoai = lsp.MaLoai WHERE (sp.TenSp LIKE ? OR ? = '')"+
                        (loaiSanPham.equals("Tất cả") ? "" : "AND lsp.TenLoai = ? AND sp.Is_Deleted = 0");
        try{
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + tenSanPham + "%");
            preparedStatement.setString(2, tenSanPham);

            if (!loaiSanPham.equals("Tất cả")){
                preparedStatement.setString(3, loaiSanPham );
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                sanPham.add(new sanPhamDTO(
                    rs.getInt("MaSP"),
                    rs.getString("TenSP"),
                    rs.getInt("MaLoai"),
                    rs.getString("MoTa"),
                    rs.getInt("GiaBan"),
                    rs.getInt("SoLuong"),
                    rs.getString("HinhAnh"),
                    rs.getInt("Is_Deleted")
                ));
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return sanPham;
    }

    public ArrayList<sanPhamDTO> timSanPhamTheoTen (String tenSanPham){
        ArrayList<sanPhamDTO> sanPham = new ArrayList<>();
        String query = "SELECT * FROM SanPham WHERE TenSP LIKE ? AND Is_Deleted = 0";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"%" + tenSanPham +"%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                sanPham.add(new sanPhamDTO(
                    rs.getInt("MaSP"),
                    rs.getString("TenSP"),
                    rs.getInt("MaLoai"),
                    rs.getString("MoTa"),
                    rs.getInt("GiaBan"),
                    rs.getInt("SoLuong"),
                    rs.getString("HinhAnh"),
                    rs.getInt("Is_Deleted")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return sanPham;
    }

    public ArrayList<sanPhamDTO> timSanPhamTheoLoai(String tenLoai){
        ArrayList<sanPhamDTO> sanPham = new ArrayList<>();
        String query = "SELECT sp.* FROM SanPham sp, LoaiSanPham lsp WHERE TenLoai LIKE ? AND sp.Is_Deleted = 0 AND sp.MaLoai = lsp.MaLoai";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"%" + tenLoai + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                sanPham.add(new sanPhamDTO(
                    rs.getInt("MaSP"),
                    rs.getString("TenSP"),
                    rs.getInt("MaLoai"),
                    rs.getString("MoTa"),
                    rs.getInt("GiaBan"),
                    rs.getInt("SoLuong"),
                    rs.getString("HinhAnh"),
                    rs.getInt("Is_Deleted")
                    ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return sanPham;
    }

    public boolean capNhatSoLuongSanPham(int maSP, int soLuongBan){
        String query ="UPDATE SanPham SET SoLuong = SoLuong - ? WHERE MaSP = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, soLuongBan);
            preparedStatement.setInt(2, maSP);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected > 0; //trả về true nếu cập nhật sản phẩm thành công 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally{
            connectManager.closeConnection();
        }
    }

    public int getMaSPbyTenSP (String TenSP){
        int MaSP =-1;
        String query ="SELECT MaSP FROM SanPham WHERE TenSP = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,TenSP);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                MaSP = rs.getInt("MaSP");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return MaSP;
    }

    public String getTenSPbyMaSp (int maSP){
        String tenSP ="";
        String query= "SELECT TenSP FROM SanPham WHERE MaSP = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maSP);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                tenSP = rs.getString("TenSP");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }

        return tenSP;
    }
    /*public int getMaLoaibyTenLoai (String TenLoai){
        int maLoai = -1;
        String query ="SELECT MaLoai FROM LoaiSanPham WHERE MaLoai = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, TenLoai);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                maLoai = rs.getInt("MaLoai");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return maLoai;
    }*/
}
