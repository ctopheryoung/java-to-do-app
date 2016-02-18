import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
      staticFileLocation("/public");
      String layout = "templates/layout.vtl";

      get("/", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/todos", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("todos", ToDo.all());
        model.put("template", "templates/todos.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("todos/new", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/todo-form.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/todos", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        String description = request.queryParams("description");
        ToDo newToDo = new ToDo(description);
        model.put("template", "templates/success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/todos/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        ToDo todo = ToDo.find(Integer.parseInt(request.params(":id")));
        model.put("todo", todo);
        model.put("template", "templates/todo.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
    }
}
