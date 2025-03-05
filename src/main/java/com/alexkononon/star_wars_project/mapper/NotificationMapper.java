package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.NotificationDTO;
import com.alexkononon.star_wars_project.entity.core.Notification;
import com.alexkononon.star_wars_project.entity.security.User;
import com.alexkononon.star_wars_project.repository.security.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "sentAt", source = "sent_at")
    NotificationDTO fromNotificationToDTO(Notification notification);


    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToUser")
    Notification fromDTOToNotification(NotificationDTO dto, @Context UserRepository userRepository);

    @Named("mapUserIdToUser")
    default User mapUserIdToUser(Long userId, @Context UserRepository userRepository) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
}
