package com.bhi.performance.controllers;

import com.bhi.performance.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index()
    {

        return "index";
    }

    @GetMapping("/task")
    public String task() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("cmd /c cd");
        System.out.println("Luis no prueba bien: ");
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));

        process.waitFor(2, TimeUnit.SECONDS);

        while (output.ready()) {
            System.out.println(" " + output.readLine());
        }

        return "index";
    }

    @GetMapping("/create1")
    public String create1() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(
                new String[]{"cmd","/c","jmeter -n -t EverydayLoadTesting.jmx -l testresults1.jtl"},
                null,
                new File("D:\\Personal\\apache-jmeter-5.4.1\\bin")
        );
        System.out.println("Create Process 1");
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));

        process.waitFor(10, TimeUnit.SECONDS);
        Long processId = Utils.getProcessID(process);
        System.out.println("PID Current Process: " + processId);

        while (output.ready()) {
            System.out.println(" " + output.readLine());
        }
        Utils.processId = processId;
        return "index";
    }

    @PostMapping("/create2")
    public String create2() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(
                new String[]{"cmd","/c","jmeter -n -t EverydayLoadTesting.jmx -l testresults1.jtl -j log1.log"},
                null,
                new File("D:\\Personal\\apache-jmeter-5.4.1\\bin")
        );
        System.out.println("Create Process 2");
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));

        process.waitFor(10, TimeUnit.SECONDS);
        Long processId = Utils.getProcessID(process);
        System.out.println("PID Current Process: " + processId);

        while (output.ready()) {
            System.out.println(" " + output.readLine());
        }
        Utils.processId = processId;
        return "stop";
    }

    @PostMapping("/pointrelease")
    public String pointrelease(@RequestParam(name = "environment") String environment) throws IOException, InterruptedException {
        System.out.println("Create Process " + environment);
        String cmd = "";
        if(environment == "1"){
            cmd = "jmeter -n -t EverydayLoadTestingSprint.jmx -l testresultssprint.jtl -j logsprint.log";
        }else if(environment == "2"){
            cmd = "jmeter -n -t EverydayLoadTestingPointRelease.jmx -l testresultspointrelease.jtl -j logpointrelease.log";
        }else if(environment == "3"){
            cmd = "jmeter -n -t EverydayLoadTestingStrategic1.jmx -l testresultsST1.jtl -j testresultsST1.log";
        }else if(environment == "4"){
            cmd = "jmeter -n -t EverydayLoadTestingStrategic2.jmx -l testresultsST2.jtl -j testresultsST1.log";
        }else if(environment == "5"){
            cmd = "jmeter -n -t EverydayLoadTestingStrategic3.jmx -l testresultsST3.jtl -j testresultsST1.log";
        }else if(environment == "6"){
            cmd = "jmeter -n -t EverydayLoadTestingStrategic4.jmx -l testresultsST4.jtl -j testresultsST1.log";
        }
        System.out.println(cmd);
        Process process = Runtime.getRuntime().exec(
                new String[]{"cmd","/c",cmd},
                null,
                new File("D:\\Personal\\apache-jmeter-5.4.1\\bin")
        );
        System.out.println("Create Process " + environment);
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));

        process.waitFor(10, TimeUnit.SECONDS);
        Long processId = Utils.getProcessID(process);
        System.out.println("PID Current Process: " + processId);

        while (output.ready()) {
            System.out.println(" " + output.readLine());
        }
        Utils.processId = processId;
        return "stop";
    }

    @PostMapping("/strategic1")
    public String strategic1() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(
                new String[]{"cmd","/c","jmeter -n -t EverydayLoadTestingPointReleaseStrategic1.jmx -l testresults1.jtl -j log1.log"},
                null,
                new File("D:\\Personal\\apache-jmeter-5.4.1\\bin")
        );
        System.out.println("Create Process 2");
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));

        process.waitFor(10, TimeUnit.SECONDS);
        Long processId = Utils.getProcessID(process);
        System.out.println("PID Current Process: " + processId);

        while (output.ready()) {
            System.out.println(" " + output.readLine());
        }
        Utils.processId = processId;
        return "stop";
    }

    @PostMapping("/kill")
    public String kill() throws IOException, InterruptedException {
        Long processId = Utils.processId;
        Process process = Runtime.getRuntime().exec(
                new String[]{"cmd","/c","taskkill /F /T /PID " + processId},
                null,
                new File("D:\\Personal\\apache-jmeter-5.4.1\\bin")
        );
        System.out.println("Kill Process");
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));

        process.waitFor(2, TimeUnit.SECONDS);

        System.out.println("Id Process was killed: " + processId);

        while (output.ready()) {
            System.out.println(" " + output.readLine());
        }

        return "stop";
    }
}
