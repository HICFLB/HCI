package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.User;
import edu.nju.onlineTicket.model.Venue;
import edu.nju.onlineTicket.service.UserService;
import edu.nju.onlineTicket.service.VenueService;
import edu.nju.onlineTicket.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by asus1 on 2018/3/7.
 */
@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    VenueService venueService;

    @RequestMapping(value = "/Venue")
    public String Venue() throws Exception {
        return "/views/Venue/apply";
    }

    @RequestMapping(value="/register")
    public void registration(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code= CodeUtil.generateUniqueCode();
        response.setCharacterEncoding("UTF-8");
        if(userService.findUser(email) != null){
            response.getWriter().print("UserExist");
        } else {
            User user = new User();
            user.setCode(code);
            user.setCredit(0);
            user.setEmail(email);
            user.setLevel(0);
            user.setState(0);
            user.setIsLogOff(0);
            user.setFiveOff(0);
            user.setTenOff(0);
            user.setFiftyOff(0);
            user.setHundredOff(0);
            user.setPassword(password);
            user.setUserName(username);

            if (userService.doRegister(user)) {
                model.addAttribute("username", username);
                response.getWriter().print("Success");
            } else {
                response.getWriter().print("Fail");
            }
        }
    }

    @RequestMapping(value = "/activeUser")
    public String activeUser(HttpServletRequest request, Model model) {
        String code=request.getParameter("code");
        if(userService.activeUser(code)){
            return "index";
        }else{
            return "/views/User/register";
        }
    }

    @RequestMapping(value="/apply")
    public void apply(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String venueName = request.getParameter("venue");
        String password = request.getParameter("password");
        String location = request.getParameter("location");
        String volume = request.getParameter("volume");
        String contact = request.getParameter("contact");
        response.setCharacterEncoding("UTF-8");
        Venue venue = new Venue();
        venue.setVenueName(venueName);
        venue.setContact(contact);
        venue.setFinance(0);
        venue.setLocation(location);
        venue.setNumOfBook(0);
        venue.setNumOfUnsubscribe(0);
        venue.setPassword(password);
        venue.setTotalSeat(Integer.valueOf(volume));
        venue.setIsPassed(0);
        Date date=new Date();     //获取一个Date对象
        DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
        String applyTime = simpleDateFormat.format(date);
        venue.setApplyTime(applyTime);
        Venue old = venueService.findVenueByName(venueName);
        if(old != null){
            if(old.getIsChecked()==1&&old.getIsPassed()==0){
                if (venueService.applyVenue(venue)) {
                    response.getWriter().print(venueService.findVenueByName(venueName).getIdCode());
                } else {
                    response.getWriter().print("Fail");
                }
            }else{
                response.getWriter().print("VenueExist");
            }
        } else {
            if (venueService.applyVenue(venue)) {
                response.getWriter().print(venueService.findVenueByName(venueName).getIdCode());
            } else {
                response.getWriter().print("Fail");
            }
        }
    }
}
