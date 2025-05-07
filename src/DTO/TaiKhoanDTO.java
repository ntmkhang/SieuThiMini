package DTO;

public class TaiKhoanDTO {
    private int maTK;
    private String tenTK;

    public TaiKhoanDTO(int maTK, String tenTK) {
        this.maTK = maTK;
        this.tenTK = tenTK;
    }

    public int getMaTK() { return maTK; }
    public String getTenTK() { return tenTK; }
}
