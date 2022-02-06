package lk.mushtaqj.messages;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import lk.mushtaqj.shared.ChatBox;
import lk.mushtaqj.shared.ChatMessage;

public class Receiver
{

  public static void main (String[] args)
  {
    final Consumer consumer = new Consumer()
    {
      @Override
      public void handleConsumeOk (String consumerTag)
      {

      }

      @Override
      public void handleCancelOk (String consumerTag)
      {

      }

      @Override
      public void handleCancel (String consumerTag) throws IOException
      {

      }

      @Override
      public void handleShutdownSignal (String consumerTag, ShutdownSignalException sig)
      {

      }

      @Override
      public void handleRecoverOk (String consumerTag)
      {

      }

      @Override
      public void handleDelivery (String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
        throws IOException
      {
        final ChatMessage chatMessage = ChatMessage.fromJson(new String(body));
        System.out.println(chatMessage.getUsername() + " : " + chatMessage.getMessage());
      }

    };
    final ChatBox adam = ChatBox.joinChat("Adam", consumer);
  }
}
