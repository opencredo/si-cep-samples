package org.opencredo;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.springframework.integration.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicRouter {


    private final Map<String, String> providerToChannelMap;

    public DynamicRouter(Map<String, String> providerToChannelMap) {
        this.providerToChannelMap = providerToChannelMap;
    }


    public String routeToProvider(Message message) {
        String providerName = (String) message.getHeaders().get("quoteProvider");
        return providerToChannelMap.get(providerName);
    }


}
