package DAO;

import DTO.CTHoaDonDTO;
import DTO.HoaDonDTO;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private static ConnectManager _conn;
    public HoaDonDAO(){
        _conn = new ConnectManager();
    }
    public List<HoaDonDTO> getAllHoaDon() {
        List<HoaDonDTO> list = new ArrayList<>();
        String sql = "SELECT * from HoaDon";

        try {
            _conn.openConnection();
            Connection conn = _conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getInt("MaHD"));
                Timestamp timestamp = rs.getTimestamp("NgayLap");
                if (timestamp != null) {
                    hd.setNgayLap(timestamp.toLocalDateTime().toLocalDate());
                }
                hd.setHinhThuc(rs.getString("HinhThuc"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setThanhTien(rs.getDouble("ThanhTien"));
                hd.setTienKhachDua(rs.getDouble("TienKhachDua"));
                hd.setTienTraLai(rs.getDouble("TienTraLai"));
                hd.setMaNV(rs.getInt("MaNV"));
                hd.setMaKH(rs.getInt("MaKH"));
                hd.setDeleted(rs.getBoolean("Is_Deleted"));
                list.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<CTHoaDonDTO> getALLCtHoaDon(){
        List<CTHoaDonDTO> list = new ArrayList<>();
        String query = "select * from CTHoaDon";
        try {
            _conn.openConnection();
            Connection conn = _conn.getConnection();
            PreparedStatement stmt =conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                CTHoaDonDTO newCtHoaDon= new CTHoaDonDTO();
                newCtHoaDon.setMaHD(rs.getInt("MaHD"));
                newCtHoaDon.setMaSP(rs.getInt("MaSP"));
                newCtHoaDon.setSoLuong(rs.getInt("SoLuong"));
                newCtHoaDon.setGiaBan(rs.getDouble("GiaBan"));
                list.add(newCtHoaDon);
            }
            return list;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }finally{
            _conn.closeConnection();
        }
    }
  
        // Các phương thức truy xuất dữ liệu, bao gồm lấy danh sách CTHoaDon theo MaHD
    public List<CTHoaDonDTO> getCTHoaDonsByMaHD(int maHD) {
            List<CTHoaDonDTO> list = new ArrayList<>();
            String sql = "SELECT MaHD, MaSP, SoLuong, GiaBan FROM CTHoaDon WHERE MaHD = ?";
            try {
                _conn.openConnection();
                Connection conn = _conn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, maHD);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    CTHoaDonDTO cthd = new CTHoaDonDTO(
                            rs.getInt("MaHD"),
                            rs.getInt("MaSP"),
                            rs.getInt("SoLuong"),
                            rs.getDouble("GiaBan")
                    );
                    list.add(cthd);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                _conn.closeConnection();
            }
            return list;
        }

       

    // Phương thức tìm kiếm hóa đơn theo ID và ngày
    public List<HoaDonDTO> searchHoaDonsByIdAndDate(String idText, LocalDate selectedDate) {
        List<HoaDonDTO> hoaDons = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM HoaDon WHERE 1=1");
    
        // Cập nhật điều kiện tìm kiếm
        if (idText != null && !idText.isEmpty()) {
            query.append(" AND MaHD = ?");
        }
        if (selectedDate != null) {
            query.append(" AND [NgayLap] = ?");
        }
    
        try {
            _conn.openConnection();
            Connection conn = _conn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query.toString());
    
            // Gán tham số
            int indexParam = 1;
            if (idText != null && !idText.isEmpty()) {
                stmt.setInt(indexParam++, Integer.parseInt(idText));
            }
            if (selectedDate != null) {
                stmt.setDate(indexParam++, java.sql.Date.valueOf(selectedDate));
            }
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO(); // hoặc HoaDonDTO tùy bạn dùng class nào
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setNgayLap(rs.getDate("NgayLap").toLocalDate());
                hd.setHinhThuc(rs.getString("HinhThuc"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setTienTraLai(rs.getDouble("TienGiam"));
                hd.setThanhTien(rs.getDouble("ThanhTien"));
                hd.setTienKhachDua(rs.getDouble("TienKhachDua"));
                hd.setTienTraLai(rs.getDouble("TienTraLai"));
                hd.setMaNV(rs.getInt("MaNV"));
                hd.setMaKH(rs.getInt("MaKH"));
                hd.setDeleted(rs.getBoolean("Is_Deleted"));
                hoaDons.add(hd);
            }
    
            rs.close();
            stmt.close();
            _conn.closeConnection();
    
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi hoặc xử lý phù hợp
        }
    
        return hoaDons;
    }
    


        
}
    

    



