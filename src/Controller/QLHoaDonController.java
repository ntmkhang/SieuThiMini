package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import BUS.HoaDonBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import DTO.CTHoaDonDTO;
import DTO.HoaDonDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class QLHoaDonController{


    @FXML
    private Label lbKH;


    @FXML
    private Label lbTongTien;

    @FXML
    private TableColumn<CTHoaDonDTO, Double> Gia;

    @FXML
    private TableColumn<HoaDonDTO, Integer> id;

    @FXML
    private TableColumn<HoaDonDTO, LocalDateTime> ngayTao;

    @FXML
    private TableColumn<HoaDonDTO, String> nhanVien;

    @FXML
    private TableColumn<CTHoaDonDTO, String> sanPham;

    @FXML
    private TableColumn<CTHoaDonDTO, Integer> soLuong;

    @FXML
    private TableView<CTHoaDonDTO> tbCTHD;

    @FXML
    private TableView<HoaDonDTO> tbHoaDon;

    @FXML
    private TableColumn<HoaDonDTO, Double> tongTien;

     @FXML
    private TextField key;

    @FXML
    private DatePicker dateSearch;

    @FXML
    private Button XemCT;

    private ObservableList<HoaDonDTO> ds = FXCollections.observableArrayList();
    private ObservableList<CTHoaDonDTO> dsSanPham = FXCollections.observableArrayList();
    private HoaDonBUS _hoaDonBUS = new HoaDonBUS();
    private NhanVienBUS _nhanVienBUS = new NhanVienBUS();
    private SanPhamBUS _sanPhamBUS = new SanPhamBUS();
    @FXML
    void initialize() {
      // Cấu hình các cột
      ds.setAll(_hoaDonBUS.getALLHD());
      id.setCellValueFactory(new PropertyValueFactory<HoaDonDTO,Integer>("maHD"));
      ngayTao.setCellValueFactory(new PropertyValueFactory<HoaDonDTO,LocalDateTime>("ngayLap"));
      tongTien.setCellValueFactory(new PropertyValueFactory<HoaDonDTO,Double>("tongTien"));

     nhanVien.setCellValueFactory(p ->{
        int maNV = p.getValue().getMaNV();
        String tenNV = _nhanVienBUS.getTenNvByID(maNV);
        return new ReadOnlyObjectWrapper<>(tenNV);
     });

    tbHoaDon.setOnMouseClicked(event ->{
        HoaDonDTO selected = tbHoaDon.getSelectionModel().getSelectedItem();
        if(selected != null){
            showCTHoaDon(selected.getMaHD());
            //maHD = selected.getMaHD();
        }
    });
     
      tbHoaDon.setItems(ds);

  }

 private void showCTHoaDon(int maHD) {
    // Lấy danh sách chi tiết hóa đơn từ BUS hoặc DAO
    dsSanPham.clear();
    List<CTHoaDonDTO> cthdList = _hoaDonBUS.getALLSanPhamByID(maHD);
    
    // Chuyển danh sách thành ObservableList để hiển thị trong TableView
    sanPham.setCellValueFactory(p->{
        int maSP = p.getValue().getMaSP();
        String tenSP = _sanPhamBUS.TimKiemTheoId(maSP).getTenSP();
        return new ReadOnlyObjectWrapper<>(tenSP);
    });

    soLuong.setCellValueFactory(new PropertyValueFactory<CTHoaDonDTO,Integer>("soLuong"));
    Gia.setCellValueFactory(new PropertyValueFactory<CTHoaDonDTO,Double>("giaBan"));
    dsSanPham.addAll(cthdList);

    double tongTienValue = 0;
    for (CTHoaDonDTO cthd : cthdList) {
        tongTienValue += cthd.getSoLuong() * cthd.getGiaBan();
    }

    // Cập nhật label tổng tiền
    lbTongTien.setText(String.format("%.2f", tongTienValue));
    // Cập nhật TableView tbCTHD với danh sách chi tiết hóa đơn
    tbCTHD.setItems(dsSanPham);
}

    @FXML
    void XemHoaDon(ActionEvent event) {
        HoaDonDTO selected = tbHoaDon.getSelectionModel().getSelectedItem();
        if(selected ==null){
            showAlert("Vui lòng chọn Hoá Đơn");
            return;
        }
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CTHoaDonGUI.fxml")); 
            Parent root = loader.load();
            CTHoaDonController controller = loader.getController();
            controller.showCTHoaDon(selected);
            Stage stage = new Stage();
        stage.setTitle("Chi tiết hóa đơn");
        stage.setScene(new Scene(root));
        stage.show();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
     
   @FXML
void searchAction(ActionEvent event) {
    Integer id = null;
    LocalDate date = null;

    // Xử lý ID nhập từ TextField `key`
    String idText = key.getText();
    if (idText != null && !idText.isEmpty()) {
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            showAlert("Vui lòng nhập ID là số nguyên.");
            return;
        }
    }

    // Giả sử bạn có DatePicker tên là `datePicker`
    if (dateSearch.getValue() != null) {
        date = dateSearch.getValue();
    }

    // Gọi DAO hoặc BUS để tìm hóa đơn
    List<HoaDonDTO> ketQua = _hoaDonBUS.searchHoaDons(idText, date);

    // Hiển thị kết quả ra bảng hoặc thông báo nếu không có
    if (ketQua.isEmpty()) {
        showAlert("Không tìm thấy hóa đơn phù hợp.");
    } else {
        tbHoaDon.getItems().setAll(ketQua);
        // key.clear();
        // dateSearch.getEditor().clear();
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


