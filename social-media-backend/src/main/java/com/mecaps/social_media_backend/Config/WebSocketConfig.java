package com.mecaps.social_media_backend.Config;

import com.mecaps.social_media_backend.Utils.UserHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
private final UserHandshakeInterceptor userHandShakeInterceptor;

    public WebSocketConfig(UserHandshakeInterceptor userHandShakeInterceptor) {
        this.userHandShakeInterceptor = userHandShakeInterceptor;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
registry.addEndpoint("/ws")
        //.addInterceptors(userHandShakeInterceptor)
        .setAllowedOriginPatterns("*");
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
registry.setApplicationDestinationPrefixes("/app");
registry.enableSimpleBroker("/queue");
  //  registry.setUserDestinationPrefix(("/user"));
    }

//    @Override
//    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
//        resolver.setDefaultMimeType(APPLICATION_JSON);
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setObjectMapper(new ObjectMapper());
//        converter.setContentTypeResolver(resolver);
//        messageConverters.add(converter);
//        return false;}

}
