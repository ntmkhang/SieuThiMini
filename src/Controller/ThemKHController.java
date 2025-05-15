package Controller;

import javax.swing.JOptionPane;

import BUS.khachHangBUS;
import DTO.khachHangDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ThemKHController {
    private Runnable onAddSuccessCallback;
    @FXML
    private Label btnClosePopUps;


    @FXML
    private TextField txtDiemTichLuy;

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
    public void setOnAddSuccessCallback(Runnable callback) {
        this.onAddSuccessCallback = callback;
    }
    private void closePopup() {
        // Lấy đối tượng Stage của cửa sổ hiện tại và đóng nó
        Stage stage = (Stage) btnClosePopUps.getScene().getWindow();
        stage.close();
    }
    public void initialize() {
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
        
        if(flag){
            if(ten.isEmpty()){
                validTenKH.setText("Không Được Bỏ Trống");
                flag = false;
            }
            else if (!ten.matches("^[\\p{L}][\\p{L}\\s]*$")) {
                validTenKH.setText("Tên không hợp lệ! Chỉ chứa chữ cái và khoảng trắng (Ví dụ: Nguyễn Văn A).");
                flag = false;
            }
            if(soDienThoai.isEmpty()){
                validSDT.setText("Không Được Bỏ Trống.");
                flag = false;
            }
            else if(!soDienThoai.matches("^0[0-9]{9}$")){
                validSDT.setText("Không Đúng Định Dạng! Hãy Nhập Lại (Ví dụ: 0987654321).");
                flag = false;
            }
            else if(khBUS.kiemTraSoDienThoai(soDienThoai)){
                validSDT.setText("Đã Có Số Điện Thoại " + soDienThoai + "! Hãy Nhập Lại.");
                flag = false;
            }
            if(!txtDiemTichLuy.getText().matches("^[0-9]+$")){
                validDiemTichLuy.setText("Không Đúng Định Dạng (Chỉ Được Nhập Số)! Hãy Nhập Lại.");
                flag = false;
            }
            
        }
        if(flag){
            khachHangDTO nv = new khachHangDTO();
            nv.setTenKH(ten);
            nv.setSoDienThoai(soDienThoai);
            nv.setDiemTichLuy(diemTichLuy);
            boolean check = true;
            int maKH = khBUS.addKhachHang(nv);
            if(check){
                JOptionPane.showMessageDialog(null,"Thêm Khách Hàng Thành Công.");
                if (onAddSuccessCallback != null) {
                    onAddSuccessCallback.run(); // Gọi callback
                }
                closePopup();
            }
            else{
                JOptionPane.showMessageDialog(null,"Thêm Khách Hàng Thất Bại.");
            }
        }
    }

    
}