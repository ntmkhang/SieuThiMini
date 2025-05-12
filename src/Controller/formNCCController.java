package Controller;


import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class formNCCController {

    @FXML
    private Label btnCancel;

    @FXML
    private ImageView btnCross;

    @FXML
    private Label btnSave;

    @FXML
    private Pane containSave;

    @FXML
    private TextField inputDiaChi;

    @FXML
    private TextField inputNguoiLH;

    @FXML
    private TextField inputSdt;

    @FXML
    private TextField inputTen;

    @FXML
    private Label labelMaNCC;

    @FXML
    private Label labelTitle;

    @FXML
    private Label warnDiachi;

    @FXML
    private Label warnNguoiLH;

    @FXML
    private Label warnSdt;

    @FXML
    private Label warnTen;

    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    private String option;
    private NhaCungCapDTO ncc;
    private NhaCungCapController nccController;
    private NhapHangController nhaphang;

    public void setNhapHangController(NhapHangController nhaphang){
        this.nhaphang = nhaphang;
    }

    public void setNccController(NhaCungCapController nccController){
        this.nccController = nccController;
    }

    public void setInforNCC(NhaCungCapDTO ncc){
        this.ncc = ncc;
    }

    public void setOption(String option) {
        this.option = option;
        if(option.equals("Sửa")){
            labelTitle.setText("Thông tin nhà cung cấp");
            labelMaNCC.setText(String.valueOf(ncc.getMaNCC()));
            inputTen.setText(ncc.getTenNCC());
            inputSdt.setText(ncc.getSoDT());
            inputDiaChi.setText(ncc.getDiaChi());
            inputNguoiLH.setText(ncc.getNguoiLienHe());
        }
        if(option.equals("Tạo")){
            btnSave.setText("Tạo");
            labelMaNCC.setText(String.valueOf(nccBUS.taoMaNCC()));
        }
    }

    public boolean checkInput(){
        warnTen.setText("");
        warnSdt.setText("");
        warnDiachi.setText("");
        warnNguoiLH.setText("");
        boolean check = true;
        if(inputTen.getText().isEmpty()){
            warnTen.setText("Vui lòng nhập tên *");
            check = false;
        }
        if(inputSdt.getText().isEmpty()){
            warnSdt.setText("Vui lòng nhập số điện thoại *");
            check = false;
        } else if(!inputSdt.getText().matches("^09[0-9]{8}$")){
            warnSdt.setText("Vui lòng nhập đúng định dạng số điện thoại *");
            check = false;
        }
        if(inputDiaChi.getText().isEmpty()){
            warnDiachi.setText("Vui lòng nhập địa chỉ *");
            check = false;
        }
        if(inputNguoiLH.getText().isEmpty()) {
            warnNguoiLH.setText("Vui lòng nhập tên ngưới liên hệ *");
            check = false;
        }
        return check;
    }

    public void alertMessage(String message){
        if(message.contains("thành công")){
            Alert alSuccess = new Alert(AlertType.INFORMATION);
            alSuccess.setTitle("Thông báo");
            alSuccess.setHeaderText("Thành công");
            alSuccess.setContentText(message);
            alSuccess.showAndWait();
        } else {
            Alert alFail = new Alert(AlertType.ERROR);
            alFail.setTitle("Thông báo");
            alFail.setHeaderText("Thất bại");
            alFail.setContentText(message);
            alFail.showAndWait();
        }
        if (containSave != null && containSave.getScene() != null) {
            Stage stage = (Stage) containSave.getScene().getWindow();
            stage.close();
        } else {
            return;
        }
    }

    @FXML
    void btnCancelClicked(MouseEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnCancelEntered(MouseEvent event) {
        String currentStyle = btnCancel.getStyle();
        btnCancel.setStyle(currentStyle + "-fx-background-color:  #C0C0C0;");
    }

    @FXML
    void btnCancelExited(MouseEvent event) {
        String currentStyle = btnCancel.getStyle();
        btnCancel.setStyle(currentStyle + "-fx-background-color: transparent;");
    }

    @FXML
    void btnSaveClicked(MouseEvent event) {
        if(option.equals("Tạo")){
            NhaCungCapDTO ncc = new NhaCungCapDTO();
            if(checkInput()){
                if(nccBUS.ktSdt(inputSdt.getText(), Integer.parseInt(labelMaNCC.getText()))){
                    warnSdt.setText("Số điện đã tồn tại vui lòng nhập lai");
                } else {
                    ncc.setTenNCC(inputTen.getText());
                    ncc.setSoDT(inputSdt.getText());
                    ncc.setDiaChi(inputDiaChi.getText());
                    ncc.setNguoiLienHe(inputNguoiLH.getText());
                    ncc.setIs_Deleted(0);
                    alertMessage(nccBUS.themNCC(ncc));
                    if (nccController != null) {
                        nccController.refreshDataNCC();
                    }
                    if(nhaphang != null){
                        nhaphang.refreshCbb();
                    }
                }
            }
        }
        if(option.equals("Sửa")){
            if(checkInput()){
                if(nccBUS.ktSdt(inputSdt.getText(),ncc.getMaNCC())){
                    warnSdt.setText("Số điện đã tồn tại vui lòng nhập lai");
                } else {
                    ncc.setTenNCC(inputTen.getText());
                    ncc.setDiaChi(inputDiaChi.getText());
                    ncc.setSoDT(inputSdt.getText());
                    ncc.setNguoiLienHe(inputNguoiLH.getText());
                    alertMessage(nccBUS.updateNCC(ncc));
                    nccController.refreshDataNCC();
                }
            }
        }
    }

    @FXML
    void btnSaveEntered(MouseEvent event) {
        String currentStyle = btnSave.getStyle();
        btnSave.setStyle(currentStyle + "-fx-background-color:   #CFEBFC;");
    }

    @FXML
    void btnSaveExited(MouseEvent event) {
        String currentStyle = btnSave.getStyle();
        btnSave.setStyle(currentStyle + "-fx-background-color: transparent;");
    }

    @FXML
    void btnCloseForm(MouseEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
