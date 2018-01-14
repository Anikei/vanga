import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void testToString() {
        Task task = new Task("aaa", 3, 4.5, 6);
        assertTrue(task.toString().equals("Task{name=aaa, 3.0/4.5/6.0}"));
    }

}