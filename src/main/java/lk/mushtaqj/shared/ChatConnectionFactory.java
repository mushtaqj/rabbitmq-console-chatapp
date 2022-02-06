package lk.mushtaqj.shared;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

public final class ChatConnectionFactory
{
  private static ConnectionFactory connectionFactory;

  public static Channel getChannel () throws IOException, TimeoutException
  {
    final ConnectionFactory factory = getConnectionFactory();
    final Channel channel = factory.newConnection().createChannel();
    channel.queueDeclare(QueueConstants.MESSAGE_QUEUE_NAME,false, false, false, null);
    return channel;
  }

  private static ConnectionFactory getConnectionFactory ()
  {

    if (connectionFactory == null)
    {
      connectionFactory = new ConnectionFactory();
      connectionFactory.setHost("localhost");
      connectionFactory.setPort(5672);
    }

    return connectionFactory;
  }
}
