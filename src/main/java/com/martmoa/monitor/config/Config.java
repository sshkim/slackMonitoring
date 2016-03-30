package com.martmoa.monitor.config;

/**
 * Created by Kim Seung Hwan on 2016-03-24.
 */
public abstract class Config {

    private boolean enabled;
    protected double eachCheckHour;
    protected long lastCheckTime;

    public Config(boolean enabled, double eachCheckHour, long lastCheckTime) {
        this.enabled = enabled;
        this.eachCheckHour = eachCheckHour * 60 * 60 * 1000;
        this.lastCheckTime = lastCheckTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getEachCheckHour() {
        return eachCheckHour;
    }

    public void setEachCheckHour(double eachCheckHour) {
        this.eachCheckHour = eachCheckHour * 60 * 60 * 1000;
    }

    public long getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(long lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }
}
