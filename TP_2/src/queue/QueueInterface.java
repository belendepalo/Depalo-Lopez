package queue;

public interface QueueInterface {
    QueueInterface add(Object cargo);
    Object take();
    Object head();
    boolean isEmpty();
    int size();
}