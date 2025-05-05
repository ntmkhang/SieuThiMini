package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import DTO.khachHangDTO;

public class khachHangDAO {
    
    private ConnectManager connectManager;
    public khachHangDAO(){
        connectManager = new ConnectManager();
    }

    public ArrayList<khachHangDTO> getAllKhachHang (){
        ArrayList<khachHangDTO> khachHang = new ArrayList<>();
        String query = "SELECT * FROM KhachHang WHERE Is_Deleted = 0";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                khachHang.add(new khachHangDTO(
                    rs.getInt("MaKH"),
                    rs.getString("TenKH"),
                    rs.getString("SoDienThoai"),
                    rs.getInt("DiemTichLuy")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return khachHang;
    }

    public int getMaKH (){
        int maKH = 0;
        String query = " SELECT TOP 1 FROM KhachHang ORDER BY MaKH DESC";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                maKH = rs.getInt("MaKH");
                maKH += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return maKH;
    }

    public khachHangDTO getKHbyMaKH (int maKH){
        khachHangDTO khachHang = null;
        String query ="SELECT * FROM KhachHang WHERE Is_Deleted =0 AND MaKH = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maKH);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                khachHang = new khachHangDTO();
                khachHang.setMaKH(rs.getInt("MaKH"));
                khachHang.setTenKH(rs.getString("TenKH"));
                khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
                khachHang.setDiemTichLuy(rs.getInt("DiemTichLuy"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return khachHang;
    }

    public String getTenKHbyMaKH (int maKH){
        String tenKH ="";
        String query ="SELECT TenKH FROM KhachHang WHERE Is_Deleted =0 AND MaKH = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maKH);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                tenKH = rs.getString("TenKH");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return tenKH;
    }

    public int getMaKHbySoDienThoai (String soDienThoai){
        int maKH = -1;
        String query ="SELECT MaKH FROM KhachHang WHERE Is_Deleted = 0 AND SoDienThoai = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, soDienThoai);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                maKH = rs.getInt("MaKH");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return maKH;
    }

    public int getDiemTichLuybySoDienThoai (String soDienThoai){
        int diem =0;
        String query =" SELECT DiemTichLuy FROM KhachHang WHERE Is_Deleted = 0 AND SoDienThoai = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, soDienThoai);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                diem = rs.getInt("DiemTichLuy");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        } 
        return diem;
    }

    public boolean kiemTraSoDienThoai (String soDienThoai){
        String query = "SELECT COUNT(*) FROM KhachHang WHERE Is_Deleted = 0 AND SoDienThoai = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, soDienThoai);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count >0)
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return false;
    }

    public boolean addKhachHang (khachHangDTO khachHang){
        String query ="INSERT INTO KhachHang (TenKH, SoDienThoai, DiemTichLuy, Is_Deleted) VALUES ( ?, ? , 0, 0)";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, khachHang.getTenKH());
            preparedStatement.setString(2, khachHang.getSoDienThoai());
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected>0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connectManager.closeConnection();
        }
    }

    public boolean updateDiemTichLuy (int maKH, int diemMoi){
        String query = "UPDATE KhachHang SET DiemTichLuy = ? WHERE MaKH = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, diemMoi);
            preparedStatement.setInt(2, maKH);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected >0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connectManager.closeConnection();
        }
    }

    public boolean subtractDiemTichLuy (int maKH, int diemTru){
        String query ="UPDATE KhachHang SET DiemTichLuy = DiemTichLuy - ? WHERE MaKH = ? AND DiemTichLuy >= ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, diemTru);
            preparedStatement.setInt(2, maKH);
            preparedStatement.setInt(3, diemTru);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected >0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connectManager.closeConnection();
        }
    }
}
