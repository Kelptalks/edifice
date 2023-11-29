package MainLoop.TikManager;

import MainLoop.Events.Event;

import java.util.LinkedList;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Tik
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * A tik is a point in time
 * The tik object is responsible
 * for organizing a collection
 * of events.
 */
public class Tik {

    private LinkedList<Event> events = new LinkedList<Event>();

    //add an event to the tik
    public void addEvent(Event event){
        events.add(event);
    }

    //get the events planned on a tik
    public LinkedList<Event> getEvents(){
        return this.events;
    }

    //execute all the events in the tik.
    public void execute(){
        for (Event event : events){
            event.execute();
        }
    }
}
