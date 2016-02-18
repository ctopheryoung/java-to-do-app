import java.time.LocalDateTime;

import org.junit.*;
import static org.junit.Assert.*;

public class ToDoTest {

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void toDo_instantiatesCorrectly_true() {
    ToDo myToDo = new ToDo("Learn to code");
    assertEquals(true, myToDo instanceof ToDo);
  }

  @Test
  public void toDo_instantiatesWithDescription_true() {
    ToDo myToDo = new ToDo("Learn to code");
    assertEquals("Learn to code", myToDo.getDescription());
  }

  @Test
  public void isCompleted_isFalseAfterInstantiaon_false() {
    ToDo myToDo = new ToDo("Learn to code");
    assertEquals(false, myToDo.isCompleted());
  }

  @Test
  public void getCreateAt_instantiatesWithCurrentTime_today() {
    ToDo myToDo = new ToDo("Learn to code");
    assertEquals(LocalDateTime.now().getDayOfWeek(), myToDo.getCreatedAt().getDayOfWeek());
  }

  @Test
  public void all_returnsAllInstancesOfToDo_true() {
    ToDo firstToDo = new ToDo("Learn to code");
    ToDo secondToDo = new ToDo("Walk the dog");
    assertTrue(ToDo.all().contains(firstToDo));
    assertTrue(ToDo.all().contains(secondToDo));
  }

  @Test
  public void newId_todosInstantiateWithAnID_true() {
    ToDo myToDo = new ToDo("Learn to code");
    assertEquals(ToDo.all().size(), myToDo.getId());
  }

  @Test
  public void find_returnsToDoWithSameId_secondToDo() {
    ToDo firstToDo = new ToDo("Learn to code");
    ToDo secondToDo = new ToDo("Walk the dog");
    assertEquals(ToDo.find(secondToDo.getId()), secondToDo);
  }

  @Test
  public void find_returnsNullWhenNoToDoFound_null() {
    assertTrue(ToDo.find(999) == null);
  }

  @Test
  public void clear_emptiesAllToDosFromArrayList() {
    ToDo myToDo = new ToDo("Mow the lawn");
    ToDo.clear();
    assertEquals(ToDo.all().size(), 0);
  }

}
