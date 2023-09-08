package queue;

public class EmptyQueue implements QueueInterface {
    
	@Override
    public QueueInterface add(Object cargo) {
        return new QueueWithElements(cargo);
    }

    @Override
    public Object take() {
        throw new Error("Queue is empty");
    }

    @Override
    public Object head() {
        throw new Error("Queue is empty");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int size() {
        return 0;
    }
}