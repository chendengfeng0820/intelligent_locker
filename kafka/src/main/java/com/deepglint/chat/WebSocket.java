package com.deepglint.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName WebSocket
 * @Description TODO
 * @author: cdf
 * @Date: 2021-02-18 23:15
 **/

@ServerEndpoint("/websocket")
@Component
@Slf4j
public class WebSocket {
	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	//concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

	/**
	 * 建立连接成功
	 *
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
		log.info("【websocket消息】 有新的连接，总数{}", webSocketSet.size());
	}

	/**
	 * 连接关闭
	 */
	@OnClose
	public void onClose() {
		this.session = session;
		webSocketSet.remove(this);
		log.info("【websocket消息】 连接断开，总数{}", webSocketSet.size());
	}

	/**
	 * 接收客户端消息
	 *
	 * @param message
	 */
	@OnMessage
	public void onMessage(String message) {
		log.info("【websocket消息】 收到消息：{}", message);
	}

	/**
	 * 发送消息
	 *
	 * @param message
	 */
	public void sendMessage(String message) {
		log.info("【websocket消息】 发送消息：{}", message);
		for (WebSocket webSocket : webSocketSet) {
			try {
				webSocket.session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
