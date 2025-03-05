package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.NotificationDTO;
import com.alexkononon.star_wars_project.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> sendNotification(@RequestBody NotificationDTO notificationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.sendNotification(notificationDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotification(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotification(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationsForUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}

