package Controller;

import BUS.LoginBUS;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsername; // Nhập tên đăng nhập

    @FXML
    private PasswordField txtPassword; // Nhập mật khẩu

    @FXML
    private Button btnLogin, btnLogout; // Nút đăng nhập & đăng xuất

    private QLBHController qlbh;
    private LoginBUS loginBUS;

    public void setQLBHController (QLBHController controller){
        this.qlbh = controller;
    }
    public void initialize() {
        loginBUS = new LoginBUS();
        btnLogin.setOnAction(event -> handleLogin());
        btnLogout.setOnAction(event -> handleLogout());
    }

    private void handleLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Lỗi đăng nhập", "Tên tài khoản hoặc mật khẩu bị trống!");
            return;
        }

        if (loginBUS.validateLogin(username, password)) {
            if(!loginBUS.isAdmin(username, password)){
                System.out.println("✅ Đăng nhập thành công!");
                openCustomerPage(username); 
            }
        } else {
            showError("Lỗi đăng nhập", "Tên người dùng hoặc mật khẩu không chính xác.");
        }
    }

    private void handleLogout() {
        txtUsername.clear();
        txtPassword.clear();
        System.out.println("🔒 Bạn đã đăng xuất.");
    }

    private void openCustomerPage(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminPaneGUI.fxml")); // 🔹 Đảm bảo đúng đường dẫn
            Parent root = loader.load();
            QLBHController controller = loader.getController();
            controller.addNhanVien(username);

            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Quản lý bán hàng");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Lỗi", "Không thể mở trang Quản lý bán hàng.");
        }
    }

    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi hệ thống");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleExitClick() {
        Platform.exit();
        System.exit(0);
    }

    
}
