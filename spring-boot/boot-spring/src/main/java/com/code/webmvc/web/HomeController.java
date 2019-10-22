package com.code.webmvc.web;

import com.code.webmvc.business.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * HomeController
 *
 * @author shunhua
 * @date 2019-10-19
 */
@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * 处理请求
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(Model model){
        homeService.execute();
        model.addAttribute("message","hello world");
        return "index";
    }
}