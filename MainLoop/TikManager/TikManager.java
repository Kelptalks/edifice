package MainLoop.TikManager;
import MainLoop.Events.Event;
import java.util.HashMap;

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  TikManager
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Manage tiks
 */
public class TikManager {

    private HashMap<Integer, Tik> tikMap = new HashMap<Integer, Tik>();
    private int currentTikTime = 0;

    /* Execute all the commands on the next tik
     */
    public void executeNextTik(){
        //get the tik an execute and remove if exists.
        Tik tikToExecute = tikMap.get(currentTikTime);
        if (tikToExecute != null){
            tikToExecute.execute();
            tikMap.remove(currentTikTime);
        }
        //update the time
        this.currentTikTime++;
    }

    /* Add an event to a tik if it exists. If
     * Event does not create and insert in order
     */
    public void addEvent(int timeInFuture, Event event){
        Tik tikAtTime = tikMap.get(timeInFuture);
        //If tik does not exist at that time, create and add to map.
        if (tikAtTime == null){
            tikAtTime = new Tik();
            tikMap.put(timeInFuture, tikAtTime);
        }
        //Add the event to the tik
        tikAtTime.addEvent(event);
    }

    public long getCurrentTikTime(){
        return currentTikTime;
    }
}
