package lk.mushtaqj.messages;

import lk.mushtaqj.shared.ChatBox;

public class Sender
{
  public static void main (String[] args)
  {
    System.out.println(args);
    final ChatBox mushtaqj = ChatBox.createChat("mushtaqj");

    String reply;
    do
    {
      reply = mushtaqj.send();
    }
    while (!reply.equals("bye"));

  }
}
