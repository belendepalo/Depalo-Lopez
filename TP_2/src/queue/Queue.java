package queue;

public class Queue {
    private QueueElementBase headOfQueue;

    public Queue() {
        headOfQueue = new EndOfQueueMarker();
    }

	public boolean isEmpty() {
		return headOfQueue instanceof EndOfQueueMarker;
	}

	public Queue add(Object newItem) {
        headOfQueue = headOfQueue.add(newItem);
        return this;
    }

	public Object take() {
	    Object removedItem = headOfQueue.take();
	    headOfQueue = ((QueueItem) headOfQueue).nextInQueue;
	    return removedItem;
	}


    public Object head() {
        return headOfQueue.head();
    }

	public int size() {
		return headOfQueue.size();
	}

}