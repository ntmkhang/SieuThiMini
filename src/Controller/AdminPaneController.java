package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.layout.element.Image;

public class AdminPaneController {

    @FXML
    private StackPane contentPane;

    @FXML
    private Button adminDangXuatBTN;
    @FXML
    private Button btnQLSP;
    @FXML
    private Button btnQLPL;
    @FXML
    private Button btnQLTK;
    @FXML
    private Button btnQLNV;
    @FXML
    private Button btnQLKH;
    @FXML
    private Button btnQLHD;
    @FXML
    private Button btnQLPN;
    @FXML
    private Button btnTK;

    private Map<Button, String> buttonMap;

    @FXML
    public void initialize() {
        setupButtonMap();
        setupButtonActions();
    }

    private void setupButtonMap() {
        buttonMap = new HashMap<>();
        // buttonMap.put(btnHome, "Home.fxml");
        buttonMap.put(btnQLSP, "QLSanPhamGUI.fxml");
        buttonMap.put(btnQLPL, "QLPhanLoaiGUI.fxml");
        buttonMap.put(btnQLNV, "QLNVGUI.fxml");
        buttonMap.put(btnQLTK, "AccountManage.fxml");
        buttonMap.put(btnQLKH, "QLKHGUI.fxml");
        buttonMap.put(btnQLHD, "QLHoaDonGUI.fxml");
        buttonMap.put(btnQLPN, "PhieuNhapGUI.fxml");
        buttonMap.put(btnTK, "ThongKeGui.fxml");
    }

    private void setupButtonActions() {
        for (Map.Entry<Button, String> entry : buttonMap.entrySet()) {
            Button btn = entry.getKey();
            String fxml = entry.getValue();
            btn.setOnAction(e -> load(fxml));
        }
    }

    private void load(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/" + fxmlFile));
            Pane newContent = loader.load();
            contentPane.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Không thể tải giao diện: " + fxmlFile);
        }
    }

    @FXML 
    void handleDangXuatBTN (ActionEvent event){
        try {
            // Load lại màn hình đăng nhập
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUi/LoginGUI.fxml"));
            Parent loginRoot = loader.load();
    
            // Lấy stage hiện tại từ event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
            // Tạo scene mới và gán vào stage
            Scene scene = new Scene(loginRoot);
            stage.setScene(scene);
            stage.setTitle("Đăng nhập");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
