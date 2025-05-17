package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.time.LocalDate;

import BUS.ThongKeBUS;
import DTO.DTO_ThongKe_ChiTieu;
import DTO.DTO_ThongKe_DoanhThu;
import DTO.DTO_ThongKe_LoiNhuan;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.Timestamp;


public class ThongKeController {
    private ObservableList<DTO_ThongKe_ChiTieu> chiTieuData;
	private ObservableList<DTO_ThongKe_DoanhThu> doanhThuData;
	private ObservableList<DTO_ThongKe_LoiNhuan> loiNhuanData;
	
	@FXML
	private TabPane mainTabPane;
	
	@FXML private TableView<DTO_ThongKe_ChiTieu> chiTieuTable;
    @FXML private TableColumn<DTO_ThongKe_ChiTieu, String> colNgayLap;
    @FXML private TableColumn<DTO_ThongKe_ChiTieu, String> colMaPhieu;
    @FXML private TableColumn<DTO_ThongKe_ChiTieu, String> colTenSanPham;
    @FXML private TableColumn<DTO_ThongKe_ChiTieu, String> colMaSanPham;
    @FXML private TableColumn<DTO_ThongKe_ChiTieu, Integer> colSoLuong;
    @FXML private TableColumn<DTO_ThongKe_ChiTieu, Double> colGiaNhap;
    @FXML private TableColumn<DTO_ThongKe_ChiTieu, Double> colChiTieu;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    
    @FXML private TableView<DTO_ThongKe_DoanhThu> doanhThuTable;
    @FXML private TableColumn<DTO_ThongKe_DoanhThu, String> colNgayLapDT;
    @FXML private TableColumn<DTO_ThongKe_DoanhThu, String> colMaHoaDonDT;
    @FXML private TableColumn<DTO_ThongKe_DoanhThu, String> colTenSanPhamDT;
    @FXML private TableColumn<DTO_ThongKe_DoanhThu, Integer> colSoLuongDT;
    @FXML private TableColumn<DTO_ThongKe_DoanhThu, Double> colGiaThucTe;
    @FXML private TableColumn<DTO_ThongKe_DoanhThu, Double> colTongTienHang;
    @FXML private DatePicker startDatePickerDT;
    @FXML private DatePicker endDatePickerDT;
    
    @FXML private TableView<DTO_ThongKe_LoiNhuan> loiNhuanTable;
    @FXML private TableColumn<DTO_ThongKe_LoiNhuan, String> colNgayLapLN;
    @FXML private TableColumn<DTO_ThongKe_LoiNhuan, String> colMaHoaDonLN;
    @FXML private TableColumn<DTO_ThongKe_LoiNhuan, String> colTenSanPhamLN;
    @FXML private TableColumn<DTO_ThongKe_LoiNhuan, Integer> colSoLuongLN;
    @FXML private TableColumn<DTO_ThongKe_LoiNhuan, Double> colGiaNhapLN;
    @FXML private TableColumn<DTO_ThongKe_LoiNhuan, Double> colGiaBanLN;
    @FXML private TableColumn<DTO_ThongKe_LoiNhuan, Double> colLoiNhuan;
    @FXML private Button btnSearch,btnSearchDT,btnSearchLN;
    @FXML private DatePicker startDatePickerLN;
    @FXML private DatePicker endDatePickerLN;

    @FXML
    private VBox chiTieuPanel, doanhThuPanel, loiNhuanPanel;

	@FXML
	private Button btnQuanLyThongKe;

    @FXML
    private StackPane mainPane;
    
    @FXML
    private Label lblTongChiTieu,lblTongDoanhThu,lblTongLoiNhuan;


    @FXML
    public void initialize() {
        if (chiTieuPanel != null) {
            chiTieuPanel.setVisible(true);
        }
        
        hienThiDSChiTieu();
        hienThiDSDoanhThu();
        hienThiDSLoiNhuan();

        if (btnSearch != null) {
            VBox.setMargin(btnSearch, new Insets(30, 0, 40, 0));
        }
        if (btnSearchDT != null) {
            VBox.setMargin(btnSearchDT, new Insets(30, 0, 40, 0));
        }
        if (btnSearchLN != null) {
            VBox.setMargin(btnSearchLN, new Insets(30, 0, 40, 0));
        }
        
        btnSearch.setOnAction(event -> handleSearch());
        btnSearchDT.setOnAction(event -> handleSearchDT());
        btnSearchLN.setOnAction(event -> handleSearchLN());
        loadAllData();
        loadAllDataDT();
        loadAllDataLN();
    }

    public void hienThiDSChiTieu() {
    	
    	colNgayLap.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));
        colMaPhieu.setCellValueFactory(new PropertyValueFactory<>("maPhieu"));
        colTenSanPham.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        colMaSanPham.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
        colSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        colGiaNhap.setCellValueFactory(new PropertyValueFactory<>("giaNhap"));
        colChiTieu.setCellValueFactory(new PropertyValueFactory<>("chiTieu"));

        

        chiTieuData = FXCollections.observableArrayList();
        chiTieuTable.setItems(chiTieuData);
        
    }

    public void hienThiDSDoanhThu() {
    	
    	colNgayLapDT.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));
    	colMaHoaDonDT.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
    	colTenSanPhamDT.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
    	colSoLuongDT.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
    	colGiaThucTe.setCellValueFactory(new PropertyValueFactory<>("giaThucTe"));
    	colTongTienHang.setCellValueFactory(new PropertyValueFactory<>("tongTienHang"));

        
        
        doanhThuData = FXCollections.observableArrayList();
        doanhThuTable.setItems(doanhThuData);
        
        
    }

    public void hienThiDSLoiNhuan() {
    	
    	colNgayLapLN.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));
    	colMaHoaDonLN.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
    	colTenSanPhamLN.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
    	colSoLuongLN.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
    	colGiaNhapLN.setCellValueFactory(new PropertyValueFactory<>("giaNhap"));
    	colGiaBanLN.setCellValueFactory(new PropertyValueFactory<>("giaBan"));
    	colLoiNhuan.setCellValueFactory(new PropertyValueFactory<>("loiNhuan"));

        
        loiNhuanData = FXCollections.observableArrayList();
        loiNhuanTable.setItems(loiNhuanData);
        
    }

    @FXML
    private void handleOpenTabPane() {
        mainTabPane.setVisible(true);
    }

    @FXML
    private void handleSearch() {
    	LocalDate startDate = startDatePicker.getValue();
    	LocalDate endDate = endDatePicker.getValue();
    	if(startDate == null || endDate == null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText("vui lòng chọn ngày bắt đầu và kết thúc.");
    		alert.show();
    		return;
    	}
    	if (startDate.isAfter(endDate)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ngày bắt đầu không được lớn hơn ngày kết thúc!");
            alert.show();
            return;
        }
    	Timestamp start = Timestamp.valueOf(startDate.atStartOfDay());
    	Timestamp end = Timestamp.valueOf(endDate.atTime(23, 59, 59));
    	try {
			ThongKeBUS busThongKe = new ThongKeBUS();
            ResultSet rs = busThongKe.getThongKeTongChiTheoNgay(start, end);
			chiTieuData = FXCollections.observableArrayList();
			lblTongChiTieu.setText("");
			double tongChiTieu = 0.0;
			while(rs.next()) {
				DTO_ThongKe_ChiTieu item = new DTO_ThongKe_ChiTieu(
						rs.getString("NgayLap"),
                        rs.getString("MaPN"),
                        rs.getString("TenSP"),
                        rs.getString("MaSP"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("GiaNhap"),
                        rs.getDouble("TongChiTieu")
				);
				chiTieuData.add(item);
				tongChiTieu += item.getChiTieu();	
			}
			if(chiTieuData.isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Không có dữ liệu trong khoảng thời gian được chọn.");
				alert.show();
			}
			chiTieuTable.setItems(chiTieuData);
			lblTongChiTieu.setText("Tổng chi tiêu: "+String.format("%,.2f VND", tongChiTieu));
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Đã xảy ra lỗi: " + e.getMessage());
            alert.show();
		}
    }

    @FXML
    private void handleSearchDT() {
    	LocalDate startDate = startDatePickerDT.getValue();
    	LocalDate endDate = endDatePickerDT.getValue();
    	if(startDate == null || endDate == null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText("vui lòng chọn ngày bắt đầu và kết thúc.");
    		alert.show();
    		return;
    	}
    	if (startDate.isAfter(endDate)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ngày bắt đầu không được lớn hơn ngày kết thúc!");
            alert.show();
            return;
        }
    	Timestamp start = Timestamp.valueOf(startDate.atStartOfDay());
    	Timestamp end = Timestamp.valueOf(endDate.atTime(23, 59, 59));
    	try {
			ThongKeBUS busThongKe = new ThongKeBUS();
			ResultSet rs = busThongKe.getThongKeTongThuTheoNgay(start, end);
			doanhThuData = FXCollections.observableArrayList();
			double tongDoanhThu = 0.0;
			
			while(rs.next()) {
				DTO_ThongKe_DoanhThu item = new DTO_ThongKe_DoanhThu(
						rs.getString("NgayLap"),
                        rs.getString("MaHD"),
                        rs.getString("TenSP"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("GiaBan"),			
                        rs.getDouble("TongTienHang"),
                        rs.getDouble("TongDoanhThu")
				);
				doanhThuData.add(item);
				tongDoanhThu += item.getTongTienHang();
				
			}
			if(doanhThuData.isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Không có dữ liệu trong khoảng thời gian được chọn.");
				alert.show();
			}
			doanhThuTable.setItems(doanhThuData);
			lblTongDoanhThu.setText("Tổng doanh thu: "+String.format("%,.2f VND", tongDoanhThu));
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Đã xảy ra lỗi: " + e.getMessage());
            alert.show();
		}
    }

    @FXML
    private void handleSearchLN() {
    	LocalDate startDate = startDatePickerLN.getValue();
    	LocalDate endDate = endDatePickerLN.getValue();
    	if(startDate == null || endDate == null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText("vui lòng chọn ngày bắt đầu và kết thúc.");
    		alert.show();
    		return;
    	}
    	if (startDate.isAfter(endDate)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ngày bắt đầu không được lớn hơn ngày kết thúc!");
            alert.show();
            return;
        }
    	Timestamp start = Timestamp.valueOf(startDate.atStartOfDay());
    	Timestamp end = Timestamp.valueOf(endDate.atTime(23, 59, 59));
    	try {
			ThongKeBUS busThongKe = new ThongKeBUS();
			ResultSet rs = busThongKe.getThongKeLoiNhuanTheoNgay(start, end);
			loiNhuanData = FXCollections.observableArrayList();
			double tongLoiNhuan = 0.0;
			while(rs.next()) {
				DTO_ThongKe_LoiNhuan item = new DTO_ThongKe_LoiNhuan(
						rs.getString("NgayLap"),
                        rs.getString("MaHD"),
                        rs.getString("TenSP"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("GiaNhap"),
                        rs.getDouble("GiaBan"),
                        rs.getDouble("LoiNhuan")
				);
				loiNhuanData.add(item);
				tongLoiNhuan += item.getLoiNhuan();
			}
			if(loiNhuanData.isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Không có dữ liệu trong khoảng thời gian được chọn.");
				alert.show();
			}
			loiNhuanTable.setItems(loiNhuanData);
			lblTongLoiNhuan.setText("Tổng lợi nhuận: "+String.format("%,.2f VND", tongLoiNhuan));
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Đã xảy ra lỗi: " + e.getMessage());
            alert.show();
		}
    }

    private void loadAllData() {
        try {
            // Khi vào trang, gọi để lấy toàn bộ dữ liệu
            ThongKeBUS busThongKe = new ThongKeBUS();
            ResultSet rs = busThongKe.getThongKeTatCaTongChi();
            
            chiTieuData = FXCollections.observableArrayList();
            double tongChiTieu = 0.0;

            while (rs.next()) {
                DTO_ThongKe_ChiTieu item = new DTO_ThongKe_ChiTieu(
                        rs.getString("NgayLap"),
                        rs.getString("MaPN"),
                        rs.getString("TenSP"),
                        rs.getString("MaSP"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("GiaNhap"),
                        rs.getDouble("TongChiTieu")
                );
                chiTieuData.add(item);
                tongChiTieu += item.getChiTieu();
            }

            if (chiTieuData.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Không có dữ liệu trong cơ sở dữ liệu.");
                alert.show();
            }

            chiTieuTable.setItems(chiTieuData);
            lblTongChiTieu.setText("Tổng chi tiêu: " + String.format("%,.2f VND", tongChiTieu));

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Đã xảy ra lỗi: " + e.getMessage());
            alert.show();
        }
    }

    private void loadAllDataDT() {
        try {
            // Khi vào trang, gọi để lấy toàn bộ dữ liệu
            ThongKeBUS busThongKe = new ThongKeBUS();
            ResultSet rs = busThongKe.getThongKeTatCaTongThu();
            
            doanhThuData = FXCollections.observableArrayList();
            double tongDoanhThu = 0.0;
			
			while(rs.next()) {
				DTO_ThongKe_DoanhThu item = new DTO_ThongKe_DoanhThu(
						rs.getString("NgayLap"),
                        rs.getString("MaHD"),
                        rs.getString("TenSP"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("GiaBan"),			
                        rs.getDouble("TongTienHang"),
                        rs.getDouble("TongDoanhThu")
				);
				doanhThuData.add(item);
				tongDoanhThu += item.getTongTienHang();
				
			}
			if(doanhThuData.isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Không có dữ liệu trong khoảng thời gian được chọn.");
				alert.show();
			}
			doanhThuTable.setItems(doanhThuData);
			lblTongDoanhThu.setText("Tổng doanh thu: "+String.format("%,.2f VND", tongDoanhThu));
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Đã xảy ra lỗi: " + e.getMessage());
            alert.show();
        }
    }

    private void loadAllDataLN() {
        try {
            // Khi vào trang, gọi để lấy toàn bộ dữ liệu
            ThongKeBUS busThongKe = new ThongKeBUS();
            ResultSet rs = busThongKe.getThongKeTatCaLoiNhuan();
            
            doanhThuData = FXCollections.observableArrayList();
            double tongLoiNhuan = 0.0;
			while(rs.next()) {
				DTO_ThongKe_LoiNhuan item = new DTO_ThongKe_LoiNhuan(
						rs.getString("NgayLap"),
                        rs.getString("MaHD"),
                        rs.getString("TenSP"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("GiaNhap"),
                        rs.getDouble("GiaBan"),
                        rs.getDouble("LoiNhuan")
				);
				loiNhuanData.add(item);
				tongLoiNhuan += item.getLoiNhuan();
			}
			if(loiNhuanData.isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Không có dữ liệu trong khoảng thời gian được chọn.");
				alert.show();
			}
			loiNhuanTable.setItems(loiNhuanData);
			lblTongLoiNhuan.setText("Tổng lợi nhuận: "+String.format("%,.2f VND", tongLoiNhuan));
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Đã xảy ra lỗi: " + e.getMessage());
            alert.show();
        }
    }

    private void switchPanel(VBox showPanel, VBox hidePanel1, VBox hidePanel2) {
        showPanel.setVisible(true);
        hidePanel1.setVisible(false);
        hidePanel2.setVisible(false);
    }
    
}
