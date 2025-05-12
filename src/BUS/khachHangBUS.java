package BUS;

import java.util.ArrayList;

import DAO.khachHangDAO;
import DTO.khachHangDTO;

public class khachHangBUS {
    private khachHangDAO khachHangDAO;

    public khachHangBUS(){
        khachHangDAO = new khachHangDAO();
    }

    public ArrayList<khachHangDTO> getAllKhachHang (){
        return khachHangDAO.getAllKhachHang();
    }

    public int addKhachHang (khachHangDTO khachHang){
        return khachHangDAO.addKhachHang(khachHang);
    }

    public int getMaKH (){
        return khachHangDAO.getMaKH();
    }

    public int getMaKHbySoDienThoai (String SoDienThoai){
        return khachHangDAO.getMaKHbySoDienThoai(SoDienThoai);
    }

    public int getDiemTichLuybySoDienThoai (String SoDienThoai){
        return khachHangDAO.getDiemTichLuybySoDienThoai(SoDienThoai);
    }

    public khachHangDTO getKhachHangbyMaKH(int maKH){
        return khachHangDAO.getKHbyMaKH(maKH);
    }

    public String getTenKHbyMaKH(int maKH){
        return khachHangDAO.getTenKHbyMaKH(maKH);
    }

    public boolean kiemTraSoDienThoai (String SoDienThoai){
        return khachHangDAO.kiemTraSoDienThoai(SoDienThoai);
    }

    public boolean updateDiemTichLuy (int maKH, int diemMoi, String soDienThoai){
        try {
            int diemHienTai = khachHangDAO.getDiemTichLuybySoDienThoai(soDienThoai);
            int tongDiem = diemHienTai + diemMoi;
            System.out.println("diem hien tai: " + diemHienTai);
            System.out.println(("diem moi: " + diemMoi));
            System.out.println("tong diem: " + tongDiem);
            return khachHangDAO.updateDiemTichLuy(maKH, tongDiem);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public boolean subtractDiemTichLuy (int maKH, int diemTru){
        return khachHangDAO.subtractDiemTichLuy(maKH, diemTru);
    }

    public boolean deleteKhachHangDTO ( int MaKH){
        return khachHangDAO.deleteKhachHangDTO(MaKH);
    }

    public boolean updateKhachHangDTO ( khachHangDTO khachHangDTO){
        return khachHangDAO.updateKhachHangDTO(khachHangDTO);
    }
}
