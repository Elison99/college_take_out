package org.example.college;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan //servlet组件扫描 会自动扫描项目中(当前包及其子包下)的@WebServlet , @WebFilter , @WebListener 注解, 自动注册Servlet的相关组件 ;
@EnableTransactionManagement //开启对事务管理的支持
public class CollegeApplication{
    public static void main(String[] args) {
        SpringApplication.run(CollegeApplication.class);
        log.info("项目启动成功....");
    }
}


