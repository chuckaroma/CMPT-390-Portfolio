import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class TestQueueEnsureCapacity extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testEnqueue() {
    QueueInterface<Integer> queue = new ArrayQueue<Integer>();
    
    for (int i = 0; i < 25; i++)
      assertTrue(queue.enqueue(new Integer(i)));
  }
  
  public void testDequeue() {
    QueueInterface<Integer> queue = new ArrayQueue<Integer>();
    
    assertNull(queue.dequeue());
    
    for (int i = 0; i < 25; i++)
      assertTrue(queue.enqueue(new Integer(i)));
    
    for (int i = 0; i < 25; i++)
      assertEquals(new Integer(i),queue.dequeue());
    
    assertNull(queue.dequeue());
  }
  
   public void testGetFront() {
    QueueInterface<Integer> queue = new ArrayQueue<Integer>();
    
    assertNull(queue.getFront());
    
    for (int i = 0; i < 25; i++)
      assertTrue(queue.enqueue(new Integer(i)));
    
    for (int i = 0; i < 25; i++) {
      assertEquals(new Integer(i),queue.getFront());
      assertEquals(new Integer(i),queue.dequeue());
    }
    
    assertNull(queue.getFront());
  }
   
   public void testIsEmpty() {
     QueueInterface<Integer> queue = new ArrayQueue<Integer>();
     
     assertTrue(queue.isEmpty());
     
     assertTrue(queue.enqueue(new Integer(-1)));
     
     assertFalse(queue.isEmpty());
     
     assertEquals(new Integer(-1), queue.getFront());
     
     assertFalse(queue.isEmpty());
     
     assertEquals(new Integer(-1), queue.dequeue());
     
     assertTrue(queue.isEmpty());
     
   }
   
   public void testClear() {
     QueueInterface<Integer> queue = new ArrayQueue<Integer>();
    
     for (int i = 0; i < 25; i++)
      assertTrue(queue.enqueue(new Integer(i)));
     
     assertFalse(queue.isEmpty());
     queue.clear();
     assertTrue(queue.isEmpty());
     
     assertNull(queue.dequeue());
   }
   
   public void testEnsure() {
     QueueInterface<Character> queue = new ArrayQueue<Character>();
     
     assertTrue(queue.enqueue('a'));
     assertTrue(queue.enqueue('b'));
     assertTrue(queue.enqueue('c'));
     assertTrue(queue.enqueue('d'));
     assertTrue(queue.enqueue('e'));
     
     assertEquals(new Character('a'),queue.dequeue());
     assertEquals(new Character('b'),queue.dequeue());
     
     assertTrue(queue.enqueue('f'));
     assertTrue(queue.enqueue('g'));
     
     assertTrue(queue.enqueue('h'));
     
     assertEquals(new Character('c'),queue.dequeue());
     assertEquals(new Character('d'),queue.dequeue());
     assertEquals(new Character('e'),queue.dequeue());
     assertEquals(new Character('f'),queue.dequeue());
     assertEquals(new Character('g'),queue.dequeue());
     assertEquals(new Character('h'),queue.dequeue());
   }
}
