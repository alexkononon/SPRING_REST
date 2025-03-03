package com.alexkononon.star_wars_project.service;

/**
 * Service interface for sending SMS messages.
 */
public interface SmsService {

    /**
     * Sends an SMS message to the specified phone number.
     *
     * @param to the recipient phone number
     * @param messageBody the text message content
     */
    void sendSms(String to, String messageBody);
}
