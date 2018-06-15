package com.controller.socketjs;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {

    /**
     * 当浏览器向服务端发送请求时，通过@MessageMapping映射/welcome这个地址，类似于@RequestMapping
     * 当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
     * @param msg
     * @return
     */
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public String say(String msg){
        return msg;
    }
}
