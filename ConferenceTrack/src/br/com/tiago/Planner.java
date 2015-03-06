package br.com.tiago;

import br.com.tiago.model.Event;
import br.com.tiago.utils.DateTimeUtils;
import br.com.tiago.utils.SchedulerLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago_de_Oliveira on 9/19/2014.
 */
public class Planner {

    private List<Event> events;
    private static final Integer TRACKS = 2;
    private static final String START_TIME = "09:00";
    private final Event lunchEvent = new Event("Lunch", "12:00", 60, 0);
    private final Event networkEvent = new Event("Network Event", "16:00", 60, 60);

    // These is a NP problem, so we could use some more advanced techniques to solve that on a smarter way like a Directed Acyclic Graph or a simple Permutation algorithm
    // It's not possible to implement on these scenario due a time constraint.
    public void execute() {
        this.events = SchedulerLoader.loadSchedule(new File("schedule.csv"));
        List<Event> finalCalendar = new ArrayList<Event>();
        Boolean addLunch = false;
        Long currentTime = DateTimeUtils.hoursInMillis(START_TIME);
        Integer track = 1;
        for (int j = 0; j < this.events.size(); j++) {
            Event currentEvent = this.events.get(j);
            currentEvent.setTrack(track);
            currentEvent.setStartTime(DateTimeUtils.millisInHours(currentTime));
            currentTime += DateTimeUtils.minutesInMillis(currentEvent.getDuration());
            finalCalendar.add(currentEvent);
            Event nextEvent = null;
            if (j < this.events.size() - 1) {
                nextEvent = this.events.get(j + 1);
            }
            if (nextEvent != null) {
                if ((!addLunch) && (currentTime + DateTimeUtils.minutesInMillis(nextEvent.getDuration()) > (DateTimeUtils.sumTime(lunchEvent.getStartTime(), lunchEvent.getAllowedDelay())))) {
                    Event lEvent = this.lunchEvent.clone();
                    lEvent.setTrack(track);
                    finalCalendar.add(lEvent);
                    currentTime += DateTimeUtils.minutesInMillis(this.lunchEvent.getDuration());
                    addLunch = true;
                }
            }
            if ((nextEvent == null) || (currentTime + DateTimeUtils.minutesInMillis(nextEvent.getDuration()) > DateTimeUtils.sumTime(networkEvent.getStartTime(), networkEvent.getAllowedDelay()))) {
                Event nEvent = this.networkEvent.clone();
                nEvent.setStartTime(DateTimeUtils.millisInHours(currentTime));
                nEvent.setTrack(track);
                finalCalendar.add(nEvent);
                currentTime = DateTimeUtils.hoursInMillis(START_TIME);
                addLunch = false;
                track++;
            }
        }

        track = 0;
        for (Event event : finalCalendar) {
            if (track != event.getTrack()) {
                System.out.println("\n ----- Track " + event.getTrack());
                track = event.getTrack();
            }
            System.out.println(event.getStartTime() + " - " + event.getDuration() + " - " + event.getName());
        }
    }

    public static void main(String[] args) {
        new Planner().execute();
    }
}
