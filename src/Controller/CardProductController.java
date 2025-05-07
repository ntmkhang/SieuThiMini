package Controller;

import DTO.sanPhamDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class CardProductController {
     @FXML
    private AnchorPane cardForm;

    @FXML
    private Button productAddBTN;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Spinner<Integer> productSpinner;

    private QLBHController qlbhController; // Tham chiếu đến QLBHController
    private sanPhamDTO currentProduct; // Sản phẩm hiện tại

    public void thongTinSanPham (String tenSP, int gia, String imgUrl, int soLuong, sanPhamDTO sanPham){
        this.currentProduct = sanPham;
        productName.setText(tenSP);
        productPrice.setText(gia + " VND");
        if(soLuong >0){
            SpinnerValueFactory<Integer> spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, soLuong, 1);
            productSpinner.setValueFactory(spinner);

            //thiết lập giá trị mặc định là 1
            productSpinner.getValueFactory().setValue(1);

            //bật spinner và nút add
            productSpinner.setDisable(false);
            productAddBTN.setDisable(false);
        } else {
            // nếu số lượng sản phẩm bằng 0, thay giá bằng hết hàng và vô hiệu hóa spinner + nút add
            productSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
            productSpinner.getValueFactory().setValue(0);
            productSpinner.setDisable(true);
            productAddBTN.setDisable(true);
            productPrice.setText("Hết hàng!");
        }
        try {
            Image image = new Image("file:" + imgUrl);
            productImage.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.currentProduct = sanPham;
    }

    public String getTenSP (){
        return productName.getText();
    } 

    public Integer getGiaSanPham (){
        return Integer.parseInt(productPrice.getText());
    }

    public Button getAddButton (){
        return productAddBTN;
    }

    public void setQLBHController (QLBHController qlbhController){
        this.qlbhController = qlbhController;
    }

    @FXML
    void handleproductAddBTN(ActionEvent event) {
        Integer soLuong = productSpinner.getValue();
        if(soLuong == null ||soLuong <=0){
            showError("Số lượng không được để trống và phải lớn hơn 0!");
            return;
        }

        if(currentProduct !=null && soLuong > currentProduct.getSoLuong()){
            showError("Số lượng không được lớn hơn số lượng sản phẩm trong kho!");
            return;
        }

        if(currentProduct != null && qlbhController!=null){
            if (qlbhController.isProductExist(currentProduct)) {
                qlbhController.updateProductInTable(currentProduct, soLuong);
            } else{
            qlbhController.addSanPhamToTable(new sanPhamDTO(
                currentProduct.getMaSP(),
                currentProduct.getTenSP(),
                currentProduct.getMaLoai(),
                currentProduct.getMoTa(), 
                Integer.parseInt(productPrice.getText().replaceAll("[^0-9]", "")),
                soLuong,
                currentProduct.getHinhAnh(),
                currentProduct.getIsDeleted()), soLuong
            );
            }
        }

    }

    private void showError(String message) {
        // Hiển thị thông báo lỗi (có thể sử dụng Alert hoặc Label tùy ý)
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
