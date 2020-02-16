package com.corgi.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    Controller 선언시 @RefreshScope도 같이 선언해줘야 된다. 일반적인 환경 설정은 서버 기동 시 한번 로드되고 캐싱되어 변하지가 않지만,
    Config 서버를 통해 외부에서 환경설정이 변경되었을 때 해당 설정이 다시 로드되어야 하는 부분은 코드 상에 어노테이션으로 표시를 해주어야 한다.
    이렇게 설정하고 Client 주소로 ex. localhost:{port}/actuator/refresh POST(Request body는 빈 값으로) 요청을 전송하면,
    동적으로 설정 정보를 변경하여 읽어들일 수 있다.
*/
@RestController
@RefreshScope
public class TestController {

    @Value("${hello.springcloud.configserver}")
    private String msg;

    @GetMapping("/test")
    public String test() {
        System.out.println("read property => " + msg);

        return msg;
    }
}
