package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.NotificationDTO;
import java.util.List;

/**
 * Service interface for managing notifications.
 * <p>
 * Provides methods for sending, retrieving, and deleting notifications.
 * </p>
 */
public interface NotificationService {

    /**
     * Sends a notification.
     *
     * @param notificationDTO the notification data transfer object containing the message and recipient user id
     * @return the saved NotificationDTO with generated id and timestamp
     */
    NotificationDTO sendNotification(NotificationDTO notificationDTO);

    /**
     * Retrieves a notification by its unique identifier.
     *
     * @param id the unique identifier of the notification
     * @return the NotificationDTO representing the notification
     * @throws RuntimeException if a notification with the given id is not found
     */
    NotificationDTO getNotification(Long id);

    /**
     * Retrieves all notifications for a given user.
     *
     * @param userId the unique identifier of the user
     * @return a list of NotificationDTOs associated with the specified user
     */
    List<NotificationDTO> getNotificationsForUser(Long userId);

    /**
     * Deletes the notification with the given identifier.
     *
     * @param id the unique identifier of the notification to be deleted
     * @throws RuntimeException if a notification with the given id is not found
     */
    void deleteNotification(Long id);
}
