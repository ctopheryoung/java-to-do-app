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
    fill("#description").with("Learn to code");
    submit(".btn");
    assertThat(pageSource()).contains("Your task has been saved.");
  }

  @Test
  public void toDoIsDisplayedTest() {
    goTo("http://localhost:4567/");
    fill("#description").with("Learn to code");
    submit(".btn");
    click("a", withText("Go Back"));
    assertThat(pageSource()).contains("Learn to code");
  }

  @Test
    public void multipleToDosAreDisplayedTest() {
      goTo("http://localhost:4567/");
      fill("#description").with("Learn to code");
      submit(".btn");
      click("a", withText("Go Back"));
      fill("#description").with("Buy groceries");
      submit(".btn");
      click("a", withText("Go Back"));
      assertThat(pageSource()).contains("Learn to code");
      assertThat(pageSource()).contains("Buy groceries");
    }
}
