package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.Performance;
import edu.nju.onlineTicket.service.PerformanceService;
import edu.nju.onlineTicket.service.TicketService;
import edu.nju.onlineTicket.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus1 on 2018/3/13.
 */
@Controller
public class PublishController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private VenueService venueService;

    @Autowired
    private TicketService ticketService;



    @RequestMapping(value = "publishPerformance")
    public void publishPerformance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String venueID = request.getParameter("venueID");
        String performName = request.getParameter("perform_name");
        String beginTime = request.getParameter("begin_time");
        String endTime = request.getParameter("end_time");
        String performType = request.getParameter("perform_type");  //返回值时01234
        String rowNum = request.getParameter("row_num");
        String columnNum = request.getParameter("column_num");
        String totalNum = request.getParameter("total_num");
        String description = request.getParameter("description");
        String[] seatType = request.getParameterValues("seat_type[]");
        String[] beginRow = request.getParameterValues("begin_row[]");
        String[] endRow = request.getParameterValues("end_row[]");
        String[] seatPrice = request.getParameterValues("seat_price[]");
        String location = venueService.findVenue(venueID).getLocation();
        Performance performance = new Performance();
        performance.setPerformanceName(performName);
        performance.setVenueID(Integer.parseInt(venueID));
        performance.setVenueName(venueService.findVenue(venueID).getVenueName());
        performance.setBeginTime(beginTime);
        performance.setEndTime(endTime);
        performance.setDescription(description);
        performance.setIsBalanced(0);
        performance.setRowNum(Integer.parseInt(rowNum));
        performance.setColumnNum(Integer.parseInt(columnNum));
        performance.setPerformanceType(Integer.parseInt(performType));
        performance.setTotalTickets(Integer.parseInt(totalNum));
        performance.setResidueNum(Integer.parseInt(totalNum));
        performance.setLocation(location);
        //低劣的冒泡排序换下位置……
        for(int m=0;m<beginRow.length-1;m++){
            for(int n=0;n<beginRow.length-m-1;n++){
                if(Integer.parseInt(beginRow[n])>Integer.parseInt(beginRow[n+1])){
                    String temp = beginRow[n];
                    beginRow[n] = beginRow[n+1];
                    beginRow[n+1] = temp;

                    String temp1 = endRow[n];
                    endRow[n] = endRow[n+1];
                    endRow[n+1] = temp1;

                    String temp2 = seatType[n];
                    seatType[n] = seatType[n+1];
                    seatType[n+1] = temp2;

                    String temp3 = seatPrice[n];
                    seatPrice[n] = seatPrice[n+1];
                    seatPrice[n+1] = temp3;
                }
            }
        }

        String situation = "";
        int[] seatNum = new int[seatType.length];
        for(int i=0;i<seatType.length;i++) {
            situation = situation + seatType[i]+"+";
            int num = (Integer.parseInt(endRow[i])-Integer.parseInt(beginRow[i])+1)*Integer.parseInt(columnNum);
            seatNum[i] = num;
            situation = situation+num+"+" + seatPrice[i]+";";
        }
        double min = Double.parseDouble(seatPrice[0]);
        double max = Double.parseDouble(seatPrice[0]);
        for(int j=0;j<seatPrice.length;j++){
            if(Double.parseDouble(seatPrice[j])>max)
                max = Double.parseDouble(seatPrice[j]);
            if(Double.parseDouble(seatPrice[j])<min)
                min = Double.parseDouble(seatPrice[j]);
        }
        performance.setMaxPrice(max);
        performance.setMinPrice(min);
        performance.setSeatSituation(situation);
        response.setCharacterEncoding("UTF-8");
        if(performanceService.publishPerformance(performance)) {
            int performanceID = performanceService.getPerformanceID(venueID, performName);
            ticketService.createTickets(performanceID, seatNum, seatType, seatPrice);
            response.getWriter().print("Success");
        }
        else {
            response.getWriter().print("Fail");
        }
    }

}
