package com.schoki.xzddz.server.controller;

import com.schoki.xzddz.server.model.vo.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jzb219@gmail.com
 * @description :  Thymeleaf控制器
 * RestController 等于@Controller + @ResponseBody
 * @date : 2018/9/3 22:21
 */
@RequestMapping
@RestController
public class ThymeleafController {

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        //设置转换的视图，默认映射到src/main/resource/templates/{viewName}.html
        mv.setViewName("index");
        mv.addObject("title", "这是一个WEN页面");
        Author author = Author.builder()
                .age(22)
                .name("玄奘大弟子")
                .email("jzb219@gmail.com")
                .build();
        mv.addObject("author", author);
        mv.addObject("desc", "欢迎进入玄奘大弟子主页面");
        return mv;
    }

    @GetMapping("index1")
    public String index1(HttpServletRequest request) {
        request.setAttribute("title", "这是一个WEN页面");
        request.setAttribute("desc", "欢迎进入玄奘大弟子主页面");
        Author author = Author.builder().age(22).name("玄奘大弟子").email("jzb219@gmail.com").build();
        request.setAttribute("author", author);
        return "index";
    }
}
