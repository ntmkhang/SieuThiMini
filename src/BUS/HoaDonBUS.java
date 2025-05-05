package BUS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.HoaDonDAO;
import DTO.CTHoaDonDTO;
import DTO.HoaDonDTO;

public class HoaDonBUS {
    private HoaDonDAO _hoaDonDao =new HoaDonDAO();

    public ArrayList<HoaDonDTO> getALLHD(){
        return (ArrayList<HoaDonDTO>) _hoaDonDao.getAllHoaDon();
    }

    public List<CTHoaDonDTO> getALLSanPhamByID(int maHD){
        return _hoaDonDao.getCTHoaDonsByMaHD(maHD);
    }

      public List<HoaDonDTO> searchHoaDons(String idText, LocalDate selectedDate) {
        return _hoaDonDao.searchHoaDonsByIdAndDate(idText, selectedDate);
    }
}
