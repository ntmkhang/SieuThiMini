package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import DAO.ConnectManager;

public class NhanVienDAO {
    private ConnectManager _conn;
    public NhanVienDAO(){
        _conn = new ConnectManager();
    } 

    public String getTenNhanVienByID(int maNV) {
        String tenNV = null;  // Khởi tạo biến để lưu tên nhân viên
        String sql = "SELECT TenNV FROM NhanVien WHERE MaNV = ?";  // Câu lệnh SQL để truy vấn tên nhân viên theo MaNV
        
        try {
            _conn.openConnection();
            Connection conn = _conn.getConnection();  // Mở kết nối cơ sở dữ liệu
            PreparedStatement ps = conn.prepareStatement(sql) ;
            
            ps.setInt(1, maNV);  // Đặt giá trị của ? bằng maNV
            ResultSet rs = ps.executeQuery();  // Thực hiện truy vấn
            
            // Nếu có kết quả trả về
            if (rs.next()) {
                tenNV = rs.getString("TenNV");  // Lấy tên nhân viên
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            _conn.closeConnection();
        }
        
        return tenNV;  // Trả về tên nhân viên
    }
}
