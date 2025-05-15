package Controller;

import javax.swing.JOptionPane;

import BUS.khachHangBUS;
import DTO.khachHangDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SuaKHController {
    private Runnable onSuaSuccessCallback;
    @FXML
    private Label btnClosePopUps;

    @FXML
    private Label btnThem;

    @FXML
    private TextField txtDiemTichLuy;

    @FXML
    private TextField txtMaKH;

    @FXML
    private TextField txtSDT;

    @FXML
    private TextField txtTenKH;

    @FXML
    private Label validDiemTichLuy;

    @FXML
    private Label validMaKH;

    @FXML
    private Label validSDT;

    @FXML
    private Label validTenKH;

    String SDT = "";

    // Setter để nhận callback từ controller bên ngoài
    public void setOnSuaSuccessCallback(Runnable callback) {
        this.onSuaSuccessCallback = callback;
    }

    public void setMaKH(int maKH) {
        txtMaKH.setText(String.valueOf(maKH)); // Hiển thị maKH trong TextField
        loadDuLieuTuBang();
    }

    public void loadDuLieuTuBang() {
        khachHangBUS kh = new khachHangBUS();
        int maKhachHang = Integer.parseInt(txtMaKH.getText());

        khachHangDTO khDTO = kh.getKhachHangbyMaKH(maKhachHang);
        txtTenKH.setText(khDTO.getTenKH());
        txtSDT.setText(khDTO.getSoDienThoai());
        txtDiemTichLuy.setText("" + khDTO.getDiemTichLuy());

        SDT = txtSDT.getText();
    }

    private void closePopup() {
        // Lấy đối tượng Stage của cửa sổ hiện tại và đóng nó
        Stage stage = (Stage) btnClosePopUps.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        khachHangBUS kh = new khachHangBUS();
        txtMaKH.setText("" + kh.getMaKH());
    }

    @FXML
    void closePopUps(MouseEvent event) {
        Stage stage = (Stage) btnClosePopUps.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Click(MouseEvent event) {
        khachHangBUS khBUS = new khachHangBUS();
        validDiemTichLuy.setText("");
        validSDT.setText("");
        validTenKH.setText("");

        boolean flag = true;
        String ten = txtTenKH.getText();
        String soDienThoai = txtSDT.getText();
        int diemTichLuy = Integer.parseInt(txtDiemTichLuy.getText());

        if (flag) {
            if (ten.isEmpty()) {
                validTenKH.setText("Không Được Bỏ Trống");
                flag = false;
            } else if (!ten.matches("^[A-ZÀ-Ỹ][a-zà-ỹ]*(\\s[A-ZÀ-Ỹ][a-zà-ỹ]*)*$")) {
                validTenKH.setText("Tên Khách Hàng Không Đúng Định Dạng! (Ví dụ: Nguyễn Văn A)");
                flag = false;
            }
            if (soDienThoai.isEmpty()) {
                validSDT.setText("Không Được Bỏ Trống.");
                flag = false;
            } else if (!soDienThoai.matches("^0[0-9]{9}$")) {
                validSDT.setText("Không Đúng Định Dạng! Hãy Nhập Lại (Ví dụ: 0987654321).");
                flag = false;
            } else if (!SDT.equals(soDienThoai) && khBUS.kiemTraSoDienThoai(soDienThoai)) {
                validSDT.setText("Đã Có Số Điện Thoại " + soDienThoai + "! Hãy Nhập Lại.");
                flag = false;
            }
            if (!txtDiemTichLuy.getText().matches("^[0-9]+$")) {
                validDiemTichLuy.setText("Không Đúng Định Dạng (Chỉ Được Nhập Số)! Hãy Nhập Lại.");
                flag = false;
            }

        }
        if (flag) {
            khachHangDTO kh = new khachHangDTO();
            kh.setMaKH(Integer.parseInt(txtMaKH.getText()));
            kh.setTenKH(ten);
            kh.setSoDienThoai(soDienThoai);
            kh.setDiemTichLuy(diemTichLuy);
            boolean check = khBUS.updateKhachHangDTO(kh);
            if (check) {
                JOptionPane.showMessageDialog(null, "Sửa Khách Hàng Thành Công.");
                if (onSuaSuccessCallback != null) {
                    onSuaSuccessCallback.run(); // Gọi callback
                }
                closePopup();
            } else {
                JOptionPane.showMessageDialog(null, "Sửa Khách Hàng Thất Bại.");
            }
        }
    }
}