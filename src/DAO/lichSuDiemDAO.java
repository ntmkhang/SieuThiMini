package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import DTO.lichSuDiemDTO;  
public class lichSuDiemDAO {
    private ConnectManager connectManager;

    public lichSuDiemDAO(){
        connectManager = new ConnectManager();
    }

    public ArrayList<lichSuDiemDTO> getAllLichSuDiem (){
        ArrayList<lichSuDiemDTO> lichSuDiem = new ArrayList<>();
        String query ="SELECT * FROM LichSuDiem";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lichSuDiem.add(new lichSuDiemDTO(
                    rs.getInt("MaGiaoDich"),
                    rs.getInt("MaKH"),
                    rs.getInt("MaHD"),
                    rs.getInt("Diem"),
                    rs.getDate("NgayTichLuy"),
                    rs.getString("LoaiGD")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return lichSuDiem;
    }

    public boolean addLichSuDiem (lichSuDiemDTO lichSuDiem){
        String query = "INSERT INTO LichSuDiem (MaKH, MaHD, Diem, NgayTichLuy, LoaiGD) VALUES (?, ?, ?, ?, ?)";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, lichSuDiem.getMaKH());
            preparedStatement.setInt(2, lichSuDiem.getMaHD());
            preparedStatement.setInt(3, lichSuDiem.getDiem());
            preparedStatement.setDate(4, lichSuDiem.getNgayTichLuy());
            preparedStatement.setString(5, lichSuDiem.getLoaiGD());
            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1); 
                }
                return true;
            } 
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            connectManager.closeConnection();
        }
    }

    public ArrayList<lichSuDiemDTO> searchLichSuDiembyMaHD (int maHD, int maKH, java.sql.Date startDate, java.sql.Date endDate){
        ArrayList<lichSuDiemDTO> lichSuDiem = new ArrayList<>();
        String query ="SELECT * FROM LichSuDiem WHERE 1=1";
        int index = 1;
        if (maKH!=-1 ){
            query += " AND MaKH =?";
        }

        if(maHD!=-1){
            query += " AND MaHD =?";
        }

        if (startDate!= null && endDate!=null)
            query += " AND NgayTichLuy BETWEEN ? AND ? ";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(maHD !=-1){
                preparedStatement.setInt(index++, maHD);
            }
            if(maKH !=-1){
                preparedStatement.setInt(index++, maKH);
            }
            if (startDate!=null && endDate != null){
                preparedStatement.setDate(index++, startDate);
                preparedStatement.setDate(index++, endDate);
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lichSuDiem.add(new lichSuDiemDTO(
                    rs.getInt("MaGiaoDich"),
                    rs.getInt("MaKH"),
                    rs.getInt("MaHD"),
                    rs.getInt("Diem"),
                    rs.getDate("NgayTichLuy"),
                    rs.getString("LoaiGD")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return lichSuDiem;
    }
}
