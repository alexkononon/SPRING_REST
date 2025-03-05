package com.alexkononon.star_wars_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDTO {
    private Long id;
    private String message;
    private Long userId;
    private LocalDateTime sentAt;
}

