package com.example.souqcom;
public class salary {

    String buy;

    public salary( String buy) {

        this.buy = buy;
    }

    @Override
    public String toString() {
        return "salary{" +
                "buy='" + buy + '\'' +
                '}';
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }
}
