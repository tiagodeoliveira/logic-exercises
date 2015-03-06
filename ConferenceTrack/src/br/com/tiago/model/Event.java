package br.com.tiago.model;

/**
 * Created by Tiago_de_Oliveira on 9/19/2014.
 */
public class Event implements Cloneable {
    private String name;
    private String startTime;
    private Integer duration;
    private Integer allowedDelay;
    private Integer track;

    public Event() {

    }

    public Event clone() {
        Event result = this;
        try {
            result = (Event) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Event(String name, String startTime, Integer duration, Integer allowedDelay) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
        this.allowedDelay = allowedDelay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAllowedDelay() {
        return allowedDelay;
    }

    public void setAllowedDelay(Integer allowedDelay) {
        this.allowedDelay = allowedDelay;
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }
}
