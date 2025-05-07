package BUS;

import DAO.LoginDAO;
import DTO.TaiKhoanDTO;

public class LoginBUS {
    private LoginDAO loginDAO;

    public LoginBUS() {
        loginDAO = new LoginDAO();
    }

    // 🔹 Kiểm tra đăng nhập Admin
    public boolean validateLogin(String username, String password) {
        TaiKhoanDTO user = loginDAO.checkLogin(username, password);
        return user != null;
    }

    public boolean isAdmin (String tenTK, String matKhau){
        return loginDAO.isAdmin(tenTK, matKhau);
    }

    public int getMaNVbyTenDangNhap (String username){
        return loginDAO.getMaNVbyTenDangNhap(username);
    }

    public String getTenNVbyMaNV (int maNV){
        return loginDAO.getTenNVbyMaNV(maNV);
    }
}