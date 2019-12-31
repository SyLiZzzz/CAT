package com.demo.controller;

import com.demo.util.sendsms;
import com.demo.vo.BaseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/Send")
public class SendContrller {
    @RequestMapping("/sendCode")
    public void sendCode(HttpServletRequest req, PrintWriter pw) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String strCode = sendsms.SMS(phone);
        req.getSession().setAttribute("code", strCode);
        System.out.println(strCode);
        if (strCode != "") {
            pw.write(strCode);
        }
    }
}