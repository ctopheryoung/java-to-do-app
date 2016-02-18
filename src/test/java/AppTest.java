import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();


  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("To-do list!");
  }

  @Test
  public void toDoIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a To-Do"));
    fill("#description").with("Learn to code");
    submit(".btn");
    assertThat(pageSource()).contains("Your to-do has been saved.");
  }

  @Test
  public void toDoIsDisplayedTest() {
    goTo("http://localhost:4567/todos/new");
    fill("#description").with("Learn to code");
    submit(".btn");
    click("a", withText("View to-do list"));
    assertThat(pageSource()).contains("Learn to code");
  }

  @Test
    public void multipleToDosAreDisplayedTest() {
      goTo("http://localhost:4567/todos/new");
      fill("#description").with("Learn to code");
      submit(".btn");
      goTo("http://localhost:4567/todos/new");
      fill("#description").with("Walk the dog");
      submit(".btn");
      click("a", withText("View to-do list"));
      assertThat(pageSource()).contains("Learn to code");
      assertThat(pageSource()).contains("Walk the dog");
    }

    @Test
      public void todoShowPageDisplaysDescription() {
        goTo("http://localhost:4567/todos/new");
        fill("#description").with("Walk the dog");
        submit(".btn");
        click("a", withText("View to-do list"));
        click("a", withText("Walk the dog"));
        assertThat(pageSource()).contains("Walk the dog");
      }
}
