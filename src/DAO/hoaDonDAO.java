package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import BUS.sanPhamBUS;
import DTO.CThoaDonDTO;
import DTO.hoaDonDTO;

public class hoaDonDAO {

    private ConnectManager connectManager;
    public hoaDonDAO (){
        connectManager = new ConnectManager();
    }

    public ArrayList<hoaDonDTO> getAllHoaDon (){
        ArrayList<hoaDonDTO> hoaDon = new ArrayList<>();
        String query ="SELECT * FROM HoaDon WHERE Is_Deleted =0";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                hoaDon.add(new hoaDonDTO(
                    rs.getInt("MaHD"),
                    rs.getInt("MaNV"),
                    rs.getInt("MaKH"),
                    rs.getDate("NgayLap"),
                    rs.getString("HinhThuc"),
                    rs.getInt("TongTien"),
                    rs.getInt("TienGiam"),
                    rs.getInt("ThanhTien"),
                    rs.getInt("TienKhachDua"),
                    rs.getInt("TienTraLai"),
                    rs.getInt("Is_Deleted")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return hoaDon;
    }

    public ArrayList<CThoaDonDTO> getAllCThoaDon (){
        ArrayList<CThoaDonDTO> CThoaDon = new ArrayList<>();
        String query ="SELECT * FROM CTHoaDon";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                CThoaDon.add(new CThoaDonDTO(
                    rs.getInt("MaHD"),
                    rs.getInt("MaSP"),
                    rs.getInt("SoLuong"),
                    rs.getInt("GiaBan"),
                    rs.getString("TenSP")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return CThoaDon;
    }

    public int getMaHD (){
        int maHD = -1;
        String query ="SELECT TOP 1 MaHD FROM HoaDon ORDER BY MaHD DESC";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                maHD = rs.getInt("MaHD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        } 
        return maHD;
    }
    
    public int themHoaDon (hoaDonDTO hoaDon){
        int maHD = -1;
        String query ="INSERT INTO HoaDon(NgayLap, HinhThuc, TongTien, TienGiam, ThanhTien, TienKhachDua, TienTraLai, MaNV, MaKH, Is_Deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, new java.sql.Date(hoaDon.getNgayLap().getTime()));
            preparedStatement.setString(2,hoaDon.getHinhThuc());
            preparedStatement.setInt(3,hoaDon.getTongTien());
            preparedStatement.setInt(4,hoaDon.getTienGiam());
            preparedStatement.setInt(5, hoaDon.getThanhTien());
            preparedStatement.setInt(6, hoaDon.getTienKhachDua());
            preparedStatement.setInt(7, hoaDon.getTienTraLai());
            preparedStatement.setInt(8, hoaDon.getMaNV());
            if(hoaDon.getMaKH() == 0){
                preparedStatement.setNull(9, java.sql.Types.INTEGER);
            } else{
                preparedStatement.setInt(9, hoaDon.getMaKH());
            }
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected >0 ){
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()){
                    maHD = rs.getInt(1);
                }
            }
           // return rowAffected >0;
        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        } finally {
            connectManager.closeConnection();
        }

        return maHD;
    }

    public boolean themCThoaDon (CThoaDonDTO cthd){
        String query ="INSERT INTO CTHoaDon (MaHD, MaSP, SoLuong, GiaBan) VALUES (?, ?, ?, ?)";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cthd.getMaHD());
            preparedStatement.setInt(2,cthd.getMaSP());
            preparedStatement.setInt(3, cthd.getSoLuong());
            preparedStatement.setInt(4, cthd.getGiaBan());
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected >0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally{
            connectManager.closeConnection();
        }
    }

    public ArrayList<hoaDonDTO> searchHoaDon (int maHD, int maKH, java.sql.Date startDate, java.sql.Date endDate){
        ArrayList<hoaDonDTO> hoaDon = new ArrayList<>();
        String query = "SELECT * FROM HoaDon WHERE Is_Deleted = 0";
        if (maHD!= -1){
            query += " AND MaHD = ?";
        }

        if (maKH!=-1){
            query += " AND MaKH = ?";
        }

        if (startDate!=null && endDate!=null){
            query += " AND NgayLap BETWEEN ? AND ? ";
        }
        
        int index =1;
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if (maHD != -1){
                preparedStatement.setInt(index++, maHD);
            }

            if (maKH!=-1){
                preparedStatement.setInt(index++, maKH);
            }

            if (startDate!=null && endDate != null){
                preparedStatement.setDate(index++, startDate);
                preparedStatement.setDate(index++, endDate);

            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                hoaDon.add(new hoaDonDTO(
                    rs.getInt("MaHD"),
                    rs.getInt("MaNV"),
                    rs.getInt("MaKH"),
                    rs.getDate("NgayLap"),
                    rs.getString("HinhThuc"),
                    rs.getInt("TongTien"),
                    rs.getInt("TienGiam"),
                    rs.getInt("ThanhTien"),
                    rs.getInt("TienKhachDua"),
                    rs.getInt("TienTraLai"),
                    rs.getInt("Is_Deleted")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }

        return hoaDon;
    }

    public ArrayList<CThoaDonDTO> getCThoaDonbyMaHD (int maHD){
        ArrayList<CThoaDonDTO> CThoaDon = new ArrayList<>();
        String query ="SELECT C.MaHD, C.MaSP, C.SoLuong, C.GiaBan FROM CTHoaDon C WHERE C.MaHD = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maHD);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int MaHD = rs.getInt("MaHD");
                int maSP = rs.getInt("MaSP");
                sanPhamBUS sanPham = new sanPhamBUS();
                String tenSP = sanPham.getTenSPbyMaSP(maSP);
                int soLuong = rs.getInt("SoLuong");
                int giaBan = rs.getInt("GiaBan");
                int thanhTien = soLuong * giaBan;
                CThoaDonDTO cthd = new CThoaDonDTO(MaHD,maSP,soLuong,giaBan,tenSP, thanhTien);
                CThoaDon.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return CThoaDon;
    }

    public hoaDonDTO getHoaDonbyMaHD (int maHD){
        hoaDonDTO hoaDon = null;
        String query ="SELECT * FROM HoaDon WHERE Is_Deleted = 0 AND MaHD = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maHD);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                    hoaDon = new hoaDonDTO();
                    hoaDon.setMaHD(rs.getInt("MaHD"));
                    hoaDon.setMaNV(rs.getInt("MaNV"));
                    hoaDon.setMaKH(rs.getInt("MaKH"));
                    hoaDon.setNgayLap(rs.getDate("NgayLap"));
                    hoaDon.setHinhThuc(rs.getString("HinhThuc"));
                    hoaDon.setTongTien(rs.getInt("TongTien"));
                    hoaDon.setTienGiam(rs.getInt("TienGiam"));
                    hoaDon.setThanhTien(rs.getInt("ThanhTien"));
                    hoaDon.setTienKhachDua(rs.getInt("TienKhachDua"));
                    hoaDon.setTienTraLai(rs.getInt("TienTraLai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectManager.closeConnection();
        }
        return hoaDon;
    }
}
