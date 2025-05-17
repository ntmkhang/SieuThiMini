package BUS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import DAO.hoaDonDAO;
import DTO.CThoaDonDTO;
import DTO.hoaDonDTO;
import DTO.sanPhamDTO;
import BUS.sanPhamBUS;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class hoaDonBUS {
    private hoaDonDAO hoaDonDAO;
    private sanPhamBUS sanPhamBUS;

    public hoaDonBUS(){
        hoaDonDAO = new hoaDonDAO();
    }

    public ArrayList<hoaDonDTO> getAllHoaDon (){
        return hoaDonDAO.getAllHoaDon();
    }

    public int addHoaDon (hoaDonDTO hoadon){
        return hoaDonDAO.themHoaDon(hoadon);
    }

    public int addHoaDonNC (ComboBox<String> sellHinhThucCB, Label sellTongTien, Label sellThanhTien, Label sellTienGiam, Label sellTienTraLai, TextField sellTienKhachTra, int maNV, int maKH){
        
        java.util.Date today = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(today.getTime());

        int tongTien = (int) Double.parseDouble(sellTongTien.getText().replace(" VND", "").replace(",", ""));
        int thanhTien = (int) Double.parseDouble(sellThanhTien.getText().replace(" VND", "").replace(",", ""));
        int tienGiam = (int) Double.parseDouble(sellTienGiam.getText().replace(" VND", "").replace(",", ""));
        int tienTraLai = (int) Double.parseDouble(sellTienTraLai.getText().replace(" VND", "").replace(",", ""));

        if (tongTien == 0 ){
            showAlert(Alert.AlertType.WARNING, "Chưa chọn sản phẩm!", "Vui lòng chọn sản phẩm!");
            return 0;
        }

        if (sellTienKhachTra.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Chưa trả tiền!", "Vui lòng nhập tiền khách trả!");
            return 0;
        }

        int tienKhachTra = (int) Double.parseDouble(sellTienKhachTra.getText().replace(" VND", "").replace(",", ""));

        if (tienTraLai < 0) {
            showAlert(Alert.AlertType.WARNING, "Chưa trả đủ!", "Khách hàng chưa trả đủ tiền!");
            return 0;
        }
        // Mặc định mã khách hàng là 0 nếu không có số điện thoại
        hoaDonDTO hoadon = new hoaDonDTO();
        hoadon.setNgayLap(sqlDate);
        hoadon.setHinhThuc(sellHinhThucCB.getSelectionModel().getSelectedItem());
        hoadon.setThanhTien(thanhTien);
        hoadon.setTienGiam(tienGiam);
        hoadon.setTienKhachDua(tienKhachTra);
        hoadon.setTienTraLai(tienTraLai);
        hoadon.setTongTien(tongTien);
        hoadon.setMaNV(maNV);
        hoadon.setMaKH(maKH);
        
        return hoaDonDAO.themHoaDon(hoadon);
    }

    public int getMaHD (){
        return hoaDonDAO.getMaHD();
    }

    public hoaDonDTO getHoaDonbyMaHD (int maHD){
        return hoaDonDAO.getHoaDonbyMaHD(maHD);
    }

    public ArrayList<hoaDonDTO> searchHoaDonNC (TextField sellSearchHDBar, ComboBox<String> sellSearchHoaDonCB, DatePicker startHDDatePicker, DatePicker endHDDatePicker){
        String selected = sellSearchHoaDonCB.getValue();
        int maKH = -1;
        int maHD = -1;
        String value = sellSearchHDBar.getText().trim();
        if (selected!= null){
            if (selected.equals("Mã hóa đơn")){
                try {
                    if(!value.isEmpty()){
                        maHD = Integer.parseInt(value);
                    }
                } catch (NumberFormatException e) {
                        maHD = -1;
                }
            }
            if (selected.equals("Mã khách hàng")){
                try {
                    if(!value.isEmpty()){
                        maKH = Integer.parseInt(value);
                    }
                } catch (NumberFormatException e) {
                    maKH =-1;
                }
            }
        }
        java.sql.Date startDate = null;
        java.sql.Date endDate = null;

        if(startHDDatePicker.getValue() ==null && endHDDatePicker.getValue()!=null || 
        startHDDatePicker.getValue() !=null && endHDDatePicker.getValue()==null){
            showAlert(Alert.AlertType.WARNING,"Thiếu thông tin", "Vui lòng nhập đầy đủ thông tin");
            return null;
        }

        if (startHDDatePicker.getValue()!=null && endHDDatePicker.getValue()!=null && 
        startHDDatePicker.getValue().isAfter(endHDDatePicker.getValue()) ) {
            showAlert(Alert.AlertType.WARNING, "Không hợp lệ", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
            return null;
        }

        if(startHDDatePicker.getValue() !=null && endHDDatePicker.getValue() != null){
            startDate = java.sql.Date.valueOf(startHDDatePicker.getValue());
            endDate = java.sql.Date.valueOf(endHDDatePicker.getValue());
        }

        return hoaDonDAO.searchHoaDon(maHD, maKH, startDate, endDate);
    }

    public ArrayList<hoaDonDTO> searchHoaDon (int maHD, int maKH, java.sql.Date startDate, java.sql.Date endDate){
        return hoaDonDAO.searchHoaDon(maHD, maKH, startDate, endDate);
    }

    public ArrayList<CThoaDonDTO> getAllCTHoaDon (){
        return hoaDonDAO.getAllCThoaDon();
    }

    public ArrayList<CThoaDonDTO> getCThoaDonbyMaHD (int maHD){
        return hoaDonDAO.getCThoaDonbyMaHD(maHD);
    }

    public boolean addCThoaDonNC (sanPhamDTO sanPhamDTO, int maHD ){
            CThoaDonDTO cthd = new CThoaDonDTO();
            cthd.setMaHD(maHD);
            cthd.setTenSP(sanPhamDTO.getTenSP());
            cthd.setSoLuong(sanPhamDTO.getSoLuong());
            cthd.setGiaBan(sanPhamDTO.getGia());
            cthd.setMaSP(sanPhamDTO.getMaSP());
            cthd.setThanhTien(sanPhamDTO.getSoLuong()*sanPhamDTO.getGia()); 
            return hoaDonDAO.themCThoaDon(cthd);
    }

    public boolean addCThoaDon (CThoaDonDTO cthd){
        return hoaDonDAO.themCThoaDon(cthd);
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
