package BUS;

import java.util.ArrayList;
import java.util.Date;

import DAO.lichSuDiemDAO;
import DTO.hoaDonDTO;
import DTO.lichSuDiemDTO;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class lichSuDiemBUS {
    private lichSuDiemDAO lichSuDiemDAO;
    public lichSuDiemBUS(){
        lichSuDiemDAO = new lichSuDiemDAO();
    }

    public ArrayList<lichSuDiemDTO> getAllLichSuDiem (){
        return lichSuDiemDAO.getAllLichSuDiem();
    }

    public boolean addLichSuDiem (ComboBox<String> cbbHinhTHuc, Label sellThanhTien, int maHD, int maKH){
        int thanhTien = (int) Double.parseDouble(sellThanhTien.getText().replace(" VND", "").replace(",", ""));
        int diemTichLuy = thanhTien/100;
        java.util.Date today = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(today.getTime());

        lichSuDiemDTO lichSuDiem = new lichSuDiemDTO();
        lichSuDiem.setMaHD(maHD);
        lichSuDiem.setMaKH(maKH);
        lichSuDiem.setNgayTichLuy(sqlDate);
        lichSuDiem.setLoaiGD(cbbHinhTHuc.getSelectionModel().getSelectedItem());
        lichSuDiem.setDiem(diemTichLuy);
        return lichSuDiemDAO.addLichSuDiem(lichSuDiem);
    }

    public ArrayList<lichSuDiemDTO> searchLichSuDiembyMaHD (int maHD, int maKH, java.sql.Date startDate, java.sql.Date endDate){
        return lichSuDiemDAO.searchLichSuDiembyMaHD(maHD, maKH, startDate, endDate);
    }

    public ArrayList<lichSuDiemDTO> searchLichSuDiemNC (TextField sellSearchLSBar, ComboBox<String> sellSearchTichDiemCB, DatePicker startLSDatePicker, DatePicker endLSDatePicker){
        int maKH = -1;
        int maHD = -1;
        String selected = sellSearchTichDiemCB.getValue();
        String value = sellSearchLSBar.getText().trim();
        if(selected!=null){
            if (selected.equals("Mã hóa đơn")) {
                try {    
                    if(!value.isEmpty())
                        maHD = Integer.parseInt(value);        
                }
                catch (NumberFormatException e) {
                    maHD = -1;
                } 
            } if(selected.equals("Mã khách hàng")){
                try {
                    if(!value.isEmpty())
                        maKH = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    maKH = -1;
                }
            }
        }

        java.sql.Date startDate = null;
        java.sql.Date endDate = null;
        if( endLSDatePicker.getValue()!=null && startLSDatePicker.getValue()== null ||
            endLSDatePicker.getValue()==null && startLSDatePicker.getValue()!=null){
            showAlert(Alert.AlertType.WARNING, "Thiếu thông tin", "Vui lòng nhập đầy đủ thông tin");
            return null;
        }

        if(startLSDatePicker.getValue() != null && endLSDatePicker.getValue() != null &&
            startLSDatePicker.getValue().isAfter(endLSDatePicker.getValue())){
            showAlert(Alert.AlertType.WARNING,"Không hợp lệ", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
            return null;
        }

        if(startLSDatePicker.getValue() !=null && endLSDatePicker.getValue() != null){
            startDate = java.sql.Date.valueOf(startLSDatePicker.getValue());
            endDate = java.sql.Date.valueOf(endLSDatePicker.getValue());
        }

        return lichSuDiemDAO.searchLichSuDiembyMaHD(maHD, maKH, startDate, endDate);
    }

     private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
