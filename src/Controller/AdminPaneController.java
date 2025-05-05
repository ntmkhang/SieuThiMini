package Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminPaneController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnQLHD;

    @FXML
    private Button btnQLKH;

    @FXML
    private Button btnQLNV;

    @FXML
    private Button btnQLPL;

    @FXML
    private Button btnQLPN;

    @FXML
    private Button btnQLSP;

    @FXML
    private Button btnTK;

     private Map<Integer, Button> buttonMap;
    @FXML
    void initialize() {
       
    }

    private void load(String file, boolean isDeleted){
        
    }


    private void setupButtonMap() {
        buttonMap = new HashMap<>();
        buttonMap.put(1, btnHome);
        buttonMap.put(2, btnQLSP);
        buttonMap.put(3, btnQLPL);
        buttonMap.put(4, btnQLNV);
        buttonMap.put(5, btnQLKH);
        buttonMap.put(6, btnQLHD);
        buttonMap.put(7, btnQLPN);
        buttonMap.put(8, btnTK);
    }
    @FXML
    void obBtnHome(ActionEvent event) {

    }

    @FXML
    void onBtnQLHoaDon(ActionEvent event) {

    }

    @FXML
    void onBtnQLKhachHang(ActionEvent event) {

    }

    @FXML
    void onBtnQLNhanVien(ActionEvent event) {

    }

    @FXML
    void onBtnQLPhanLoai(ActionEvent event) {

    }

    @FXML
    void onBtnQLPhieuNhap(ActionEvent event) {

    }

    @FXML
    void onBtnQLSanPham(ActionEvent event) {

    }

    @FXML
    void onBtnThongKe(ActionEvent event) {

    }

  
}
