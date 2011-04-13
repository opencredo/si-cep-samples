package org.opencredo.flow;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.junit.Test;
import org.opencredo.RequestProcessingTime;
import org.opencredo.esper.EsperStatement;
import org.opencredo.esper.EsperTemplate;
import org.springframework.core.io.ClassPathResource;

public class EsperExpressionTest {

    @Test
    public void testEsperExpression() throws Exception {
        EsperTemplate template = new EsperTemplate();
        template.setConfiguration(new ClassPathResource("classpath:esper-configuration.xml"));
        template.initialize();
        EsperStatement esperStatement =  new EsperStatement("select avg(timeTakenForRequest), providerName from org.opencredo.RequestProcessingTime.win:time(1 second) group by providerName");
        esperStatement.addListener(new Listener());
        template.addStatement(esperStatement);
        template.sendEvent(new RequestProcessingTime("test one", 100));
        template.sendEvent(new RequestProcessingTime("test two", 100));
        template.sendEvent(new RequestProcessingTime("test one", 200));
        Thread.sleep(10000);

    }


    private static class Listener implements UpdateListener {

        @Override
        public void update(EventBean[] newEvents, EventBean[] oldEvents) {
            for(EventBean eventBean: newEvents){
                Double averageTimeTaken = (Double)eventBean.get("avg(timeTakenForRequest)");
                if(averageTimeTaken == null){
                    break;
                }
                String providerName = (String) eventBean.get("providerName");

                System.out.println(providerName + " " + averageTimeTaken);
            }
        }
    }

}
