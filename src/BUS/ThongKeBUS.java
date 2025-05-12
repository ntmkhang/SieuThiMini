package BUS;

import DAO.ThongKeDAO;

import java.sql.ResultSet;
import java.sql.Timestamp;

public class ThongKeBUS {
    private ThongKeDAO thongkeDAO;

    public ThongKeBUS(){
        thongkeDAO = new ThongKeDAO();
    }

    public ResultSet getThongKeTongThuTheoNgay(Timestamp start, Timestamp end){
        return thongkeDAO.ThongKeTongThuTheoNgay(start, end);
    }

    public ResultSet getThongKeTatCaTongThu(){
        return thongkeDAO.ThongKeTatCaTongThu();
    }

    public ResultSet getThongKeTongChiTheoNgay(Timestamp start, Timestamp end){
        return thongkeDAO.ThongKeTongChiTheoNgay(start, end);
    }

    public ResultSet getThongKeTatCaTongChi(){
        return thongkeDAO.ThongKeTatCaTongChi();
    }

    public ResultSet getThongKeLoiNhuanTheoNgay(Timestamp start, Timestamp end){
        return thongkeDAO.ThongKeLoiNhuanTheoNgay(start, end);
    }

    public ResultSet getThongKeTatCaLoiNhuan(){
        return thongkeDAO.ThongKeTatCaLoiNhuan();
    }
}
