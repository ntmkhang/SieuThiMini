package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class loaiSanPhamDAO {
    private ConnectManager connectManager;
    
    public loaiSanPhamDAO (){
        connectManager = new ConnectManager();
    }
    
    public ArrayList<String> getAllLoaiSanPham (){
        ArrayList<String> loaiSanPham = new ArrayList<>();
        String query = "SELECT TenLoai FROM LoaiSanPham";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                loaiSanPham.add(rs.getString("TenLoai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return loaiSanPham;
    }

    public int getMaLoaibyTenLoai (String TenLoai){
        int maLoai = -1;
        String query ="SELECT MaLoai FROM LoaiSanPham WHERE TenLoai LIKE ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"%" +TenLoai +"%");
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                maLoai = rs.getInt("MaLoai");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return maLoai;
    }
}
