package queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class QueueWithElements implements QueueInterface {
    private LinkedList<Object> queue = new LinkedList<>();

    public QueueWithElements(Object cargo) {
        this.queue = new LinkedList<>();
        this.queue.add(cargo);
    }
    
    @Override
    public QueueInterface add(Object cargo) {
        queue.add(cargo);
        return this;
    }

    @Override
    public Object take() {
    	try {
            return this.queue.removeFirst();
        } catch (NoSuchElementException e) {
            return new EmptyQueue().take();
        }
    }

    @Override
    public Object head() {
        return queue.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }
}