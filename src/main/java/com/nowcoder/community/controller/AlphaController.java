package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")//路径
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot!";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset = utf-8");
        try(
                PrintWriter writer = response.getWriter();
        ){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //GET请求
    // 例如：/student?current=1&limit=20，分页显示学生，当前第一页，每页限制20
    @RequestMapping(path = "/student",method = RequestMethod.GET)//只处理GET请求
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            //名是current传入current里，也可以不传，不传的话默认值为1
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some stdents";
    }

    //也可以使用/student/123（也可以用/student?id=123）
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    //POST请求
    //由于GET请求的数据都在路径上显示，路径长度有限，所以提交数据量有限，所以提交数据时用POST请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","30");
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name","辽宁大学");
        model.addAttribute("age","73");
        return "/demo/view";
    }

    //响应JSON数据（异步请求）
    //Java对象 --> JSON字符串 --> JS对象
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp() {
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps() {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",20);
        emp.put("salary",9500.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","王五");
        emp.put("age",34);
        emp.put("salary",3000.00);
        list.add(emp);
        return list;
    }
}
