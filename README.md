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
4. Message will be produced same way as JMS-Project-One. But don't deploy it with MyMDB. Commented out all code in this class, as this    is also a cosumer for a queue. In queue there is only one consumer can consume the message; and, who will be able to consume the      message is not guranteed. If we have the same implementation for Topic , then all consumer will be able to consume the message.   
5. To consume message synchronously run SychronousMessageConsumer. You will see the log message as a confirmation of the consumption.
6. To consume message synchronously run AsynchronousMessageConsumer. You will see the log message as a confirmation of the consumption.
7. Any message is in the queue will be consumed by the AsynchronousMessageConsumer, onMessage() method will be invoked as soon as message produced
8. Any number of message consumer can be registered with message listener. [Need to find out this scenerio]
9. We can Hit any key to stop listening. 
10. Once message gets consumed its get removed from the queue
11. To check number of messages in particular queue, we can run this command below from jboss cli
  "/subsystem=messaging/hornetq-server=default/jms-queue=<QUEUE_NAME>:read-resource(include-runtime=true, include-defaults=true)"
12. check "<persistence-enabled>true</persistence-enabled>" element in standalone-full. xml. "True" mean, even if the server goes        down or restarted, **unconsumed message will be there to consume later when server comes back**. 
13. Check another two elements "<permission type="send" roles="guest"/> and <permission type="consume" roles="guest"/>"; they mean that anyone in the role of guest will be able to send and consume message
14. ***Same project need to be tried with Durable*
