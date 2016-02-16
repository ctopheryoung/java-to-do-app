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
          model.put("todos", request.session().attribute("todos"));
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/todos", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();

          ArrayList<ToDo> todos = request.session().attribute("todos");

          if (todos == null) {
            todos = new ArrayList<ToDo>();
            request.session().attribute("todos", todos);
          }

          String description = request.queryParams("description");
          ToDo newToDo = new ToDo(description);

          todos.add(newToDo);

          model.put("template", "templates/success.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
