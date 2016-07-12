package com.dealmacha.util;
/*package com.chitfund.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.enableSimpleBroker("/orders", "/offers");

        config.setApplicationDestinationPrefixes("/app");

    }

     @Bean
    public WebSocketHandler myHandler() {
        return null;
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
       registry.addEndpoint("/arthvedi").setAllowedOrigins("*").withSockJS();

    }

    public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/myHandler").se;
    }

}
*/