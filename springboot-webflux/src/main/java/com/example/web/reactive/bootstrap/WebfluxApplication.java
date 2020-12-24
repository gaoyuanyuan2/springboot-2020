package com.example.web.reactive.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
@RestController
public class WebfluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
//        return RouterFunctions.route(request -> {
//                    URI uri = request.uri();
//                    return "/hello-world".equals(uri.getPath());
//                }, // 判断请求是否匹配
//                request -> {
//                    Mono<ServerResponse> mono =
//                            ServerResponse.status(HttpStatus.OK)
//                            .body(Mono.just("Hello,world!"), String.class);
//                    return mono;
//                }//绑定实现
//        );
        return route(GET("/hello-world"), this::helloWorld);
    }

    public Mono<ServerResponse> helloWorld(ServerRequest serverRequest) {
        println("Hello, World");
        return ServerResponse.status(HttpStatus.OK)
                .body(Mono.just("Hello, World"), String.class);
    }

    @GetMapping("/mvc")
    public String mve() {
        println("Mvc");
        return "MVC";
    }

    @GetMapping("/mono")
    public Mono<String> mono() {
        println("Mono");
        return Mono.just("Mono");
    }
    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println(" [线程:" + threadName + "]" + object);
    }
}