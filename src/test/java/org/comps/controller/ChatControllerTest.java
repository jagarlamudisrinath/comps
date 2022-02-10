package org.comps.controller;

import org.comps.model.ChatMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:test-data/009-group-discussions.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.FAIL_ON_ERROR))
public class ChatControllerTest {
    @LocalServerPort
    private int port;
    private SockJsClient sockJsClient;
    private WebSocketStompClient stompClient;
    private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    @BeforeEach
    public void setup() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        this.sockJsClient = new SockJsClient(transports);

        this.stompClient = new WebSocketStompClient(sockJsClient);
        this.stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    @Test
    public void chat() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<Throwable> failure = new AtomicReference<>();

        StompSessionHandler handler = new TestSessionHandler(failure) {
            @Override
            public void afterConnected(final StompSession session, StompHeaders connectedHeaders) {
                session.subscribe("/topic/9-CS-9-AS-g1", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return ChatMessage.class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        ChatMessage chatMessage = (ChatMessage) payload;
                        try {
                            Assertions.assertEquals("User joined", chatMessage.getContent());
                        } catch (Throwable t) {
                            failure.set(t);
                        } finally {
                            session.disconnect();
                            latch.countDown();
                        }
                    }
                });
                try {
                    ChatMessage message = new ChatMessage();
                    message.setChatId("9-CS-9-AS-g1");
                    message.setContent("Testing Websocket connection");
                    message.setSender("9srinath");
                    session.send("/app/chat", message);
                    latch.countDown();
                } catch (Throwable t) {
                    failure.set(t);
                    latch.countDown();
                }
            }
        };

        this.headers.add("chatId", "9-CS-9-AS-g1");
        this.headers.add("userId", "9srinath");
        this.stompClient.connect("ws://localhost:{port}/ws", this.headers, handler, this.port);

        if (latch.await(10, TimeUnit.SECONDS)) {
            if (failure.get() != null) {
                throw new AssertionError("", failure.get());
            }
        } else {
            Assertions.fail("Message not received");
        }
    }

    private class TestSessionHandler extends StompSessionHandlerAdapter {
        private final AtomicReference<Throwable> failure;

        public TestSessionHandler(AtomicReference<Throwable> failure) {
            this.failure = failure;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            this.failure.set(new Exception(headers.toString()));
        }

        @Override
        public void handleException(StompSession s, StompCommand c, StompHeaders h, byte[] p, Throwable ex) {
            this.failure.set(ex);
        }

        @Override
        public void handleTransportError(StompSession session, Throwable ex) {
            this.failure.set(ex);
        }
    }
}
