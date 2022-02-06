package lk.mushtaqj.shared;

import org.json.JSONObject;

public class ChatMessage
{
  private final String username;
  private final String message;

  public static ChatMessage fromJson(final String jsonStr) {
    final JSONObject jsonObject = new JSONObject(jsonStr);

    return new ChatMessage(jsonObject.getString("username"), jsonObject.getString("message"));
  }

  public ChatMessage (String username, String message)
  {
    this.username = username;
    this.message = message;
  }

  public String getUsername ()
  {
    return username;
  }

  public String getMessage ()
  {
    return message;
  }

  public JSONObject toJson() {
    return new JSONObject(this);
  }

  @Override
  public String toString ()
  {
    return toJson().toString(2);
  }
}
