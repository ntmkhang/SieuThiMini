package Controller;

import javax.swing.JOptionPane;

import BUS.khachHangBUS;
import DTO.khachHangDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class formThemKhachHangController {

    @FXML
    private Pane containSave;

    @FXML
    private Label labelTitle;

    @FXML
    private Button sellHuyBTN;

    @FXML
    private TextField sellInputSoDienThoai;

    @FXML
    private TextField sellInputTenKhachHang;

    @FXML
    private Button sellLuuBTN;

    @FXML
    private Label warnSdt;

    @FXML
    private Label warnTen;

    private khachHangBUS khachHangBUS = new khachHangBUS();
    private QLBHController qlbh;

    public void setQLBHController (QLBHController controller){
        this.qlbh=controller;
    }

    @FXML
    void handleHuyBTN(ActionEvent event) {
        Stage stage = (Stage) sellHuyBTN.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleLuuBTN(ActionEvent event) {
        if(khachHangBUS.kiemTraSoDienThoai(sellInputSoDienThoai.getText().trim())){
            showAlert(Alert.AlertType.ERROR, "Đã tồn tại!", "Đã tồn tại khách hàng!");
            return;
        }
        
        if (sellInputTenKhachHang.getText().trim().isEmpty() || sellInputSoDienThoai.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Thiếu thông tin!", "Vui lòng nhập đầy đủ thông tin khách hàng!");
            return;
        }

        if (!isValidStringEntry(sellInputTenKhachHang.getText().trim())){
            showAlert(Alert.AlertType.ERROR, "Sai cú pháp!", "Tên khách hàng không được chứa số và ký tự đặc biệt!");
            return;
        }

        if (!isValidNumberEntry(sellInputSoDienThoai.getText().trim())){
            showAlert(Alert.AlertType.ERROR, "Sai cú pháp!", "Số điện thoại không được chứa ký tự khác!");
            return;
        }

        if(!isValidPhoneNumber(sellInputSoDienThoai.getText().trim())){
            showAlert(Alert.AlertType.ERROR, "Sai cú pháp!", "Số ký tự vượt quá số điện thoại!");
            return;
        }

        khachHangDTO khachHang = new khachHangDTO();
        khachHang.setTenKH(sellInputTenKhachHang.getText());
        khachHang.setSoDienThoai(sellInputSoDienThoai.getText());
        khachHang.setDiemTichLuy(0);

        boolean flag = khachHangBUS.addKhachHang(khachHang);

        if (flag){
            showAlert(Alert.AlertType.INFORMATION, "Thành công!", "Thêm khách hàng thành công!");
            qlbh.addKhachHang(sellInputSoDienThoai.getText().trim());
            closePopup();
        } else{
            showAlert(Alert.AlertType.ERROR, "Thất bại!", "Thêm khách hàng thất bại!");
        }
    }

     private void closePopup() {
        // Lấy đối tượng Stage của cửa sổ hiện tại và đóng nó
        Stage stage = (Stage) sellLuuBTN.getScene().getWindow();
        stage.close();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidNumberEntry (String number){
        //Kiểm tra số điện thoại chỉ gồm số 
        return number.matches("\\d*");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Kiểm tra số điện thoại phải có 10 chữ số
        return phoneNumber.matches("\\d{10}");
    }

    private boolean isValidStringEntry (String string){
        //kiểm tra dữ liệu nhập vào chỉ bao gồm ký tự và khoảng trắng
        return string.matches("^[\\p{L}\\s]+$");
    }
}

