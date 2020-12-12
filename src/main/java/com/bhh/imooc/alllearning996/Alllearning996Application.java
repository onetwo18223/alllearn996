package com.bhh.imooc.alllearning996;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//可以扫描到filter
@ServletComponentScan
@SpringBootApplication
public class Alllearning996Application {

    public static void main(String[] args) {
        SpringApplication.run(Alllearning996Application.class, args);
    }

}
