package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.SanPhamDTO;

public class SanPhamDAO {
    private static ConnectManager _connect;

    public SanPhamDAO(){
        this._connect = new ConnectManager();
    }

    public List<SanPhamDTO> getAllList(){
        List<SanPhamDTO> productList = new ArrayList<>();
        String query = "select * from SanPham";
        try {
            _connect.openConnection();
            Connection conn = _connect.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
              productList.add(new SanPhamDTO(
                rs.getInt("MaSP"),
                rs.getString("TenSP"),
                rs.getString("MoTa"),
                rs.getInt("SoLuong"),
                rs.getString("HinhAnh"),
                rs.getDouble("GiaBan"),
                rs.getInt("MaLoai"),
                rs.getBoolean("Is_Deleted")
              ));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        _connect.closeConnection();
        return productList;
    }

    public boolean ThemSP(SanPhamDTO sPhamDTO){
        String query = "insert into SanPham(TenSP,MoTa,SoLuong,HinhAnh,GiaBan,MaLoai,Is_Deleted) values (?,?,?,?,?,?,?)";
        try {
            _connect.openConnection();
            Connection conn = _connect.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, sPhamDTO.getTenSP());
            preparedStatement.setString(2, sPhamDTO.getMoTa());
            preparedStatement.setInt(3, sPhamDTO.getSoLuong());
            preparedStatement.setString(4, sPhamDTO.getHinhAnh());
            preparedStatement.setDouble(5, sPhamDTO.getGiaBan());
            preparedStatement.setInt(6,sPhamDTO.getMaLoai());
            preparedStatement.setBoolean(7, false);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            _connect.closeConnection();
        }
    }

    public boolean SuaSP(int id, SanPhamDTO sPhamDTO) {
        String query = "UPDATE SanPham SET TenSP = ?, MoTa = ?, SoLuong = ?, HinhAnh = ?, GiaBan = ?, MaLoai = ?,Is_Deleted = ? WHERE MaSP = ?";
    
        try {
            _connect.openConnection();
            Connection conn = _connect.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query); 
            preparedStatement.setInt(8, id);
            preparedStatement.setString(1, sPhamDTO.getTenSP());
            preparedStatement.setString(2, sPhamDTO.getMoTa());
            preparedStatement.setInt(3, sPhamDTO.getSoLuong());
            preparedStatement.setString(4, sPhamDTO.getHinhAnh());
            preparedStatement.setDouble(5, sPhamDTO.getGiaBan());
            preparedStatement.setInt(6, sPhamDTO.getMaLoai());
            preparedStatement.setBoolean(7, true);
            preparedStatement.setInt(8, id); // điều kiện WHERE
    
            return preparedStatement.executeUpdate() > 0;
    
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            _connect.closeConnection();;
        }
    }
    public boolean capNhatTrangThaiSanPham(int id, boolean isDeleted) {
        String sql = "UPDATE SanPham SET Is_Deleted = ? WHERE MaSP = ?";
        try {
            _connect.openConnection();
            Connection conn = _connect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, isDeleted);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean XoaSP(int id){
        String query = "update SanPham set Is_Deleted = ? where MaSP = ?";
        try {
            _connect.openConnection();
            Connection conn = _connect.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(2, id);
            preparedStatement.setBoolean(1, false);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
             e.printStackTrace();
             return true;
        }finally{
            _connect.closeConnection();
        }
    }

    public SanPhamDTO TimKiemTheoId(int id){
        String query = "select * from SanPham where MaSP =?"; 
        try {
            _connect.openConnection();
            Connection conn = _connect.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new SanPhamDTO(
                    rs.getInt("MaSP"),
                    rs.getString("TenSP"),
                    rs.getString("MoTa"),
                    rs.getInt("SoLuong"),
                    rs.getString("HinhAnh"),
                    rs.getDouble("GiaBan"),
                    rs.getInt("MaLoai"),
                    rs.getBoolean("Is_Deleted")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            _connect.closeConnection();
        }

        return null;
    }
}
    


