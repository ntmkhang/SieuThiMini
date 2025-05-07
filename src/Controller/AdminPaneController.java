package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.layout.element.Image;

public class AdminPaneController {

    @FXML
    private StackPane contentPane;

    @FXML
    private Button btnHome;
    @FXML
    private Button btnQLSP;
    @FXML
    private Button btnQLPL;
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
        buttonMap.put(btnQLNV, "QLNhanVien.fxml");
        buttonMap.put(btnQLKH, "QLKhachHang.fxml");
        buttonMap.put(btnQLHD, "QLHoaDonGUI.fxml");
        buttonMap.put(btnQLPN, "QLPhieuNhap.fxml");
        buttonMap.put(btnTK, "ThongKe.fxml");
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

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
