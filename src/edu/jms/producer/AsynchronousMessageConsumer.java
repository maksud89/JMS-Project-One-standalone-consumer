package edu.jms.producer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class AsynchronousMessageConsumer {
	// Set up all the default values
	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_DESTINATION = "jms/queue/HELLOWORLDMDBQueue";
	private static final String DEFAULT_USERNAME = "quickstartUser";
	private static final String DEFAULT_PASSWORD = "quickstartPwd1!";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "remote://localhost:4447";

	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		MessageConsumer consumer = null;
		Destination destination = null;
		Context context = null;
		Properties env = new Properties();
		// Set up the context for the JNDI lookup
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL,
				System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
		env.put(Context.SECURITY_PRINCIPAL,
				System.getProperty("username", DEFAULT_USERNAME));
		env.put(Context.SECURITY_CREDENTIALS,
				System.getProperty("password", DEFAULT_PASSWORD));
		context = new InitialContext(env);
		// Perform the JNDI lookups
		String connectionFactoryString = System.getProperty(
				"connection.factory", DEFAULT_CONNECTION_FACTORY);
		connectionFactory = (ConnectionFactory) context
				.lookup(connectionFactoryString);
		String destinationString = System.getProperty("destination",
				DEFAULT_DESTINATION);
		destination = (Destination) context.lookup(destinationString);

		// Create the JMS connection, session and consumer
		connection = connectionFactory.createConnection(
				System.getProperty("username", DEFAULT_USERNAME),
				System.getProperty("password", DEFAULT_PASSWORD));
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		consumer = session.createConsumer(destination);
		connection.start();
		consumer.setMessageListener(new JmsMessageListener());

		System.out.println("Start listening... press any key to exit");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.read();

		session.close();
		connection.close();
	}
}

class JmsMessageListener implements MessageListener {
	@Override
	public void onMessage(Message message) {
		System.out.print("Message received: " + message + ", Payload: ");
		try {
			if (message instanceof TextMessage) {
				System.out.println(((TextMessage) message).getText());
			} else if (message instanceof ObjectMessage) {
				System.out.println(((ObjectMessage) message).getObject());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}