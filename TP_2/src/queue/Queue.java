package queue;
import java.util.LinkedList;

public class Queue {
	public LinkedList<Object> queue = new LinkedList<>();
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public Queue add( Object  cargo ) {
		queue.add(cargo);
		return this;
	}

    public Object take() {
        if (queue.isEmpty()) {
            throw new Error("Queue is empty");
        }
        return queue.removeFirst();
    }

    public Object head() {
        if (queue.isEmpty()) {
            throw new Error("Queue is empty");
        }
        return queue.getFirst();
    }

	public int size() {
		return queue.size();
	}

}
