package br.com.tiago.utils;

import br.com.tiago.model.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiago_de_Oliveira on 9/19/2014.
 */
public class SchedulerLoader {

    private static final String CSV_SEPARATOR = ",";

    public static List<Event> loadSchedule(File csvFile){
        List<Event> result = new ArrayList<Event>();
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while((line = br.readLine()) != null) {
                String[] rawEvent = line.split(CSV_SEPARATOR);
                Event event = new Event();
                event.setName(rawEvent[0]);
                event.setDuration(Integer.parseInt(rawEvent[1]));
                result.add(event);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + csvFile.getAbsolutePath() + " not founded");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
