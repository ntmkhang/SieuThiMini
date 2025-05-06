package Controller;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import BUS.HoaDonBUS;
import BUS.SanPhamBUS;
import DTO.CTHoaDonDTO;
import DTO.HoaDonDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CTHoaDonController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<CTHoaDonDTO, Double> colGia;

    @FXML
    private TableColumn<CTHoaDonDTO, String> colSanPham;

    @FXML
    private TableColumn<CTHoaDonDTO, Integer> colSoLuong;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbID;

    @FXML
    private Label lbTongTien;

    @FXML
    private Label khachDua;

    @FXML
    private Label thanhToan;

    @FXML
    private Label traLai;

    @FXML
    private TableView<CTHoaDonDTO> tbCTHD;

    private HoaDonBUS _hoaDonBUS = new HoaDonBUS();
    private SanPhamBUS _sanPhamBUS = new SanPhamBUS();

    @FXML
    void initialize() {
    }


    public void showCTHoaDon(HoaDonDTO hoadon){
        ObservableList<CTHoaDonDTO> dsSanPham = FXCollections.observableArrayList();
         List<CTHoaDonDTO> cthdList = _hoaDonBUS.getALLSanPhamByID(hoadon.getMaHD());
         lbDate.setText(hoadon.getNgayLap().toString());
         lbID.setText(String.valueOf(hoadon.getMaHD()));
         thanhToan.setText(hoadon.getHinhThuc());
         khachDua.setText(String.valueOf(hoadon.getTienKhachDua()));
         traLai.setText(String.valueOf(hoadon.getTienTraLai()));
        
    // Chuyển danh sách thành ObservableList để hiển thị trong TableView
    colSanPham.setCellValueFactory(p->{
        int maSP = p.getValue().getMaSP();
        String tenSP = _sanPhamBUS.TimKiemTheoId(maSP).getTenSP();
        return new ReadOnlyObjectWrapper<>(tenSP);
    });

    colSoLuong.setCellValueFactory(new PropertyValueFactory<CTHoaDonDTO,Integer>("soLuong"));
    colGia.setCellValueFactory(new PropertyValueFactory<CTHoaDonDTO,Double>("giaBan"));
    dsSanPham.addAll(cthdList);

    double tongTienValue = 0;
    for (CTHoaDonDTO cthd : cthdList) {
        tongTienValue += cthd.getSoLuong() * cthd.getGiaBan();
    }

    // Cập nhật label tổng tiền
    lbTongTien.setText(String.format("%.2f", tongTienValue));
    tbCTHD.setItems(dsSanPham);
    }

}
