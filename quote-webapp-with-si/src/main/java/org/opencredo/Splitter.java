package org.opencredo;

import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

    private final List<String> knownProviders;

    public Splitter(List<String> knownProviders) {
        this.knownProviders = knownProviders;
    }

    public List<Message> split(Message message){
        List<Message> toReturn = new ArrayList<Message>();
        for(String provider: knownProviders){
            toReturn.add(MessageBuilder.fromMessage(message).setHeader("quoteProvider", provider).build());
        }
        return toReturn;
    }
}
