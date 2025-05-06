package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import BUS.LoaiSanPhamBUS;
import DTO.LoaiSanPhamDTO;
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
    private TableColumn<LoaiSanPhamDTO, Integer> id;

    @FXML
    private TableColumn<LoaiSanPhamDTO, String> moTa;

    @FXML
    private TableView<LoaiSanPhamDTO> table;

    @FXML
    private TableColumn<LoaiSanPhamDTO, String> ten;

    @FXML
    private TableColumn<LoaiSanPhamDTO, Boolean> trangThai;

    @FXML
    private TableColumn<LoaiSanPhamDTO, Void> handlekhoa;

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

    private ObservableList<LoaiSanPhamDTO> ds = FXCollections.observableArrayList();
    private LoaiSanPhamBUS _loaiSanPhamBUS = new LoaiSanPhamBUS();

    @FXML
    void initialize() {
        ds.setAll(_loaiSanPhamBUS.getAllLoaiSanPham());
        id.setCellValueFactory(new PropertyValueFactory<LoaiSanPhamDTO, Integer>("maLoai"));
        ten.setCellValueFactory(new PropertyValueFactory<LoaiSanPhamDTO, String>("tenLoai"));
        moTa.setCellValueFactory(new PropertyValueFactory<LoaiSanPhamDTO, String>("moTa"));
        trangThai.setCellValueFactory(new PropertyValueFactory<LoaiSanPhamDTO, Boolean>("Deleted"));

        trangThai.setCellFactory(column -> new TableCell<LoaiSanPhamDTO, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Nếu isDeleted == true → Ngừng hoạt động
                    setText(item ? "Ngừng hoạt động" : "Đang hoạt động");
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
        FilteredList<LoaiSanPhamDTO> filteredList = new FilteredList<>(ds, p -> true);

        txtTimKiem.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(loai -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return loai.getTenLoai().toLowerCase().contains(lowerCaseFilter);
            });
        });



        SortedList<LoaiSanPhamDTO> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);

    }

    @FXML
    private void Sua(ActionEvent event) {
        LoaiSanPhamDTO loai = new LoaiSanPhamDTO();
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
            LoaiSanPhamDTO lDto = new LoaiSanPhamDTO(ID, Ten, MoTa, false);
            _loaiSanPhamBUS.Them(lDto);
            ds.add(lDto);
            clearFields();
            return;
        }

        LoaiSanPhamDTO lDto = new LoaiSanPhamDTO(ID, Ten, MoTa, false);
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

        LoaiSanPhamDTO lDto = new LoaiSanPhamDTO(ID, Ten, MoTa, false);
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
            LoaiSanPhamDTO selected = table.getSelectionModel().getSelectedItem();
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
    
            boolean newStatus = !selected.isDeleted(); // Đảo trạng thái
            boolean success = _loaiSanPhamBUS.Khoa(ID, newStatus);
    
            if (success) {
                selected.setDeleted(newStatus); // Cập nhật model
                table.refresh(); // Nếu không dùng Property, vẫn cần
                showAlert(newStatus ? "Khóa thành công ID "+ID +" ." : "Mở khóa thành công ID "+ ID+" .");
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
