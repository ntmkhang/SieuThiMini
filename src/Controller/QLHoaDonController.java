package Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import BUS.hoaDonBUS;
import BUS.khachHangBUS;
import BUS.nhanVienBUS;
import BUS.sanPhamBUS;
import DTO.CThoaDonDTO;
import DTO.hoaDonDTO;
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

public class QLHoaDonController {

    @FXML
    private Label lbKH;

    @FXML
    private Label lbTongTien;

    @FXML
    private TableColumn<CThoaDonDTO, Double> Gia;

    @FXML
    private TableColumn<hoaDonDTO, Integer> id;

    @FXML
    private TableColumn<hoaDonDTO, LocalDateTime> ngayTao;

    @FXML
    private TableColumn<hoaDonDTO, String> nhanVien;

    @FXML
    private TableColumn<CThoaDonDTO, String> sanPham;

    @FXML
    private TableColumn<CThoaDonDTO, Integer> soLuong;

    @FXML
    private TableView<CThoaDonDTO> tbCTHD;

    @FXML
    private TableView<hoaDonDTO> tbHoaDon;

    @FXML
    private TableColumn<hoaDonDTO, Double> tongTien;

    @FXML
    private TextField key;

    @FXML
    private TextField searchKH;

    @FXML
    private DatePicker dateSearch;

    @FXML
    private DatePicker toDateSearch;

    @FXML
    private Button XemCT;

    private ObservableList<hoaDonDTO> ds = FXCollections.observableArrayList();
    private ObservableList<CThoaDonDTO> dsSanPham = FXCollections.observableArrayList();
    private hoaDonBUS _hoaDonBUS = new hoaDonBUS();
    private nhanVienBUS _nhanVienBUS = new nhanVienBUS();
    private sanPhamBUS _sanPhamBUS = new sanPhamBUS();
    private khachHangBUS _khachHangBUS = new khachHangBUS();

    @FXML
    void initialize() {
        // Cấu hình các cột
        ds.setAll(_hoaDonBUS.getAllHoaDon());
        id.setCellValueFactory(new PropertyValueFactory<hoaDonDTO, Integer>("maHD"));
        ngayTao.setCellValueFactory(new PropertyValueFactory<hoaDonDTO, LocalDateTime>("ngayLap"));
        tongTien.setCellValueFactory(new PropertyValueFactory<hoaDonDTO, Double>("tongTien"));

        nhanVien.setCellValueFactory(p -> {
            int maNV = p.getValue().getMaNV();
            String tenNV = _nhanVienBUS.getTenNVbyMaNV(maNV);
            return new ReadOnlyObjectWrapper<>(tenNV);
        });

        tbHoaDon.setOnMouseClicked(event -> {
            hoaDonDTO selected = tbHoaDon.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showCTHoaDon(selected.getMaHD());
                lbKH.setText(_khachHangBUS.getTenKHbyMaKH(selected.getMaKH()));
                // maHD = selected.getMaHD();
            }
        });

        key.setPromptText("Mã hoá đơn");
        searchKH.setPromptText("Mã khách hàng");

        tbHoaDon.setItems(ds);

    }

    private void showCTHoaDon(int maHD) {
        // Lấy danh sách chi tiết hóa đơn từ BUS hoặc DAO
        dsSanPham.clear();
        ArrayList<CThoaDonDTO> cthdList = _hoaDonBUS.getCThoaDonbyMaHD(maHD);

        // Chuyển danh sách thành ObservableList để hiển thị trong TableView
        sanPham.setCellValueFactory(p -> {
            int maSP = p.getValue().getMaSP();
            String tenSP = _sanPhamBUS.TimKiemTheoId(maSP).getTenSP();
            return new ReadOnlyObjectWrapper<>(tenSP);
        });

        soLuong.setCellValueFactory(new PropertyValueFactory<CThoaDonDTO, Integer>("soLuong"));
        Gia.setCellValueFactory(new PropertyValueFactory<CThoaDonDTO, Double>("giaBan"));
        dsSanPham.addAll(cthdList);

        double tongTienValue = 0;
        for (CThoaDonDTO cthd : cthdList) {
            tongTienValue += cthd.getSoLuong() * cthd.getGiaBan();
        }

        // Cập nhật label tổng tiền
        lbTongTien.setText(String.format("%.2f", tongTienValue));
        // Cập nhật TableView tbCTHD với danh sách chi tiết hóa đơn
        tbCTHD.setItems(dsSanPham);
    }

    @FXML
    void XemHoaDon(ActionEvent event) {
        hoaDonDTO selected = tbHoaDon.getSelectionModel().getSelectedItem();
        if (selected == null) {
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
        Integer id = -1, idKH = -1;

        // Xử lý ID nhập từ TextField `key`
        String idText = key.getText();
        String KhText = searchKH.getText();
        if (idText != null && !idText.isEmpty()) {
            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException e) {
                showAlert("Vui lòng nhập ID là số nguyên.");
                return;
            }
        }

        if (KhText != null && !KhText.isEmpty()) {
            try {
                idKH = Integer.parseInt(KhText);
            } catch (NumberFormatException e) {
                showAlert("Vui lòng nhập mã khách hàng là số nguyên.");
                return;
            }
        }

        LocalDate localDateFrom = dateSearch.getValue();
        LocalDate localDateTo = toDateSearch.getValue();

        Date sqlDateFrom = null;
        Date sqlDateTo = null;

        // Chỉ chuyển đổi ngày khi người dùng đã chọn giá trị
        if (localDateFrom != null) {
            sqlDateFrom = java.sql.Date.valueOf(localDateFrom);
        }

        if (localDateTo != null) {
            sqlDateTo = java.sql.Date.valueOf(localDateTo);
        }
        // Gọi DAO hoặc BUS để tìm hóa đơn
        ArrayList<hoaDonDTO> ketQua = _hoaDonBUS.searchHoaDon(id, idKH, sqlDateFrom, sqlDateTo);

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
