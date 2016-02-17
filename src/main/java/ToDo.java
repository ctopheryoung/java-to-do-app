import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToDo {

  private static ArrayList<ToDo> instances = new ArrayList<ToDo>();

  private String mDescription;
  private LocalDateTime mCreatedAt;
  private boolean mCompleted;
  private int mId;

  public ToDo(String description) {
    mDescription = description;
    mCreatedAt = LocalDateTime.now();
    mCompleted = false;
    instances.add(this);
    mId = instances.size();
  }

  public String getDescription() {
    return mDescription;
  }

  public boolean isCompleted() {
    return mCompleted;
  }

  public LocalDateTime getCreatedAt() {
    return mCreatedAt;
  }

  public int getId() {
    return mId;
  }

  public static ToDo find(int id) {
    try {
      return instances.get(id-1);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  public static void clear() {
    instances.clear();
  }

  public static ArrayList<ToDo> all() {
    return instances;
  }
}
