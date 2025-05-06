package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import BUS.SanPhamBUS;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QLSanPhamController {
    
    @FXML
    private Button btn;

     @FXML
    private TextField keySearch;

    @FXML
    private Button search;

    @FXML
    private VBox vbox = null;


    @FXML
    private ScrollPane scrollpane;

    private SanPhamBUS _sanphamBUS = new SanPhamBUS();
    private List<SanPhamDTO> listSP = new ArrayList<>();
    @FXML
    void initialize() {
        listSP = _sanphamBUS.getAll();
        ShowListSP();       
    }

    @FXML
    void searchKey(ActionEvent event) {
        listSP = _sanphamBUS.timKiem(keySearch.getText());
        vbox.getChildren().clear();
        ShowListSP();
    }

    private void ShowListSP(){
        int n =0;
        for (SanPhamDTO sp : listSP) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ItemGUI.fxml"));
            Node nodes = loader.load();
            ItemController controller = loader.getController();
            controller.setItem(sp,++n);
            // Gán callback reload
            controller.setOnProductUpdated(() -> {
                vbox.getChildren().clear();
                listSP =_sanphamBUS.timKiem(keySearch.getText());
                ShowListSP();
            });
            vbox.getChildren().add(nodes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

    @FXML
    private void addSP(){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FormSPGUI.fxml")); 
        Parent root = loader.load();
        FormSPController controller = loader.getController();  
        controller.isMode(false);     
        Stage formStage = new Stage();
        formStage.setScene(new Scene(root));
        formStage.setTitle("Add Product");
        formStage.showAndWait(); // đợi đến khi form đóng
        }catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

}
