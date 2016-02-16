import org.junit.*;
import static org.junit.Assert.*;

public class ToDoTest {

  @Test
  public void ToDo_instantiatesCorrectly_true() {
    ToDo myToDo = new ToDo("Learn to code");
    assertEquals(true, myToDo instanceof ToDo);
  }

  @Test
  public void task_instantiatesWithDescription_true() {
    ToDo myToDo = new ToDo("Learn to code");
    assertEquals("Learn to code", myToDo.getDescription());
  }

}
