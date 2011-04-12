package org.opencredo.provider;


public class QuoteProviderResponseTimeout extends RuntimeException{
    public QuoteProviderResponseTimeout(String s) {
        super(s);
    }

    public QuoteProviderResponseTimeout(String s, Throwable throwable) {
        super(s, throwable);
    }

    public QuoteProviderResponseTimeout(Throwable throwable) {
        super(throwable);
    }
}
