package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class ThongKeDAO {
    private ConnectManager connectManager;

    public ThongKeDAO(){
        connectManager = new ConnectManager();
    }

    public ResultSet ThongKeTongThuTheoNgay(Timestamp start, Timestamp end){
        ResultSet rs = null;
        String query = "Select hd.NgayLap, hd.MaHD, sp.TenSP, cthd.SoLuong, cthd.GiaBan, (cthd.SoLuong * cthd.GiaBan) AS TongTienHang, " + 
                        "SUM(cthd.SoLuong *cthd.GiaBan) OVER() AS TongDoanhThu " +
                        "From HoaDon hd JOIN CTHoaDon cthd ON hd.MaHD = cthd.MaHD " + 
                        "JOIN SanPham sp ON cthd.MaSP = sp.MaSP " + 
                        "Where hd.NgayLap BETWEEN ? AND ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setTimestamp(1, start);
            stmt.setTimestamp(2, end);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet ThongKeTatCaTongThu(){
        ResultSet rs = null;
        String query = "Select hd.NgayLap, hd.MaHD, sp.TenSp, cthd.SoLuong, cthd.GiaBan, (cthd.SoLuong * cthd.GiaBan) AS TongTienHang," + 
                        "SUM(cthd.SoLuong * cthd.GiaBan) OVER() AS TongDoanhThu " + 
                        "From HoaDon hd JOIN CTHoaDon cthd ON hd.MaHD = cthd.MaHD " + 
                        "JOIN SanPham sp ON cthd.MaSP = sp.MaSP";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet ThongKeTongChiTheoNgay(Timestamp start, Timestamp end){
        ResultSet rs = null;
        String query = "Select pn.NgayLap, pn.MaPN, sp.TenSP, ctpn.MaSP, ctpn.SoLuong, ctpn.GiaNhap, (ctpn.SoLuong * ctpn.GiaNhap) AS TongChiTieu " + 
                        "From PhieuNhap pn JOIN CTPhieuNhap ctpn ON pn.MaPN = ctpn.MaPN " + 
                        "JOIN SanPham sp ON ctpn.MaSP = sp.MaSP " + 
                        "Where pn.NgayLap BETWEEN ? AND ? ";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setTimestamp(1, start);
            stmt.setTimestamp(2, end);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet ThongKeTatCaTongChi(){
        ResultSet rs = null;
        String query = "Select pn.NgayLap, pn.MaPN, sp.TenSP, ctpn.MaSP, ctpn.SoLuong, ctpn.GiaNhap, (ctpn.SoLuong * ctpn.GiaNhap) AS TongChiTieu " + 
                        "From PhieuNhap pn JOIN CTPhieuNhap ctpn ON pn.MaPN = ctpn.MaPN " + 
                        "JOIN SanPham sp ON ctpn.MaSP = sp.MaSP";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet ThongKeLoiNhuanTheoNgay(Timestamp start, Timestamp end){
        ResultSet rs = null;
        String query = "SELECT hd.NgayLap, hd.MaHD, sp.TenSP, cthd.SoLuong, ctpn.GiaNhap, cthd.GiaBan, " +
						"((cthd.SoLuong * cthd.GiaBan) - (cthd.SoLuong * ctpn.GiaNhap)) AS LoiNhuan " +
						"FROM HoaDon hd " +
						"JOIN CTHoaDon cthd ON hd.MaHD = cthd.MaHD " +
						"JOIN SanPham sp ON cthd.MaSP = sp.MaSP " +
						"JOIN CTPhieuNhap ctpn ON ctpn.MaSP = sp.MaSP " +
	                    "WHERE hd.NgayLap BETWEEN ? AND ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setTimestamp(1, start);
            stmt.setTimestamp(2, end);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet ThongKeTatCaLoiNhuan(){
        ResultSet rs = null;
        String query = "SELECT hd.NgayLap, hd.MaHD, sp.TenSP, cthd.SoLuong, ctpn.GiaNhap, cthd.GiaBan, " +
					"((cthd.SoLuong * cthd.GiaBan) - (cthd.SoLuong * ctpn.GiaNhap)) AS LoiNhuan " +
					"FROM HoaDon hd " +
					"JOIN CTHoaDon cthd ON hd.MaHD = cthd.MaHD " +
					"JOIN SanPham sp ON cthd.MaSP = sp.MaSP " +
					"JOIN CTPhieuNhap ctpn ON ctpn.MaSP = sp.MaSP ";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return rs;
    }

    
}
