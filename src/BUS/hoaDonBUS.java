package BUS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import DAO.hoaDonDAO;
import DTO.CThoaDonDTO;
import DTO.hoaDonDTO;

public class hoaDonBUS {
    private hoaDonDAO hoaDonDAO;

    public hoaDonBUS(){
        hoaDonDAO = new hoaDonDAO();
    }

    public ArrayList<hoaDonDTO> getAllHoaDon (){
        return hoaDonDAO.getAllHoaDon();
    }

    public boolean addHoaDon (hoaDonDTO hoaDon){
        return hoaDonDAO.themHoaDon(hoaDon);
    }

    public int getMaHD (){
        return hoaDonDAO.getMaHD();
    }

    public hoaDonDTO getHoaDonbyMaHD (int maHD){
        return hoaDonDAO.getHoaDonbyMaHD(maHD);
    }

    public ArrayList<hoaDonDTO> searchHoaDon (int maHD, int maKH, java.sql.Date startDate, java.sql.Date endDate){
        return hoaDonDAO.searchHoaDon(maHD, maKH, startDate, endDate);
    }

    public ArrayList<CThoaDonDTO> getAllCTHoaDon (){
        return hoaDonDAO.getAllCThoaDon();
    }

    public ArrayList<CThoaDonDTO> getCThoaDonbyMaHD (int maHD){
        return hoaDonDAO.getCThoaDonbyMaHD(maHD);
    }

    public boolean addCThoaDon (CThoaDonDTO cthd){
        return hoaDonDAO.themCThoaDon(cthd);
    }
}
