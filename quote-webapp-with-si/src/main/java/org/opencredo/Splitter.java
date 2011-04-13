package org.opencredo;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Splitter implements UpdateListener {

    private ConcurrentHashMap<String, Double> providerPerformance = new ConcurrentHashMap<String, Double>();

    private Double averageTimeThreshold = 2000.00;


    private final List<String> knownProviders;

    public Splitter(List<String> knownProviders) {
        this.knownProviders = knownProviders;
    }

    public void setAverageTimeThreshold(Double averageTimeThreshold) {
        this.averageTimeThreshold = averageTimeThreshold;
    }

    public List<Message> split(Message message) {
        List<Message> toReturn = new ArrayList<Message>();
        for (String provider : knownProviders) {
            Double averageProcessingTime = providerPerformance.get(provider);
            if (averageProcessingTime == null || averageProcessingTime <= averageTimeThreshold) {
                toReturn.add(MessageBuilder.fromMessage(message).setHeader("quoteProvider", provider).build());
            }
        }
        return toReturn;
    }

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        for (EventBean eventBean : newEvents) {
            Double averageTimeTaken = (Double) eventBean.get("avg(timeTakenForRequest)");
            if (averageTimeTaken == null) {
                break;
            }
            String providerName = (String) eventBean.get("providerName");
            providerPerformance.put(providerName, averageTimeTaken);
            System.out.println(providerPerformance);
        }
    }
}
