package queue;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QueueTest {
	private String firstAddedObject = "First";
	private String secondAddedObject = "Second";
	private String something = "Something";

	@Test
	public void test01QueueShouldBeEmptyWhenCreated() {
		assertTrue(new Queue().isEmpty());
	}

	@Test
	public void test02AddElementsToTheQueue() {
		assertFalse(newQueueWithSomething().isEmpty());
	}

	@Test
	public void test03AddedElementsIsAtHead() {
		assertEquals(something, newQueueWithSomething().head());
	}

	@Test
	public void test04TakeRemovesElementsFromTheQueue() {
		Queue queue = newQueueWithSomething();
		queue.take();

		assertTrue(queue.isEmpty());
	}

	@Test
	public void test05TakeReturnsLastAddedObject() {
		assertEquals(something, newQueueWithSomething().take());
	}

	@Test
	public void test06QueueBehavesFIFO() {
		Queue queue = newQueueWithFirstAndSecond();

		assertEquals(queue.take(), firstAddedObject);
		assertEquals(queue.take(), secondAddedObject);
		assertTrue(queue.isEmpty());
	}

	@Test
	public void test07HeadReturnsFirstAddedObject() {
		assertEquals(newQueueWithFirstAndSecond().head(), firstAddedObject);
	}

	@Test
	public void test08HeadDoesNotRemoveObjectFromQueue() {
		Queue queue = newQueueWithSomething();
		assertEquals(1, queue.size());
		queue.head();
		assertEquals(1, queue.size());
	}

	@Test
	public void test09SizeRepresentsObjectInTheQueue() {
		assertEquals(2, newQueueWithFirstAndSecond().size());
	}

	@Test
	public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
		assertTrue(
				assertThrows(Error.class, () -> new Queue().take()).getMessage().equals(EndOfQueueMarker.EmptyQueue));
	}

	@Test
	public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
		Queue queue = newQueueWithSomething();
		queue.take();

		assertTrue(assertThrows(Error.class, () -> queue.take()).getMessage().equals(EndOfQueueMarker.EmptyQueue));
	}

	@Test
	public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
		assertTrue(
				assertThrows(Error.class, () -> new Queue().head()).getMessage().equals(EndOfQueueMarker.EmptyQueue));
	}

	private Queue newQueueWithSomething() {
		return new Queue().add(something);
	}

	private Queue newQueueWithFirstAndSecond() {
		return new Queue().add(firstAddedObject).add(secondAddedObject);
	}
}