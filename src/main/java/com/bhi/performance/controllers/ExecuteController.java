package com.bhi.performance.controllers;

import com.bhi.performance.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/execute")
public class ExecuteController {

    @GetMapping
    public String index() {
        return "execute";
    }

    @GetMapping("/stop")
    public String stop() throws IOException, InterruptedException {
        if (Utils.sprintProcessId  > -1) {
            this.kill(Utils.sprintProcessId);
            Utils.sprintProcessId = -1;
        }

        if (Utils.pointReleaseProcessId  > -2) {
            this.kill(Utils.pointReleaseProcessId);
            Utils.pointReleaseProcessId = -2;
        }

        if (Utils.strategic1ProcessId  > -3) {
            this.kill(Utils.strategic1ProcessId);
            Utils.strategic1ProcessId = -3;
        }

        if (Utils.strategic2ProcessId > -4) {
            this.kill(Utils.strategic2ProcessId);
            Utils.strategic2ProcessId = -4;
        }

        if (Utils.strategic3ProcessId > -5) {
            this.kill(Utils.strategic3ProcessId);
            Utils.strategic3ProcessId = -5;
        }

        if (Utils.strategic4ProcessId > -6) {
            this.kill(Utils.strategic4ProcessId);
            Utils.strategic4ProcessId = -6;
        }
        return "execute";
    }

    public String kill(Long processId) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(
                new String[]{"cmd","/c","taskkill /F /T /PID " + processId},
                null,
                new File("C:\\apache-jmeter-5.4.1\\bin")
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

    private Long execute(String environment) throws IOException, InterruptedException {
        System.out.println("Create Process " + environment);
        String cmd = "";
        if (environment == "1") {
            cmd = "jmeter -n -t EverydayLoadTestingSprint.jmx -l testresultssprint.jtl -j logsprint.log";
        } else if (environment == "2") {
            cmd = "jmeter -n -t EverydayLoadTestingPointRelease.jmx -l testresultspointrelease.jtl -j logpointrelease.log";
        } else if (environment == "3") {
            cmd = "jmeter -n -t EverydayLoadTestingStrategic1.jmx -l testresultsST1.jtl -j testresultsST1.log";
        } else if (environment == "4") {
            cmd = "jmeter -n -t EverydayLoadTestingStrategic2.jmx -l testresultsST2.jtl -j testresultsST1.log";
        } else if (environment == "5") {
            cmd = "jmeter -n -t EverydayLoadTestingStrategic3.jmx -l testresultsST3.jtl -j testresultsST1.log";
        } else if (environment == "6") {
            cmd = "jmeter -n -t EverydayLoadTestingStrategic4.jmx -l testresultsST4.jtl -j testresultsST1.log";
        }
        System.out.println(cmd);
        Process process = Runtime.getRuntime().exec(
                new String[]{"cmd", "/c", cmd},
                null,
                new File("C:\\apache-jmeter-5.4.1\\bin")
        );
        System.out.println("Create Process " + environment);
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));

        process.waitFor(10, TimeUnit.SECONDS);
        Long processId = Utils.getProcessID(process);
        System.out.println("PID Current Process: " + processId);

        while (output.ready()) {
            System.out.println(" " + output.readLine());
        }
        //Utils.processId = processId;
        return processId;
    }

    @PostMapping("/load")
    public String pointrelease(@RequestParam(name = "sprint", required = false) String sprint, @RequestParam(name = "pointrelease", required = false) String pointrelease,
                               @RequestParam(name = "strategic1", required = false) String strategic1, @RequestParam(name = "strategic2", required = false) String strategic2,
                               @RequestParam(name = "strategic3", required = false) String strategic3, @RequestParam(name = "strategic4", required = false) String strategic4, ModelMap model
    )  throws IOException, InterruptedException {
//        System.out.println("Create Process " + environment);
//        String cmd = "";
//        if(environment == "1"){
//            cmd = "jmeter -n -t EverydayLoadTestingSprint.jmx -l testresultssprint.jtl -j logsprint.log";
//        }else if(environment == "2"){
//            cmd = "jmeter -n -t EverydayLoadTestingPointRelease.jmx -l testresultspointrelease.jtl -j logpointrelease.log";
//        }else if(environment == "3"){
//            cmd = "jmeter -n -t EverydayLoadTestingStrategic1.jmx -l testresultsST1.jtl -j testresultsST1.log";
//        }else if(environment == "4"){
//            cmd = "jmeter -n -t EverydayLoadTestingStrategic2.jmx -l testresultsST2.jtl -j testresultsST1.log";
//        }else if(environment == "5"){
//            cmd = "jmeter -n -t EverydayLoadTestingStrategic3.jmx -l testresultsST3.jtl -j testresultsST1.log";
//        }else if(environment == "6"){
//            cmd = "jmeter -n -t EverydayLoadTestingStrategic4.jmx -l testresultsST4.jtl -j testresultsST1.log";
//        }
//        System.out.println(cmd);
//        Process process = Runtime.getRuntime().exec(
//                new String[]{"cmd","/c",cmd},
//                null,
//                new File("C:\\apache-jmeter-5.4.1\\bin")
//        );
//        System.out.println("Create Process " + environment);
//        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//        process.waitFor(10, TimeUnit.SECONDS);
//        Long processId = Utils.getProcessID(process);
//        System.out.println("PID Current Process: " + processId);
//
//        while (output.ready()) {
//            System.out.println(" " + output.readLine());
//        }
        if ("sprint".equals(sprint)) {
            Utils.sprintProcessId = this.execute("1");
            model.addAttribute("sprint", "1");
        } else {
            model.addAttribute("sprint", "0");
        }

        if ("pointrelease".equals(pointrelease)) {
            Utils.pointReleaseProcessId = this.execute("2");
            model.addAttribute("pointRelease", "1");
        } else {
            model.addAttribute("pointRelease", "0");
        }

        if ("strategic1".equals(strategic1)) {
            Utils.strategic1ProcessId = this.execute("3");
            model.addAttribute("strategic1", "1");
        } else {
            model.addAttribute("strategic1", "0");
        }

        if ("strategic2".equals(strategic2)) {
            Utils.strategic2ProcessId = this.execute("4");
            model.addAttribute("strategic2", "1");
        } else {
            model.addAttribute("strategic2", "0");
        }

        if ("strategic3".equals(strategic3)) {
            Utils.strategic3ProcessId = this.execute("5");
            model.addAttribute("strategic3", "1");
        } else {
            model.addAttribute("strategic3", "0");
        }

        if ("strategic4".equals(strategic4)) {
            Utils.strategic4ProcessId = this.execute("6");
            model.addAttribute("strategic4", "1");
        } else {
            model.addAttribute("strategic4", "0");
        }

        System.out.println(sprint);
        System.out.println(pointrelease);
        System.out.println(strategic1);
        System.out.println(strategic2);
        System.out.println(strategic3);
        System.out.println(strategic4);
//        Utils.processId;
        return "stop";
    }




}
