package queue;

public class Queue {
    private QueueInterface currentQueue = new EmptyQueue();
    
    public Queue add(Object cargo) {
    	currentQueue = currentQueue.add(cargo);
        return this;
    }

    public Object take() {
        return currentQueue.take();
    }

    public Object head() {
        return currentQueue.head();
    }

    public boolean isEmpty() {
        return currentQueue.isEmpty();
    }

    public int size() {
        return currentQueue.size();
    }
}