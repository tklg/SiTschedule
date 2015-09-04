//package net.tkluge.schedule;
//
//import android.os.Environment;
//
//import net.fortuna.ical4j.data.CalendarBuilder;
//import net.fortuna.ical4j.model.Calendar;
//import net.fortuna.ical4j.model.Component;
//import net.fortuna.ical4j.model.Property;
//
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * Created by kluget on 9/2/2015.
// */
//public class icsParser {
//    ArrayList<ArrayList> schedule;
//    ArrayList<EventItem> event;
//    public icsParser() {
//        schedule = new ArrayList<ArrayList>();
//        event = new ArrayList<EventItem>();
//    }
//    public void addDetail(String name, String value) {
//        event.add(new EventItem(name, value));
//    }
//    public void addComponent(ArrayList event) {
//        schedule.add(event);
//    }
//    public void clearSchedForNextDay() {
//        event.clear();
//    }
//    public ArrayList readCal() {
//        //icsParser cal = new icsParser();
//        FileInputStream fin = null;
//        try {
//            fin = new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/calendar.ics");
//            CalendarBuilder builder = new CalendarBuilder();
//
//            Calendar calendar = null;
//            try {
//                calendar = builder.build(fin);
//                for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
//                    Component component = (Component) i.next();
//                    F.nl("Component [" + component.getName() + "]");
//
//                    for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
//                        Property property = (Property) j.next();
//                        F.nl("Property [" + property.getName() + ", " + property.getValue() + "]");
//                        addDetail(property.getName(), property.getValue());
//                    }
//
//                    addComponent(event);
//                    //clearSchedForNextDay();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return schedule;
//    }
//}
