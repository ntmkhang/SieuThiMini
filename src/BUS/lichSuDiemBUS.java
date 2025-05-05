package BUS;

import java.util.ArrayList;

import DAO.lichSuDiemDAO;
import DTO.lichSuDiemDTO;

public class lichSuDiemBUS {
    private lichSuDiemDAO lichSuDiemDAO;
    public lichSuDiemBUS(){
        lichSuDiemDAO = new lichSuDiemDAO();
    }

    public ArrayList<lichSuDiemDTO> getAllLichSuDiem (){
        return lichSuDiemDAO.getAllLichSuDiem();
    }

    public boolean addLichSuDiem (lichSuDiemDTO lichSuDiem){
        return lichSuDiemDAO.addLichSuDiem(lichSuDiem);
    }

    public ArrayList<lichSuDiemDTO> searchLichSuDiembyMaHD (int maHD, int maKH, java.sql.Date startDate, java.sql.Date endDate){
        return lichSuDiemDAO.searchLichSuDiembyMaHD(maHD, maKH, startDate, endDate);
    }
}
