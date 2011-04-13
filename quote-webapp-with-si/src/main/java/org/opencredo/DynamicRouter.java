package org.opencredo;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.springframework.integration.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicRouter implements UpdateListener {

    ConcurrentHashMap<String, Double> providerPerformance = new ConcurrentHashMap<String, Double>();

    Double averageTimeThreshold = 2000.00;


    private final Map<String, String> providerToChannelMap;

    public DynamicRouter(Map<String, String> providerToChannelMap) {
        this.providerToChannelMap = providerToChannelMap;
    }

    public void setAverageTimeThreshold(Double averageTimeThreshold) {
        this.averageTimeThreshold = averageTimeThreshold;
    }

    public String routeToProvider(Message message) {
        String providerName = (String) message.getHeaders().get("quoteProvider");
        Double averageProcessingTime = providerPerformance.get(providerName);
        if (averageProcessingTime == null || averageProcessingTime <= averageTimeThreshold) {
            return providerToChannelMap.get(providerName);
        }
        return null;
//        List<String> providersWithinSla = new ArrayList<String>();
//        for(String provider: knownProviders){
//            Double averageProcessingTime = providerPerformance.get(provider);
//            if(averageProcessingTime == null || averageProcessingTime <= averageTimeThreshold){
//                providersWithinSla.add(providerToChannelMap.get(provider));
//            }
//
//        }
//        return providersWithinSla;
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
