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



    public boolean ThemNV(nhanVienDTO nhanvien){
        String query = "INSERT INTO NhanVien (TenNV, GioiTinh, NgaySinh, SoDienThoai, Email, DiaChi, MaChucVu, Is_Deleted) VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nhanvien.getTenNV());
            stmt.setBoolean(2, nhanvien.isGioiTinh());
            stmt.setDate(3, nhanvien.getNgaySinh());
            stmt.setString(4, nhanvien.getSoDienThoai());
            stmt.setString(5, nhanvien.getEmail());
            stmt.setString(6, nhanvien.getDiaChi());
            stmt.setInt(7, nhanvien.getMaChucVu());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return false;
    }

    public boolean XoaNV(int MaNV){
        String query = "UPDATE NhanVien SET Is_Deleted = 1 Where MaNV = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaNV);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return false;
    }

    public boolean SuaNV(nhanVienDTO nhanvien){
        boolean check = false;
        String query = "UPDATE NhanVien SET TenNV = ?, GioiTinh = ?, NgaySinh = ?, SoDienThoai = ?, Email = ?, DiaChi = ?, MaChucVu = ? " + 
                        "Where MaNV = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nhanvien.getTenNV());
            stmt.setBoolean(2, nhanvien.isGioiTinh());
            stmt.setDate(3, nhanvien.getNgaySinh());
            stmt.setString(4, nhanvien.getSoDienThoai());
            stmt.setString(5, nhanvien.getEmail());
            stmt.setString(6, nhanvien.getDiaChi());
            stmt.setInt(7, nhanvien.getMaChucVu());
            stmt.setInt(8, nhanvien.getMaNV());
            if(stmt.executeUpdate() > 0){
                check = true;
            }            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return check;
    }

    public Integer getMaNV(){
        int maNV = 0;
        String query = "Select TOP 1 MaNV From NhanVien ORDER BY MaNV DESC";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                maNV = rs.getInt("MaNV");
                maNV += 1;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return maNV;
    }

    public boolean  KTraNhanVienTonTai(int MaNV){
        String query = "Select * From NhanVien Where MaNV = ? AND Is_Deleted = 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaNV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count >0)
                    return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return false;
    }

    public nhanVienDTO getNVBangMaNV(int MaNV){
        nhanVienDTO nv = new nhanVienDTO();
        String query = "Select * " + 
                        "From NhanVien Where MaNV = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaNV);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                nv.setMaNV(rs.getInt("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setMaChucVu(rs.getInt("MaChucVu"));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return nv;
    }

    public ArrayList<nhanVienDTO> getAllNV(){
        ArrayList<nhanVienDTO> slnv = new ArrayList<nhanVienDTO>();
        String query = "Select * From NhanVien Where Is_Deleted != 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                nhanVienDTO nv = new nhanVienDTO();
                nv.setMaNV(rs.getInt(1));
                nv.setTenNV(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setSoDienThoai(rs.getString(5));
                nv.setEmail(rs.getString(6));
                nv.setDiaChi(rs.getString(7));
                nv.setMaChucVu(rs.getInt(8));
                slnv.add(nv);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return slnv;
    }

    public ArrayList<nhanVienDTO> TKiemNhanVienTheoTen(String keyword){
        ArrayList<nhanVienDTO> tknvtt = new ArrayList<nhanVienDTO>();
        String query = "Select * From NhanVien Where TenNV LIKE ? AND Is_Deleted != 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                nhanVienDTO nv = new nhanVienDTO();
                nv.setMaNV(rs.getInt(1));
                nv.setTenNV(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setSoDienThoai(rs.getString(5));
                nv.setEmail(rs.getString(6));
                nv.setDiaChi(rs.getString(7));
                nv.setMaChucVu(rs.getInt(8));
                tknvtt.add(nv);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return tknvtt;
    }

    public boolean kiemTraSoDienThoai(String soDienThoai){
        boolean check = false;
        String query = "Select COUNT(*) From NhanVien Where SoDienThoai = ? AND Is_Deleted != 1";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, soDienThoai);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                if(count > 0){
                    return true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return check;
    }

    public String getTenNVByMaNV(int MaNV){
        String tennv = null;
        String query = "Select TenNV From NhanVien Where MaNV = ?";
        try {
            connectManager.openConnection();
            Connection connection = connectManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaNV);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                tennv = rs.getString("TenNV");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally{
            connectManager.closeConnection();
        }
        return tennv;
    }
}
