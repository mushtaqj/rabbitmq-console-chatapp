package lk.mushtaqj.shared;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.tools.json.JSONUtil;

public final class ChatBox
{
  private Channel channel;
  private final Scanner scanner = new Scanner(System.in);
  private final String username;

  private ChatBox (final String username)
  {
    this.username = username;
    System.out.println(
      "----------------------------- ChatterBox 1.0 -----------------------------\n" + "\t\t\tInitializing chat ...\n" +
      "\t\t\tLogged in as " + username);
  }

  public static ChatBox createChat (final String username)
  {
    return new ChatBox(username);
  }

  public static ChatBox joinChat (final String username, final Consumer consumer)
  {
    final ChatBox chatBox = new ChatBox(username);

    try
    {
      chatBox.getChannel().basicConsume(QueueConstants.MESSAGE_QUEUE_NAME, consumer);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return chatBox;
  }

  public String send ()
  {
    System.out.print("" + username + "˜: ");
    final String message = scanner.nextLine();

    final ChatMessage chatMessage = new ChatMessage(username, message);

    try
    {
      getChannel().basicPublish("", QueueConstants.MESSAGE_QUEUE_NAME, null, chatMessage.toString().getBytes());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    return message;
  }

  public void reply ()
  {
    System.out.print("" + username + "˜: ");
    final String message = scanner.nextLine();
    channel.ack
  }

  private Channel getChannel ()
  {
    if (channel == null)
    {
      try
      {
        channel = ChatConnectionFactory.getChannel();
      }
      catch (IOException | TimeoutException e)
      {
        e.printStackTrace();
      }
    }
    return channel;
  }
}
