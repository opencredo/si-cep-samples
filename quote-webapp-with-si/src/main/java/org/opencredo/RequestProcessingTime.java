package org.opencredo;

public class RequestProcessingTime {

    private String providerName;

    private long timeTakenForRequest;

    public RequestProcessingTime(String providerName, long timeTakenForRequest) {
        this.providerName = providerName;
        this.timeTakenForRequest = timeTakenForRequest;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public long getTimeTakenForRequest() {
        return timeTakenForRequest;
    }

    public void setTimeTakenForRequest(long timeTakenForRequest) {
        this.timeTakenForRequest = timeTakenForRequest;
    }
}
