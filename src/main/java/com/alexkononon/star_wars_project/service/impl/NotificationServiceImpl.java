package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.NotificationDTO;
import com.alexkononon.star_wars_project.entity.core.Notification;
import com.alexkononon.star_wars_project.entity.security.User;
import com.alexkononon.star_wars_project.mapper.NotificationMapper;
import com.alexkononon.star_wars_project.repository.core.NotificationRepository;
import com.alexkononon.star_wars_project.repository.security.UserRepository;
import com.alexkononon.star_wars_project.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   UserRepository userRepository,
                                   NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.notificationMapper = notificationMapper;
    }

    public NotificationDTO sendNotification(NotificationDTO notificationDTO) {
        User user = userRepository.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + notificationDTO.getUserId()));

        Notification notification = notificationMapper.fromDTOToNotification(notificationDTO, userRepository);
        notification.setSent_at(LocalDateTime.now());
        notification.setUser(user);

        notification = notificationRepository.save(notification);
        return notificationMapper.fromNotificationToDTO(notification);
    }

    public NotificationDTO getNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        return notificationMapper.fromNotificationToDTO(notification);
    }

    public List<NotificationDTO> getNotificationsForUser(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        return notifications.stream()
                .map(notificationMapper::fromNotificationToDTO)
                .collect(Collectors.toList());
    }

    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("Notification not found with id: " + id);
        }
        notificationRepository.deleteById(id);
    }
}
