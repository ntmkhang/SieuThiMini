package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import BUS.loaiSanPhamBUS;
import DTO.loaiSanPhamDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class QLPhanLoaiController {
    @FXML
    private TableColumn<loaiSanPhamDTO, Integer> id;

    @FXML
    private TableColumn<loaiSanPhamDTO, String> moTa;

    @FXML
    private TableView<loaiSanPhamDTO> table;

    @FXML
    private TableColumn<loaiSanPhamDTO, String> ten;

    @FXML
    private TableColumn<loaiSanPhamDTO, Integer> trangThai;

    @FXML
    private TableColumn<loaiSanPhamDTO, Void> handlekhoa;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtTimKiem;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtMota;

    @FXML
    private TextField txtTen;

    @FXML
    private Button handleThem;

    @FXML
    private Button handleKhoa;

    private ObservableList<loaiSanPhamDTO> ds = FXCollections.observableArrayList();
    private loaiSanPhamBUS _loaiSanPhamBUS = new loaiSanPhamBUS();

    @FXML
    void initialize() {
        ds.setAll(_loaiSanPhamBUS.getAll());
        id.setCellValueFactory(new PropertyValueFactory<loaiSanPhamDTO, Integer>("maLoai"));
        ten.setCellValueFactory(new PropertyValueFactory<loaiSanPhamDTO, String>("tenLoai"));
        moTa.setCellValueFactory(new PropertyValueFactory<loaiSanPhamDTO, String>("moTa"));
        trangThai.setCellValueFactory(new PropertyValueFactory<loaiSanPhamDTO, Integer>("IsDeleted"));

        trangThai.setCellFactory(column -> new TableCell<loaiSanPhamDTO, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item == 1 ? "Ngừng hoạt động" : "Đang hoạt động");
                }
            }

        });

        table.getSelectionModel().selectedItemProperty().addListener((a,oldeValue,newValue) -> {
            if (newValue != null) {
                // Gán giá trị từ dòng đã chọn vào các TextField
                txtID.setText(String.valueOf(newValue.getMaLoai()));
                txtTen.setText(newValue.getTenLoai());
                txtMota.setText(newValue.getMoTa());
            }
        });
        table.setItems(ds);
        timKiem();

    }

   
    private void timKiem() {
        FilteredList<loaiSanPhamDTO> filteredList = new FilteredList<>(ds, p -> true);

        txtTimKiem.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(loai -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return loai.getTenLoai().toLowerCase().contains(lowerCaseFilter);
            });
        });



        SortedList<loaiSanPhamDTO> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);

    }

    @FXML
    private void Sua(ActionEvent event) {
        loaiSanPhamDTO loai = new loaiSanPhamDTO();
        int ID = Integer.parseInt(txtID.getText());
        String MoTa = txtMota.getText();
        String Ten = txtTen.getText();
        Boolean check =_loaiSanPhamBUS.checkID(ID);
        if (Ten.isEmpty()) {
            showAlert("Vui lòng nhập đầy đủ Tên.");
            return;
        }
        if(check == false){
            showAlert("ID "+ID+" chưa tồn tại, thêm mới");
            loaiSanPhamDTO lDto = new loaiSanPhamDTO(ID, Ten, MoTa, 0);
            _loaiSanPhamBUS.Them(lDto);
            ds.add(lDto);
            clearFields();
            return;
        }

        loaiSanPhamDTO lDto = new loaiSanPhamDTO(ID, Ten, MoTa, 0);
        _loaiSanPhamBUS.Sua(lDto);
        for (int i=0;i<ds.size();i++) {
            if(ds.get(i).getMaLoai()==ID){
                ds.set(i, lDto);
            }
        }
        table.refresh();
        showAlert("Sửa thành công");
        clearFields();

    }




    @FXML
    void Them(ActionEvent event) {
        // Integer ID = null;
        // String idTet = txtID.getText().trim();
        try {
            // if(!idTet.isEmpty()){
            int ID = Integer.parseInt(txtID.getText());
            Boolean check =_loaiSanPhamBUS.checkID(ID);
            if(check == true){
                showAlert("ID "+ID+" đã tồn tại");
                return;
            }
            String Ten = txtTen.getText();
            String MoTa = txtMota.getText();

        if (Ten.isEmpty()) {
            showAlert("Vui lòng nhập đầy đủ Tên.");
            return;
        }

        loaiSanPhamDTO lDto = new loaiSanPhamDTO(ID, Ten, MoTa, 0);
        _loaiSanPhamBUS.Them(lDto);
        showAlert("Thêm thành công");
        ds.add(lDto);
        clearFields();
    } catch (NumberFormatException e) {
        showAlert("Lỗi kiểu ID");
    }
    }

    @FXML
    private void khoa(ActionEvent event) {
        try {
            loaiSanPhamDTO selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Vui lòng chọn một dòng để khóa.");
                return;
            }
    
            int ID = selected.getMaLoai();
            Boolean check = _loaiSanPhamBUS.checkID(ID);
            if (!check) {
                showAlert("Không tồn tại");
                return;
            }
            int newStatus = 1;
            if(selected.getIsDeleted() == 1)
            newStatus = 0; // Đảo trạng thái

            boolean success = _loaiSanPhamBUS.Khoa(ID, newStatus);
    
            if (success) {
                selected.setIsDeleted(newStatus); // Cập nhật model
                table.refresh(); // Nếu không dùng Property, vẫn cần
                showAlert(newStatus == 1 ? "Khóa thành công ID "+ID +" ." : "Mở khóa thành công ID "+ ID+" .");
            } else {
                showAlert("Cập nhật trạng thái thất bại.");
            }
    
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert("Sai định dạng trường ID");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Có lỗi xảy ra khi khóa.");
        }
    }
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void clearFields() {
        txtID.setText(null);;
        txtTen.setText(null);;
        txtMota.setText(null);;
    }

}
