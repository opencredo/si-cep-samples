package org.opencredo;

import org.opencredo.quote.domain.Quote;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.channel.ChannelInterceptor;
import org.springframework.integration.channel.interceptor.ChannelInterceptorAdapter;
import org.springframework.integration.support.MessageBuilder;


public class RequestTimePublishingInterceptor extends ChannelInterceptorAdapter {

    MessageChannel requestProcessingChannel;

    public RequestTimePublishingInterceptor(MessageChannel requestProcessingChannel) {
        this.requestProcessingChannel = requestProcessingChannel;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        long timeTaken = System.currentTimeMillis() - message.getHeaders().get("REQUEST_TIME", Long.class);
        String providerName = ((Quote)message.getPayload()).getProviderName();
        requestProcessingChannel.send(
                MessageBuilder.withPayload(new RequestProcessingTime(providerName, timeTaken)).build());
        return super.preSend(message, channel);
    }
}
