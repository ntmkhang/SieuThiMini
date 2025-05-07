package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import BUS.*;
import DTO.*;
import DAO.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.LocalDateStringConverter;
import javafx.scene.control.Alert.AlertType;
import com.itextpdf.layout.element.Paragraph;


public class QLBHController implements javafx.fxml.Initializable{

    @FXML
    private TableView<hoaDonDTO> HoaDonTable;

    @FXML
    private TableView<lichSuDiemDTO> LichSuDiemTable;

    @FXML
    private Button btn_themKhachHang;

    @FXML
    private TableColumn<CThoaDonDTO, Integer> colCTGiaBan;

    @FXML
    private TableColumn<CThoaDonDTO, Integer> colCTMaHD;

    @FXML
    private TableColumn<CThoaDonDTO, Integer> colCTMaSP;

    @FXML
    private TableColumn<CThoaDonDTO, Integer> colCTSoLuong;

    @FXML
    private TableColumn<CThoaDonDTO, Integer> colCTThanhTien;

    @FXML
    private TableColumn<CThoaDonDTO, String> colCTTenSP;

    @FXML
    private TableColumn<lichSuDiemDTO, Integer> colLSDiem;

    @FXML
    private TableColumn<lichSuDiemDTO, Integer> colLSMaGD;

    @FXML
    private TableColumn<lichSuDiemDTO, Integer> colLSMaHD;

    @FXML
    private TableColumn<lichSuDiemDTO, Integer> colLSMaKH;

    @FXML
    private TableColumn<lichSuDiemDTO, Date> colLSNgayTichLuy;

    @FXML
    private TableColumn<lichSuDiemDTO, String> colLSTenKH;

    @FXML
    private TableColumn<lichSuDiemDTO, String> colLSLoaiGD;

    @FXML
    private TableColumn<hoaDonDTO, Integer> colMaHD;

    @FXML
    private TableColumn<hoaDonDTO, Integer> colMaKH;

    @FXML
    private TableColumn<hoaDonDTO, Integer> colMaNV;

    @FXML
    private TableColumn<hoaDonDTO, Date> colNgayLap;

    @FXML
    private TableColumn<hoaDonDTO, String> colHinhThuc;

    @FXML
    private TableColumn<hoaDonDTO, Integer> colTienGiam;

    @FXML
    private TableColumn<hoaDonDTO, Integer> colThanhTien;

    @FXML
    private TableColumn<hoaDonDTO, Integer> colTienKhachDua;

    @FXML
    private TableColumn<hoaDonDTO, Integer> colTienTraLai;

    @FXML
    private TableColumn<hoaDonDTO, Integer> colTongTien;

    @FXML
    private DatePicker endHDDatePicker;

    @FXML
    private DatePicker endLSDatePicker;

    @FXML
    private Label sellNamelbl;

    @FXML
    private Label sellPointlbl;

    @FXML
    private Button openChiTietHoaDonBTN;

    @FXML
    private TableColumn<sanPhamDTO, Integer> sellColPrice;

    @FXML
    private TableColumn<sanPhamDTO, String> sellColProduct;

    @FXML
    private TableColumn<sanPhamDTO, Integer> sellColQuantity;

    @FXML
    private TextField sellDiemApDung;

    @FXML
    private AnchorPane sellForm;

    @FXML
    private GridPane sellGridPane;

    @FXML
    private Button sellInBTN;

    @FXML
    private Label sellMaNV;

    @FXML
    private ScrollPane sellScrollPane;

    @FXML
    private Button sellSearchBTN;

    @FXML
    private TextField sellSearchBar;

    @FXML
    private ComboBox<String> sellSearchCB;

    @FXML
    private ComboBox<String> sellSearchHoaDonCB;

    @FXML
    private ComboBox<String> sellSearchTichDiemCB;

    @FXML
    private ComboBox<String> sellHinhThucCB;

    @FXML
    private Button sellSearchHDBTN;

    @FXML
    private TextField sellSearchHDBar;

    @FXML
    private Button sellSearchLSBTN;

    @FXML
    private TextField sellSearchLSBar;

    @FXML
    private TextField sellSoDienThoai;

    @FXML
    private Button sellSoDienThoaiBTN;

    @FXML
    private TableView<sanPhamDTO> sellTableView;

    @FXML
    private Label sellTenNV;

    @FXML
    private Label sellThanhTien;

    @FXML
    private Button sellThanhToanBTN;

    @FXML
    private Label sellTienGiam;

    @FXML
    private TextField sellTienKhachTra;

    @FXML
    private Label sellTienTraLai;

    @FXML
    private Label sellTongTien;

    @FXML
    private Button sellXoaBTN;

    @FXML
    private DatePicker startHDDatePicker;

    @FXML
    private DatePicker startLSDatePicker;

    @FXML
    private TableView<CThoaDonDTO> tbl_CTHoaDon;

    private ObservableList<sanPhamDTO> sellItems = FXCollections.observableArrayList();

    private loaiSanPhamBUS loaiSanPhamBUS = new loaiSanPhamBUS();
    private hoaDonBUS hoaDonBUS = new hoaDonBUS();
    private lichSuDiemBUS lichSuDiemBUS = new lichSuDiemBUS();
    private khachHangBUS khachHangBUS = new khachHangBUS();
    private sanPhamBUS sanPhamBUS = new sanPhamBUS();
    private LoginBUS loginBUS = new LoginBUS();

    private int lastHoaDon = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Locale vietnameseLocale = new Locale("vi", "VN");
         // Cài đặt Locale cho DatePicker và định dạng ngày tháng
        DateTimeFormatter vietnameseFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", vietnameseLocale);

        // Cài đặt cho startHDDatePicker
        startHDDatePicker.setConverter(new LocalDateStringConverter(vietnameseFormatter, vietnameseFormatter));
        startHDDatePicker.setPromptText("Chọn ngày bắt đầu");

        // Cài đặt cho endHDDatePicker
        endHDDatePicker.setConverter(new LocalDateStringConverter(vietnameseFormatter, vietnameseFormatter));
        endHDDatePicker.setPromptText("Chọn ngày kết thúc");

        sellSearchHoaDonCB.getItems().addAll("Mã hóa đơn", "Mã khách hàng");
        sellSearchHoaDonCB.setValue("Mã hóa đơn"); // set gia tri mac dinh cho cb

        startLSDatePicker.setConverter(new LocalDateStringConverter(vietnameseFormatter, vietnameseFormatter));
        startLSDatePicker.setPromptText("Chọn ngày bắt đầu");

        endLSDatePicker.setConverter(new LocalDateStringConverter(vietnameseFormatter, vietnameseFormatter));
        endLSDatePicker.setPromptText("Chọn ngày kết thúc");

        sellSearchTichDiemCB.getItems().addAll("Mã hóa đơn", "Mã khách hàng");
        sellSearchTichDiemCB.setValue("Mã hóa đơn");
        

        ArrayList<lichSuDiemDTO> lichSuDiemList = lichSuDiemBUS.getAllLichSuDiem();
        ArrayList<hoaDonDTO> hoaDonList = hoaDonBUS.getAllHoaDon();

        loadDataLichSuDiem(lichSuDiemList);
        loadDataDanhSachHoaDon(hoaDonList);

        //show sản phẩm trong gridpane bán hàng 
        showProduct(sanPhamBUS.getAllSanPham());

        setLoaiSanPham();
        //set up bảng sản phẩm trong giỏ hàng
        sellColProduct.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
        sellColQuantity.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        sellColPrice.setCellValueFactory(new PropertyValueFactory<>("gia"));
        
        sellTableView.setItems(sellItems);

        sellHinhThucCB.setItems(FXCollections.observableArrayList("Tiền mặt", "Chuyển khoản"));

        sellItems.addListener(new ListChangeListener<sanPhamDTO>() {
            @Override
            public void onChanged(Change<? extends sanPhamDTO> change) {
                updateTongTien(); // Cập nhật tổng tiền mỗi khi có sự thay đổi
            }
        });

        updateTongTien();

        // Listener theo dõi sự thay đổi giá trị của sellTongTien và sellTienGiam
        sellTongTien.textProperty().addListener((observable, oldValue, newValue) -> updateThanhTien());
        sellTienGiam.textProperty().addListener((observable, oldValue, newValue) -> updateThanhTien());
        sellTienKhachTra.textProperty().addListener((observable, oldValue, newValue) -> updateTienTraLai());
        sellThanhTien.textProperty().addListener((observable, oldValue, newValue) -> updateThanhTien());
    }

    @SuppressWarnings("static-access")
    public void showProduct(ArrayList<sanPhamDTO> sanPham){
        sellGridPane.getChildren().clear();
        int column =0;
        int row = 1;
        try {
            for (sanPhamDTO sanPhamDTO : sanPham) {
                //tải FXML của mỗi sản phẩm 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CardProductGUI.fxml"));
                AnchorPane sanPhamCard = loader.load();

                CardProductController cardController = loader.getController();

                cardController.thongTinSanPham(
                    sanPhamDTO.getTenSP(),
                    sanPhamDTO.getGia(),
                    "C:/Users/ntmkh/OneDrive/Documents/SieuThiMini/SieuThiMini/Assets/img/Product/" + sanPhamDTO.getHinhAnh(),
                    sanPhamDTO.getSoLuong(),
                    sanPhamDTO
                    );

                    cardController.setQLBHController(this);

                    sellGridPane.add(sanPhamCard, column++, row);
                    sellGridPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
                    sellGridPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
                    sellGridPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
                    sellGridPane.setMargin(sanPhamCard, new Insets(10));

                    if (column == 3){
                        column = 0;
                        row ++;
                    }
                }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDataLichSuDiem (ArrayList<lichSuDiemDTO> lichSuDiem){
        ObservableList <lichSuDiemDTO> lichSuDiemOL = FXCollections.observableArrayList(lichSuDiem);
        colLSMaGD.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMaGiaoDich()).asObject());
        colLSMaKH.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMaKH()).asObject());
        colLSMaHD.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMaHD()).asObject());
        colLSDiem.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getDiem()).asObject());
        colLSTenKH.setCellValueFactory(data -> new SimpleStringProperty(khachHangBUS.getTenKHbyMaKH(data.getValue().getMaKH())));
        colLSNgayTichLuy.setCellValueFactory(data -> new SimpleObjectProperty <>(data.getValue().getNgayTichLuy()));
        colLSLoaiGD.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLoaiGD()));

        LichSuDiemTable.setItems(lichSuDiemOL);;
    }

    public void loadDataDanhSachHoaDon (ArrayList<hoaDonDTO> hoaDon){
        ObservableList <hoaDonDTO> hoaDonOL = FXCollections.observableArrayList(hoaDon);
        colMaHD.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMaHD()).asObject());
        colNgayLap.setCellValueFactory(data -> new SimpleObjectProperty <> (data.getValue().getNgayLap()));
        colHinhThuc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHinhThuc())); 
        colTongTien.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getTongTien()).asObject());
        colTienGiam.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getTienGiam()).asObject());
        colThanhTien.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getThanhTien()).asObject());
        colTienKhachDua.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getTienKhachDua()).asObject());
        colTienTraLai.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getTienTraLai()).asObject());
        colMaKH.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMaKH()).asObject());
        colMaNV.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMaNV()).asObject());

       HoaDonTable.setItems(hoaDonOL);
    }

    @FXML
    public void handleHoaDonClicked (MouseEvent event){
        colCTMaHD.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMaHD()).asObject());
        colCTMaSP.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMaSP()).asObject());
        colCTTenSP.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTenSP()));
        colCTSoLuong.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getSoLuong()).asObject());
        colCTGiaBan.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getGiaBan()).asObject());
        colCTThanhTien.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getThanhTien()).asObject());
        hoaDonDTO selected = HoaDonTable.getSelectionModel().getSelectedItem();

        if(selected!= null){
            ObservableList <CThoaDonDTO> CThoaDonOL = FXCollections.observableArrayList(hoaDonBUS.getCThoaDonbyMaHD(selected.getMaHD()));
            tbl_CTHoaDon.setItems(CThoaDonOL);
        }
    }

    @FXML
    public void handlleSearchLSHDBTN(ActionEvent event) {
        String selected = sellSearchHoaDonCB.getValue();
        int maKH = -1;
        int maHD = -1;
        String value = sellSearchHDBar.getText().trim();
        if (selected!= null){
            if (selected.equals("Mã hóa đơn")){
                try {
                    if(!value.isEmpty()){
                        maHD = Integer.parseInt(value);
                    }
                } catch (NumberFormatException e) {
                        maHD = -1;
                }
            }
            if (selected.equals("Mã khách hàng")){
                try {
                    if(!value.isEmpty()){
                        maKH = Integer.parseInt(value);
                    }
                } catch (NumberFormatException e) {
                    maKH =-1;
                }
            }
        }
        java.sql.Date startDate = null;
        java.sql.Date endDate = null;

        if(startHDDatePicker.getValue() ==null && endHDDatePicker.getValue()!=null || 
        startHDDatePicker.getValue() !=null && endHDDatePicker.getValue()==null){
            showAlert(Alert.AlertType.WARNING,"Thiếu thông tin", "Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if (startHDDatePicker.getValue()!=null && endHDDatePicker.getValue()!=null && 
        startHDDatePicker.getValue().isAfter(endHDDatePicker.getValue()) ) {
            showAlert(Alert.AlertType.WARNING, "Không hợp lệ", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
            return;
        }

        if(startHDDatePicker.getValue() !=null && endHDDatePicker.getValue() != null){
            startDate = java.sql.Date.valueOf(startHDDatePicker.getValue());
            endDate = java.sql.Date.valueOf(endHDDatePicker.getValue());
        }

        ArrayList<hoaDonDTO> result = hoaDonBUS.searchHoaDon(maHD, maKH, startDate, endDate);
        loadDataDanhSachHoaDon(result);
    }

    @FXML
    void handlleSearchLSTDBTN(ActionEvent event) {
        int maKH = -1;
        int maHD = -1;
        String selected = sellSearchTichDiemCB.getValue();
        String value = sellSearchLSBar.getText().trim();
        if(selected!=null){
            if (selected.equals("Mã hóa đơn")) {
                try {    
                    if(!value.isEmpty())
                        maHD = Integer.parseInt(value);        
                }
                catch (NumberFormatException e) {
                    maHD = -1;
                } 
            } if(selected.equals("Mã khách hàng")){
                try {
                    if(!value.isEmpty())
                        maKH = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    maKH = -1;
                }
            }
        }

        java.sql.Date startDate = null;
        java.sql.Date endDate = null;
        if( endLSDatePicker.getValue()!=null && startLSDatePicker.getValue()== null ||
            endLSDatePicker.getValue()==null && startLSDatePicker.getValue()!=null){
            showAlert(Alert.AlertType.WARNING, "Thiếu thông tin", "Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if(startLSDatePicker.getValue() != null && endLSDatePicker.getValue() != null &&
            startLSDatePicker.getValue().isAfter(endLSDatePicker.getValue())){
            showAlert(Alert.AlertType.WARNING,"Không hợp lệ", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
        }

        if(startLSDatePicker.getValue() !=null && endLSDatePicker.getValue() != null){
            startDate = java.sql.Date.valueOf(startLSDatePicker.getValue());
            endDate = java.sql.Date.valueOf(endLSDatePicker.getValue());
        }
        
        ArrayList<lichSuDiemDTO> result = lichSuDiemBUS.searchLichSuDiembyMaHD(maHD, maKH, startDate, endDate);
        loadDataLichSuDiem(result);
    }

    @FXML
    void handleInBTN(ActionEvent event) {
        if (lastHoaDon == -1) {
            showAlert(Alert.AlertType.WARNING, "Thông báo", "Chưa có hóa đơn để in.");
            return;
        }
    
        // Lấy thông tin hóa đơn từ database
        hoaDonDTO hoaDon = hoaDonBUS.getHoaDonbyMaHD(lastHoaDon);
        ArrayList<CThoaDonDTO> chiTietHoaDonList = hoaDonBUS.getCThoaDonbyMaHD(lastHoaDon);
    
        if (hoaDon == null || chiTietHoaDonList == null || chiTietHoaDonList.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không tìm thấy hóa đơn hoặc chi tiết hóa đơn.");
            return;
        }
    
        // Tạo nội dung hóa đơn để in
        String noiDungHoaDon = createInvoiceContent(hoaDon, chiTietHoaDonList);
    
        // In hóa đơn
        inHoaDon(noiDungHoaDon, hoaDon, chiTietHoaDonList);
    
        // Reset lastInvoiceId sau khi in để tránh in lại
        lastHoaDon = -1;
    }

    @FXML
    void handleSearchBTN(ActionEvent event) {
        performSearch();
    }

    @FXML
    void handleSearchCB(ActionEvent event) {
        performSearch();
    }

    @FXML
    void handleSoDienThoaiBTN(ActionEvent event) {
        String soDienThoai = sellSoDienThoai.getText().trim();
        if(!isValidPhoneNumber(soDienThoai)){
            sellTienGiam.setText("0 VND");
            showAlert(AlertType.ERROR, "Không hợp lệ!", "Vui lòng nhập số điện thoại hợp lệ!");
            return;
        }

        if(!khachHangBUS.kiemTraSoDienThoai(soDienThoai)){
            sellTienGiam.setText("0 VND");
            showAlert(AlertType.ERROR, "Không hợp lệ!", "Số điện thoại không tồn tại trong hệ thống");
        }

        int diem = khachHangBUS.getDiemTichLuybySoDienThoai(soDienThoai);
        int maKH = khachHangBUS.getMaKHbySoDienThoai(soDienThoai);
        String ten = khachHangBUS.getTenKHbyMaKH(maKH);
        sellNamelbl.setText(ten);
        sellPointlbl.setText(String.valueOf(diem));
    }

    @FXML 
    void handleDiemApDungEnter(ActionEvent event){
        String diemApDungStr = sellDiemApDung.getText().trim();

        if (diemApDungStr.isEmpty() || !isValidNumberEntry(diemApDungStr)) {
            sellTienGiam.setText("0 VND");
            showAlert(AlertType.ERROR, "Không hợp lệ!", "Vui lòng nhập số điểm hợp lệ (chỉ gồm số).");
            return;
        }

        int diemApDung = Integer.parseInt(sellDiemApDung.getText().trim());
        int diemKH = Integer.parseInt(sellPointlbl.getText().trim());

        if(diemApDung > diemKH ){
            sellTienGiam.setText("0 VND");
            showAlert(AlertType.ERROR, "Không hợp lệ!", "Điểm áp dụng phải nhỏ hơn hoặc bằng số điểm hiện có!");
            return;
        }

        if(!isValidNumberEntry(sellDiemApDung.getText().trim())){
            sellTienGiam.setText("0 VND");
            showAlert(AlertType.ERROR, "Không hợp lệ!", "Có ký tự không hợp lệ, vui lòng nhập lại");
            return;
        }

        int tienGiam = diemApDung;
        sellTienGiam.setText(String.format("%,d VND", tienGiam));
    }

    @FXML
    void handleThanhToanBTN(ActionEvent event) {
        if(sellHinhThucCB.getSelectionModel().isEmpty()){
            showAlert(Alert.AlertType.WARNING, "Chưa chọn hình thức", "Hãy chọn hình thức thanh toán.");
            return;
        }

        java.util.Date today = new java.util.Date();
        Date sqlDate = new Date(today.getTime());

        //lấy dữ liệu từ các trường nhập liệu 
        String soDienThoai = sellSoDienThoai.getText().trim();
        int tongTien = (int) Double.parseDouble(sellTongTien.getText().replace(" VND", "").replace(",", ""));
        int thanhTien = (int) Double.parseDouble(sellThanhTien.getText().replace(" VND", "").replace(",", ""));
        int tienGiam = (int) Double.parseDouble(sellTienGiam.getText().replace(" VND", "").replace(",", ""));
        int tienTraLai = (int) Double.parseDouble(sellTienTraLai.getText().replace(" VND", "").replace(",", ""));

        if (tongTien == 0 ){
            showAlert(Alert.AlertType.WARNING, "Chưa chọn sản phẩm!", "Vui lòng chọn sản phẩm!");
            return;
        }

        if (sellTienKhachTra.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Chưa trả tiền!", "Vui lòng nhập tiền khách trả!");
            return;
        }

        int tienKhachTra = (int) Double.parseDouble(sellTienKhachTra.getText().replace(" VND", "").replace(",", ""));

        if (tienTraLai < 0) {
            showAlert(Alert.AlertType.WARNING, "Chưa trả đủ!", "Khách hàng chưa trả đủ tiền!");
            return;
        }

        int maKH = 0; // Mặc định mã khách hàng là 0 nếu không có số điện thoại
        if (!soDienThoai.isEmpty()) {
            maKH = khachHangBUS.getMaKHbySoDienThoai(soDienThoai);
        }

        hoaDonDTO hoadon = new hoaDonDTO();
        hoadon.setNgayLap(sqlDate);
        hoadon.setHinhThuc(sellHinhThucCB.getSelectionModel().getSelectedItem());
        hoadon.setThanhTien(thanhTien);
        hoadon.setTienGiam(tienGiam);
        hoadon.setTienKhachDua(tienKhachTra);
        hoadon.setTienTraLai(tienTraLai);
        hoadon.setTongTien(tongTien);
        hoadon.setMaNV(Integer.parseInt(sellMaNV.getText().trim()));
        hoadon.setMaKH(maKH);
        
        String check = sellDiemApDung.getText().trim();
        int diemTru =0;
        
        if(!check.isEmpty()){
            diemTru = Integer.parseInt(sellDiemApDung.getText().trim());
        }
        
        hoaDonBUS.addHoaDon(hoadon);

        khachHangBUS.subtractDiemTichLuy(maKH, diemTru);

        int maHD = hoaDonBUS.getMaHD();
        lastHoaDon = maHD;
        
        for (sanPhamDTO sanPhamDTO : sellItems) {
            CThoaDonDTO cthd = new CThoaDonDTO();
            cthd.setMaHD(maHD);
            cthd.setMaSP(sanPhamDTO.getMaSP());
            cthd.setSoLuong(sanPhamDTO.getSoLuong());
            cthd.setGiaBan(sanPhamDTO.getGia());
            cthd.setThanhTien(sanPhamDTO.getGia()*sanPhamDTO.getSoLuong());
            hoaDonBUS.addCThoaDon(cthd);
            boolean result = sanPhamBUS.capNhatSoLuongSanPham(sanPhamDTO.getMaSP(), sanPhamDTO.getSoLuong());

            if(!result){
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể trừ số lượng sản phẩm trong kho.");
                return; // Dừng lại nếu không trừ được sản phẩm
            }
        }

        int diemTichLuy = thanhTien/100;
        if (diemTichLuy >0 && !soDienThoai.isEmpty()){
            lichSuDiemDTO lichSuDiem = new lichSuDiemDTO();
            lichSuDiem.setMaHD(maHD);
            lichSuDiem.setMaKH(maKH);
            lichSuDiem.setLoaiGD(sellHinhThucCB.getSelectionModel().getSelectedItem());
            lichSuDiem.setDiem(diemTichLuy);
            lichSuDiem.setNgayTichLuy(sqlDate);

            lichSuDiemBUS.addLichSuDiem(lichSuDiem);
            khachHangBUS.updateDiemTichLuy(maKH, diemTichLuy, soDienThoai);
        }

        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thanh toán thành công!");

        resetData();

    }

    @FXML
    void handleXoaBTN(ActionEvent event) {
        sanPhamDTO sanPham = sellTableView.getSelectionModel().getSelectedItem();
        if(sanPham != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa sản phẩm");
            alert.setHeaderText("Bạn có chắc muốn xóa sản phẩm này ?");
            // xử lý sự kiện người dùng 
            
            Optional<ButtonType> button = alert.showAndWait();
            if (button.isPresent() && button.get()== ButtonType.OK){
                sellTableView.getItems().remove(sanPham);
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Chưa chọn sản phẩm", "Hãy chọn sản phẩm cần xóa.");

        }
    }

    @FXML
    void handlethemKhachHang(ActionEvent event) {
        try {
            sellSoDienThoai.clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/formTHemKhachHang.fxml"));
            Parent root = loader.load();
            formThemKhachHangController controller = loader.getController();
            controller.setQLBHController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Thêm khách hàng");
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSanPhamToTable (sanPhamDTO sanPham, int soLuong){
        int tongTien = sanPham.getGia()*soLuong;
        sanPham.setSoLuong(soLuong);
        sanPham.setGia(tongTien);

        sellItems.add(sanPham);
    }

    public boolean isProductExist(sanPhamDTO currentProduct) {
        // Duyệt qua tất cả các sản phẩm trong danh sách sellItems
        for (sanPhamDTO item : sellItems) {
            // Kiểm tra xem sản phẩm hiện tại có tồn tại trong danh sách không
            if (item.getMaSP() == currentProduct.getMaSP()) {
                return true;  // Nếu có, trả về true
            }
        }
        return false;  // Nếu không tìm thấy, trả về false
    }

    public void updateProductInTable(sanPhamDTO currentProduct, int soLuong) {
        boolean isUpdated = false;
        for (sanPhamDTO item : sellItems) {
            if (item.getMaSP() == currentProduct.getMaSP()) {
                // Cập nhật số lượng và tổng giá
                item.setSoLuong(item.getSoLuong() + soLuong);
                item.setGia(item.getGia() + (currentProduct.getGia() * soLuong));
                updateTongTien();
                sellTableView.refresh();
                isUpdated = true;
                break;
            }
        }
        // Cập nhật lại bảng (TableView) nếu có
       // sellTableView.setItems(sellItems);;
          // Nếu bạn dùng TableView
    }

    public void addKhachHang (String soDienThoai){
        sellSoDienThoai.setText(soDienThoai);
        int diem = khachHangBUS.getDiemTichLuybySoDienThoai(soDienThoai);
        int maKH = khachHangBUS.getMaKHbySoDienThoai(soDienThoai);
        String ten = khachHangBUS.getTenKHbyMaKH(maKH);
        sellNamelbl.setText(ten);
        sellPointlbl.setText(String.valueOf(diem));
    }

    public void addNhanVien (String tenDangNhap) {
        int maNV = loginBUS.getMaNVbyTenDangNhap(tenDangNhap);
        String TenNV = loginBUS.getTenNVbyMaNV(maNV);
        sellTenNV.setText(TenNV);
        sellMaNV.setText(String.valueOf(maNV));
    }

    public void performSearch (){
        String ten = sellSearchBar.getText().trim();
        String loai = sellSearchCB.getValue();

        ArrayList<sanPhamDTO> sanPham = sanPhamBUS.timSanPhamTheoTenvaLoaiSanPham(ten, loai);
        showProduct(sanPham);
    }

    public void setLoaiSanPham (){
        ArrayList<String> loaiSanPham = loaiSanPhamBUS.getAlLoaiSanPham();
        loaiSanPham.add("Tất cả");

        ObservableList<String> loaiSanPhamOL = FXCollections.observableArrayList(loaiSanPham);

        sellSearchCB.setItems(loaiSanPhamOL);
        sellSearchCB.setValue("Tất cả");
    }

    public void updateTongTien (){
        int tongTien = 0;
        for (sanPhamDTO sanPhamDTO : sellItems) {
            tongTien += sanPhamDTO.getGia();
        }

        sellTongTien.setText(String.format("%,d VND", tongTien));
    }

    public void updateTienGiam(){
        try {
            int tienGiam = (int) Double.parseDouble(sellDiemApDung.getText().trim());
            sellTienGiam.setText(String.format("%,d VND", tienGiam));
        } catch (Exception e) {
            sellThanhTien.setText("0 VNĐ");
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ!");
        }
    }

    public void updateThanhTien (){
        try {
            int tongTien = (int) Double.parseDouble(sellTongTien.getText().trim().replace(" VND", "").replace(",", ""));
            int tienGiam = (int) Double.parseDouble(sellTienGiam.getText().trim().replace(" VND", "").replace(",", ""));
            int thanhTien = tongTien - tienGiam;
            sellThanhTien.setText(String.format("%,d VND", thanhTien));
        } catch (Exception e) {
            sellThanhTien.setText("0 VNĐ");
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ!");
        }
    }

    public void updateTienTraLai (){
        try {
            // Lấy giá trị từ label sellTongTien và sellTienGiam, loại bỏ ký tự VNĐ, chuyển
            // sang số thực (double)
            int thanhTien = (int) Double.parseDouble(sellThanhTien.getText().replace(" VND", "").replace(",", ""));
            int tienKhachTra = (int) Double
                    .parseDouble(sellTienKhachTra.getText().replace(" VND", "").replace(",", ""));

            // Tính toán thành tiền sau khi giảm
            int tienTraLai = tienKhachTra - thanhTien;

            // Hiển thị kết quả lên label sellThanhTien
            sellTienTraLai.setText(String.format("%,d VND", tienTraLai));
        } catch (NumberFormatException e) {
            // Xử lý nếu có lỗi khi chuyển đổi giá trị
            sellTienTraLai.setText("0 VND");
            //showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ!");
        }
    }

    private String createInvoiceContent(hoaDonDTO hoaDon, ArrayList<CThoaDonDTO> chiTietHoaDonList) {
        StringBuilder sb = new StringBuilder();
        String nv = sellTenNV.getText().trim();
    
        sb.append("======= HÓA ĐƠN BÁN HÀNG =======\n");
        sb.append("Mã hóa đơn: ").append(hoaDon.getMaHD()).append("\n");
        sb.append("Tên nhân viên: ").append(nv).append("\n");
        sb.append("Mã khách hàng: ").append(hoaDon.getMaKH()).append("\n");
        sb.append("Ngày lập: ").append(hoaDon.getNgayLap()).append("\n");
        sb.append("Hình thức thanh toán: ").append(hoaDon.getHinhThuc()).append("\n");
        sb.append("--------------------------------\n");
        sb.append(String.format("%-20s %-10s %-10s\n", "Sản phẩm", "SL", "Giá (VNĐ)"));
        sb.append("--------------------------------\n");
    
        for (CThoaDonDTO ctHoaDon : chiTietHoaDonList) {
            sb.append(String.format("%-20s %-10d %,10d\n",
                    ctHoaDon.getTenSP(),
                    ctHoaDon.getSoLuong(),
                    ctHoaDon.getGiaBan() * ctHoaDon.getSoLuong()));
        }
    
        sb.append("--------------------------------\n");
        sb.append("Tổng tiền: \t\t\t").append(String.format("%,d VNĐ", hoaDon.getTongTien())).append("\n");
        sb.append("Tiền giảm: \t\t\t").append(String.format("%,d VNĐ", hoaDon.getTienGiam())).append("\n");
        sb.append("Thành tiền: \t\t\t").append(String.format("%,d VNĐ", hoaDon.getThanhTien())).append("\n");
        sb.append("Tiền khách đưa: \t\t").append(String.format("%,d VNĐ", hoaDon.getTienKhachDua())).append("\n");
        sb.append("Tiền trả lại: \t\t\t").append(String.format("%,d VNĐ", hoaDon.getTienTraLai())).append("\n");
        sb.append("================================\n");
        sb.append("Cảm ơn Quý khách đã mua hàng!\n");
    
        return sb.toString();
    }

    private void inHoaDon(String noiDungHoaDon, hoaDonDTO hoaDon, ArrayList<CThoaDonDTO> chiTietHoaDonList) {
        Stage stage = new Stage();
        stage.setTitle("Hóa đơn thanh toán");
    
        // Tạo nội dung hóa đơn
        TextArea hoaDonText = new TextArea(noiDungHoaDon);
        hoaDonText.setEditable(false);
        hoaDonText.setWrapText(true);
        hoaDonText.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 13px;");
        hoaDonText.setPrefSize(400, 500);
    
        // Nút in
        Button printButton = new Button("In hóa đơn");
        printButton.setOnAction(e -> {
            // Đường dẫn cố định để lưu hóa đơn PDF
            String directoryPath = "C:\\Users\\ntmkh\\OneDrive\\Documents\\SieuThiMini\\SieuThiMini\\Assets\\hoadon"; // Thay đổi theo nhu cầu
            File directory = new File(directoryPath);
    
            // Kiểm tra xem thư mục có tồn tại không, nếu không thì tạo thư mục mới
            if (!directory.exists()) {
                directory.mkdirs();
            }
    
            // Tạo tên file PDF dựa trên thời gian hiện tại để đảm bảo không trùng lặp
            String fileName = generateFileName();
            File file = new File(directory, fileName);
    
            try {
                // Gọi hàm để lưu hóa đơn vào file PDF
                saveHoaDonToPDF(file, noiDungHoaDon, hoaDon, chiTietHoaDonList);
                showAlert(Alert.AlertType.INFORMATION, "In thành công", "Hóa đơn đã được lưu vào file PDF.");
            } catch (IOException ex) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể lưu hóa đơn vào file PDF.");
                ex.printStackTrace();
            }
        });
    
        VBox root = new VBox(10, hoaDonText, printButton);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
    
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); // Chặn cửa sổ chính
        stage.showAndWait();
    }

    private void saveHoaDonToPDF(File file, String noiDungHoaDon, hoaDonDTO hoaDon, ArrayList<CThoaDonDTO> chiTietHoaDonList) throws IOException {
        // Đường dẫn đến file font DejaVuSans.ttf
        String fontPath = "src/main/resources/fonts/DejaVuSans.ttf";
        
        try (PdfWriter writer = new PdfWriter(file);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {
    
            // Nhúng font có hỗ trợ Unicode tiếng Việt
            PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, true);
            document.setFont(font);
    
            // Thêm các thông tin hóa đơn vào PDF dưới dạng văn bản thuần túy
            document.add(new Paragraph("======= HÓA ĐƠN BÁN HÀNG =======").setFont(font).setBold());
            document.add(new Paragraph("Mã hóa đơn: " + hoaDon.getMaHD()).setFont(font));
            document.add(new Paragraph("Tên nhân viên: " + sellTenNV.getText().trim()).setFont(font));
            document.add(new Paragraph("Mã khách hàng: " + hoaDon.getMaKH()).setFont(font));
            document.add(new Paragraph("Ngày lập: " + hoaDon.getNgayLap()).setFont(font));
            document.add(new Paragraph("Hình thức thanh toán: " + hoaDon.getHinhThuc()).setFont(font));
            document.add(new Paragraph("--------------------------------").setFont(font));
    
            // Dữ liệu chi tiết hóa đơn
            document.add(new Paragraph(String.format("%-20s %-10s %-10s", "Sản phẩm", "SL", "Giá (VNĐ)")).setFont(font));
            document.add(new Paragraph("--------------------------------").setFont(font));
    
            // Lặp qua chi tiết hóa đơn để thêm từng sản phẩm vào PDF
            for (CThoaDonDTO ctHoaDon : chiTietHoaDonList) {
                document.add(new Paragraph(String.format("%-20s %-10d %,10d",
                        ctHoaDon.getTenSP(),
                        ctHoaDon.getSoLuong(),
                        ctHoaDon.getGiaBan() * ctHoaDon.getSoLuong())).setFont(font));
            }
    
            document.add(new Paragraph("--------------------------------").setFont(font));
            document.add(new Paragraph("Tổng tiền: " + String.format("%,d VNĐ", hoaDon.getTongTien())).setFont(font));
            document.add(new Paragraph("Tiền giảm: " + String.format("%,d VNĐ", hoaDon.getTienGiam())).setFont(font));
            document.add(new Paragraph("Thành tiền: " + String.format("%,d VNĐ", hoaDon.getThanhTien())).setFont(font));
            document.add(new Paragraph("Tiền khách đưa: " + String.format("%,d VNĐ", hoaDon.getTienKhachDua())).setFont(font));
            document.add(new Paragraph("Tiền trả lại: " + String.format("%,d VNĐ", hoaDon.getTienTraLai())).setFont(font));
            document.add(new Paragraph("================================").setFont(font));
            document.add(new Paragraph("Cảm ơn Quý khách đã mua hàng!").setFont(font));
        }
    }

    private String generateFileName() {
    // Tạo tên file với timestamp để đảm bảo không trùng lặp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fileName = "HoaDon_" + now.format(formatter) + ".pdf";
        System.out.println(fileName);
        return "HoaDon_" + now.format(formatter) + ".pdf";
    }

    
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Kiểm tra số điện thoại phải có 10 chữ số
        return phoneNumber.matches("\\d{10}");
    }

    private boolean isValidNumberEntry (String number){
        return number.matches("\\d*");
    }

    private void resetData(){
        sellItems.clear();
        updateTongTien(); //sau khi danh sách sản phẩm clear, update tổng tiền lại = 0 
        showProduct(sanPhamBUS.getAllSanPham());
        loadDataDanhSachHoaDon(hoaDonBUS.getAllHoaDon());
        loadDataLichSuDiem(lichSuDiemBUS.getAllLichSuDiem());
        sellSearchBar.clear();
        sellSearchCB.setValue("Tất cả");
        sellHinhThucCB.setPromptText("Chọn hình thức");
        sellSoDienThoai.clear();
        sellSoDienThoai.setPromptText("Số điện thoại");
        sellDiemApDung.clear();
        sellDiemApDung.setPromptText("0");
        sellNamelbl.setText("");
        sellPointlbl.setText("");
        sellSoDienThoai.setText("");
        sellTongTien.setText("0 VND");
        sellTienGiam.setText("0 VND");
        sellThanhTien.setText("0 VND");
        sellTienKhachTra.clear();
        sellTienKhachTra.setPromptText("0 VND");
        sellTienTraLai.setText("0 VND");
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
