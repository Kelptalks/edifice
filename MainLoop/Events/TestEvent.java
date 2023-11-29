package MainLoop.Events;

public class TestEvent implements Event{
    @Override
    public boolean execute() {
        System.out.println("Event executed");
        return true;
    }
}
