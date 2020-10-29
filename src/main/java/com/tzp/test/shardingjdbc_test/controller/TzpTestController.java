package com.tzp.test.shardingjdbc_test.controller;

import com.tzp.test.shardingjdbc_test.service.TzpTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Set;

@Controller
@RequestMapping("/tzpTest")
@Slf4j
public class TzpTestController {
    @Resource
    private TzpTestService tzpTestService;

    @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insert() {
        try {
            tzpTestService.addTzpTest();
            return "success";
        } catch (Exception e) {
            log.error("insert异常",e);
            return e.getMessage();
        }

    }
}
