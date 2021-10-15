package com.bhi.performance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/execute")
public class ExecuteController {

    @GetMapping
    public String index()
    {
        return "execute";
    }

    @GetMapping("/stop")
    public String stop()
    {
        return "execute";
    }

    @PostMapping("/load")
    public String load(@RequestParam(name = "sprint") String sprint, @RequestParam(name = "pointrelease") String pointrelease) throws IOException, InterruptedException{
        System.out.println("el pepe " + sprint + "xd " + pointrelease);
        return "execute";
    }



}
