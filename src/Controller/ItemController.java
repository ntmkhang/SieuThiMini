package Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import BUS.sanPhamBUS;
import DTO.sanPhamDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label TenSP;

    @FXML
    private Button btnSua;

    @FXML
    private Button btnXoa;

    @FXML
    private ImageView hideIcon;

    @FXML
    private Label giaban;

    @FXML
    private Label soluong;

    @FXML
    private Label stt;

    private sanPhamBUS _sanphamBUS = new sanPhamBUS();

    private Runnable onProductUpdated;
    public void setOnProductUpdated(Runnable onProductUpdated) {
    this.onProductUpdated = onProductUpdated;
    }

    @FXML
    void initialize() {
        
    }

    public void setItem(sanPhamDTO sp, int i){
        this.stt.setText(Integer.toString(i));
        this.TenSP.setText(sp.getTenSP());
        this.giaban.setText(Double.toString(sp.getGia()));
        this.soluong.setText(Integer.toString(sp.getSoLuong()));
        Image img =new Image(new File("src/asset/img/view.png").toURI().toString());
        if (sp.getIsDeleted()==0) {
            // btnXoa.setText("Xoá");
            hideIcon.setImage(img);
        } else {
            hideIcon.setImage(new Image(new File("src/asset/img/hide.png").toURI().toString()));
        }
        btnSua.addEventHandler(ActionEvent.ACTION, event -> SuaSP(sp));
        btnXoa.addEventHandler(ActionEvent.ACTION, event -> XoaSP(sp.getMaSP(),sp.getIsDeleted()));
    }

    public void SuaSP(sanPhamDTO sp){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FormSPGUI.fxml")); 
        Parent root = loader.load();
        FormSPController controller = loader.getController();  
        controller.isMode(true);
        controller.setSanPham(sp);
        Stage formStage = new Stage();
        formStage.setScene(new Scene(root));
        formStage.setTitle("Update Product");
        formStage.showAndWait(); // đợi đến khi form đóng
        if (onProductUpdated != null) {
            onProductUpdated.run();
        }
        }catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void XoaSP(int ID, int isDeleted){
        sanPhamDTO sp = _sanphamBUS.TimKiemTheoId(ID);

        if(sp!=null && sp.getIsDeleted()==1){
            _sanphamBUS.capNhatTrangThai(ID, 0);
        }else{
            _sanphamBUS.capNhatTrangThai(ID, 1);
        }
        
       
        if (onProductUpdated != null) {
            onProductUpdated.run();
        }
    }
}
