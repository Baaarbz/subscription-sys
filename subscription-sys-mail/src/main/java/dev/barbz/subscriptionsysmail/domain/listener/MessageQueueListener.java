package dev.barbz.subscriptionsysmail.domain.listener;

/**
 * Definition of the message queue listener contract
 */
public interface MessageQueueListener {

    /**
     * Listen the queue to send mail notification with the user specified in the message.
     * This message will be received as a JSON and properly mapped to {@link dev.barbz.subscriptionsysmail.domain.MailReceiver}
     * to lately use that data to send the mail.
     *
     * @param message subscription message as JSON
     */
    void listenSendMailQueue(String message);

}
