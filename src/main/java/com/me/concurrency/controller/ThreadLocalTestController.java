package com.me.concurrency.controller;

import com.me.concurrency.example.threadlocal.RequestHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/8/13.
 */
@Controller
public class ThreadLocalTestController {

    @RequestMapping("/threadlocal/test")
    @ResponseBody
    public Long getThreadId(){
        return RequestHolder.getId();
    }
}
