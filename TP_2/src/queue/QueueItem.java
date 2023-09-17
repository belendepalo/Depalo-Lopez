package queue;

public class QueueItem extends QueueElementBase{
	public Object element;
	public QueueElementBase nextInQueue;

    public QueueItem(Object element, QueueElementBase nextElement) {
        this.element = element;
        this.nextInQueue = nextElement;
    }

	@Override
	public Object head() { 
		return element;
	}

	@Override
	public Object take() { 
		return element;
	}
	
	@Override
	public QueueElementBase add(Object newItem) {
		nextInQueue = nextInQueue.add(newItem); 
	    return this;
	}

	@Override
	public int size() {
	    return 1 + nextInQueue.size();
	}

}