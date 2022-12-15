package com.Ezenweb.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component // 해당 클래스 [ 서버소켓 ] 을 빈[ 스프링 메모리 ]에 등록
public class ServerSockeHandler extends TextWebSocketHandler {

    private  static List<WebSocketSession> list = new Vector<>(); // 0. 접속자 명단리스트

    // 1. 접속

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("접속 : "+ session);
        list.add(session); // 리스트명단에 접속된 세션 추가

    }

    // 2. 종료

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("퇴장 : "+ session);
        list.remove(session);
    }

    // 3. 메시지 받았을때


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for( WebSocketSession s : list ) { // 반복문을 이용한 접속자명단 출력
            s.sendMessage(message); // 서버가 받은 메시지를 모든 접속자에게 전달
        }
    }
}

//   for(int i = 0 ; i<list.size(); i++){}
//list.forEach( (s) -> {} );  // js: =>  ,  java ->