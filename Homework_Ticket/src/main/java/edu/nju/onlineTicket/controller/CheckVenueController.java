package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.SubVenue;
import edu.nju.onlineTicket.model.Venue;
import edu.nju.onlineTicket.service.VenueService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by asus1 on 2018/3/10.
 */
@Controller
public class CheckVenueController {

    @Autowired
    VenueService venueService;

    @RequestMapping(value = "getApplyList")
    public void getSampleList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Venue> venues = venueService.getAllApply();
        JSONArray array = JSONArray.fromObject(venues);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping(value = "getApplyListByType")
    public void getApplyListByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int type = Integer.parseInt(request.getParameter("type"));
        ArrayList<Venue> venues = venueService.getApplyListByType(type);
        JSONArray array = JSONArray.fromObject(venues);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping(value = "checkApply")
    public void checkApply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String venueID = request.getParameter("venueID");
        int state = Integer.parseInt(request.getParameter("state"));
        response.setCharacterEncoding("UTF-8");
        if(venueService.checkApply(venueID,state))
            response.getWriter().print("Success");
        else
            response.getWriter().print("Fail");
    }

    @RequestMapping(value = "getModifyApplyListByType")
    public void getModifyApplyListByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int type = Integer.parseInt(request.getParameter("type"));
        ArrayList<SubVenue> subVenues = venueService.getModifyApplyListByType(type);
        JSONArray array = JSONArray.fromObject(subVenues);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping(value = "passModifyVenue")
    public void passModifyVenue(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String venueID = request.getParameter("venueID");
        String subVenueID = request.getParameter("subVenueID");
        response.setCharacterEncoding("UTF-8");
        if(venueService.passModifyVenue(venueID,subVenueID)) {         //修改了副表中的状态 并更新了场馆信息
            Venue venue = venueService.findVenue(venueID);
            request.getSession().setAttribute("venue",venue);       //更新最新的场馆信息
            response.getWriter().print("Success");
        }
        else {
            response.getWriter().print("Fail");
        }
    }

    @RequestMapping(value = "refuseModifyVenue")
    public void refuseModifyVenue(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String venueID = request.getParameter("venueID");
        String subVenueID = request.getParameter("subVenueID");
        String reason = request.getParameter("reason");
        response.setCharacterEncoding("UTF-8");
        if(venueService.refuseModifyVenue(venueID,subVenueID,reason)) {         //修改了副表中的状态 未更新了场馆信息 但是返回原因
            Venue venue = venueService.findVenue(venueID);
            request.getSession().setAttribute("venue",venue);       //更新最新的场馆信息
            response.getWriter().print("Success");
        }
    }
}
