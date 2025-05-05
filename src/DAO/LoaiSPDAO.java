package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import DTO.LoaiSanPhamDTO;

public class LoaiSPDAO {
    private static ConnectManager _connect;

    public LoaiSPDAO(){
        _connect = new ConnectManager();
    }

    public List<LoaiSanPhamDTO> getAllList(){
        List<LoaiSanPhamDTO> list = new ArrayList<>();
        String query = "select * from LoaiSanPham";
        try {
        _connect.openConnection();
        Connection conn = _connect.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            list.add(new LoaiSanPhamDTO(
            rs.getInt("MaLoai"),
            rs.getString("TenLoai"),
            rs.getString("MoTa"),
            rs.getBoolean("Is_Deleted"))
            );
        }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        _connect.closeConnection();
        return list;
    }
    public boolean themLoai(LoaiSanPhamDTO loaiSanPhamDTO){
        String query = "insert into LoaiSanPham(MaLoai, TenLoai,MoTa,Is_Deleted) values (?,?,?,?) ";
        try {
            _connect.openConnection();
            Connection conn =_connect.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, loaiSanPhamDTO.getMaLoai());
            preparedStatement.setString(2, loaiSanPhamDTO.getTenLoai());
            preparedStatement.setString(3, loaiSanPhamDTO.getMoTa());
            preparedStatement.setBoolean(4, false);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }finally{
            _connect.closeConnection();
        }
    }

    public boolean SuaLoaiSP(LoaiSanPhamDTO loaiSanPham ){
        String query = "update LoaiSanPham set TenLoai = ?, Mota = ? where MaLoai = ?";
        try {
            _connect.openConnection();
            Connection conn = _connect.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, loaiSanPham.getTenLoai());
            preparedStatement.setString(2,loaiSanPham.getMoTa());
            preparedStatement.setInt(3, loaiSanPham.getMaLoai());

            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getName(int id){
        String query = "select TenLoai from LoaiSanPham where MaLoai = ?";
        try {
            _connect.openConnection();
            Connection conn = _connect.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getString("TenLoai");
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }finally{
            _connect.closeConnection();
        }

        return null;
    }

    public Boolean khoaPL(int id, Boolean isDeleted){
        String query = "update LoaiSanPham set Is_Deleted = ? where MaLoai =?";
        try {
            _connect.openConnection();
            Connection conn = _connect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setBoolean(1, isDeleted);
            stmt.setInt(2, id);
            return stmt.executeUpdate()>0;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }
}
