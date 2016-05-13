# JMS-Project-One-standalone-consumer
A java SE satndalone JMS consumer

1. JMS producer is the same producer in JMS-Project-One. Producer in Jboss and consumer is SE standalone.
2. There is two implementations of standalone SE client, one for Synchronous another for Asynchronous
3. Synchronous means, message will be consumed only one at a time, it will not keep listening to message destination
4. Asynchronous means, once client is in online, any number of message available in the destination will be cusumed by the consumer.
5. Asynchronous client will exit once we hit any key.

How to Run in eclipse:

1. Jboss-client.jar need to be in the class path to access jboss from standalone client.
2. In jboss create the following application user to access the running application.
   UserName:quickstartUser
   Realm: ApplicationRealm
   Password	: quickstartPwd1!
   Roles: guest
3. Add user by running this script "EAP_HOME\bin\add-user.bat"
4. Message will be produced same way as JMS-Project-One. But don't deploy it with MyMDB. Commented out all code in this class, as this 
   is also a cosumer for a queue. In queue there is only one consumer can consume the message; and, who will be able to consume the message
   is not guranteed.      
3. To consume message synchronously run SychronousMessageConsumer. You will see the log message as a confirmation of the consumption.
4. To consume message synchronously run AsynchronousMessageConsumer. You will see the log message as a confirmation of the consumption.
5. Any message is in the queue will be consumed by the AsynchronousMessageConsumer, onMessage() method will be invoked as soon as message produced
6. Any number of message consumer can be registered with message listener. [Need to find out this scenerio]
7. We can Hit any key to stop listening. 
8. Once message gets consumed its get removed from the queue
9. To check number of messages in particular queue, we can run this command below from jboss cli
  "/subsystem=messaging/hornetq-server=default/jms-queue=<QUEUE_NAME>:read-resource(include-runtime=true, include-defaults=true)"
10. 
