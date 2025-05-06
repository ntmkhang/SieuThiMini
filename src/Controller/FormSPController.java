package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import BUS.LoaiSanPhamBUS;
import BUS.SanPhamBUS;
import DAO.LoaiSPDAO;
import DAO.SanPhamDAO;
import DTO.LoaiSanPhamDTO;
import DTO.SanPhamDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FormSPController {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button btnCRUD;

    @FXML
    private TextField giaban;

    @FXML
    private TextField mota;

    @FXML
    private ComboBox<String> phanloai;

    @FXML
    private TextField soluong;

    @FXML
    private TextField tensp;

    @FXML
    private ImageView SanPhamImg;

    @FXML
    private TextField imgSrc;

    @FXML
    private Button btnImg;

    @FXML
    private TextField keySearch;

    @FXML
    private Button search;

    private LoaiSanPhamBUS _LoaiSPBUS = new LoaiSanPhamBUS() ;
    private SanPhamBUS _sanphamBus  = new SanPhamBUS();
    private boolean isEditMode;
    private SanPhamDTO sanpham = new SanPhamDTO();
    private String url = "src/asset/img";

    @FXML
    void initialize() {
        sanpham = new SanPhamDTO();
        LoadLoaiSP();
    }
    
    public void isMode(boolean mode){
        isEditMode = mode;
        if(isEditMode){
            setSanPham(sanpham);
            btnCRUD.setText("Sửa sản phẩm");
        }
            else
            btnCRUD.setText("Thêm sản phẩm");
    }
 
    @FXML
    void addSP(ActionEvent event) {
        if(!setSPData()) return;

        if(isEditMode){
            setSPData();
            _sanphamBus.SuaSP(sanpham.getMaSP(), sanpham);
            showAlert("Sửa Thành Công");
        }else{
            setSPData();
            _sanphamBus.ThemSP(sanpham); 
            showAlert("Thêm Thành Công");       
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void chooseImage(ActionEvent e){
        Stage stage = (Stage)pane.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose image");
        FileChooser.ExtensionFilter imgFilter = new FileChooser.ExtensionFilter("Image File", "*.png","*.jpg","*.gif");
        fc.getExtensionFilters().add(imgFilter);
        File file = fc.showOpenDialog(stage);
       

        if(file !=null){
            File desDir = new File(url);
        if(!desDir.exists()){
            desDir.mkdirs();
        }
        File desFile = new File(desDir,file.getName());

        try {
            Files.copy(file.toPath(), desFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            // TODO: handle exception
            exception.printStackTrace();
        }
            Image img = new Image(desFile.toURI().toString());
            // Image img = new Image(file.toURI().toString(),50,50,false,true);
            
            imgSrc.setText(desFile.getName());
            SanPhamImg.setImage(img); // hiển thị ảnh 
        }
    }

    

    private void LoadLoaiSP(){
        List<LoaiSanPhamDTO> loaiSPList = _LoaiSPBUS.getAllLoaiSanPham();
        for (LoaiSanPhamDTO loaiSanPhamDTO : loaiSPList) {
            phanloai.getItems().add(loaiSanPhamDTO.getTenLoai());
        }
    }

    public boolean setSPData(){
        
        sanpham.setTenSP(tensp.getText());
        sanpham.setMoTa(mota.getText());
        try {
            double price = Double.parseDouble(giaban.getText());
            sanpham.setGiaBan(price);

        } catch (NumberFormatException e) {
            // TODO: handle exception
            //e.printStackTrace();
            showAlert("Sai kiểu dữ liệu giá bán");
            return false;
        }
        try {
            int sl = Integer.parseInt(soluong.getText());
            sanpham.setSoLuong(sl); 
        } catch (Exception e) {
            // TODO: handle exception
            //e.printStackTrace();
            showAlert("Sai kiểu dữ liệu số lượng");
            return false;
        }

        
        String selectedName = phanloai.getValue();
        if (selectedName != null) {
        List<LoaiSanPhamDTO> loaiSPList = _LoaiSPBUS.getAllLoaiSanPham();
        for (LoaiSanPhamDTO loai : loaiSPList) {
            if (loai.getTenLoai().equals(selectedName)) {
                sanpham.setMaLoai(loai.getMaLoai());
                break;
            }
        }
        }

        sanpham.setHinhAnh(imgSrc.getText());
        return true;

    }

    public void setSanPham(SanPhamDTO sp) {
        sanpham.setMaSP(sp.getMaSP());
        tensp.setText(sp.getTenSP());
        mota.setText(sp.getMoTa());
        giaban.setText(String.valueOf(sp.getGiaBan()));
        soluong.setText(String.valueOf(sp.getSoLuong()));
        
        // Set hình ảnh
        if (sp.getHinhAnh() != null && !sp.getHinhAnh().isEmpty()) {
            File imgFile = new File(url+"/"+sp.getHinhAnh());
            if (imgFile.exists()) {
                Image img = new Image(imgFile.toURI().toString());
                SanPhamImg.setImage(img);
                imgSrc.setText(sp.getHinhAnh());
            }
        }
    
        // Set loại sản phẩm lên combobox
        if (sp.getMaLoai() != 0) {
           String loai = _LoaiSPBUS.getTenLoaiById(sp.getMaLoai());
           phanloai.setValue(loai);

        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    
}
