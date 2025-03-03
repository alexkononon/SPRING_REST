package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.dto.UserDTO;

/**
 * Service interface for managing user registration and authentication.
 * Provides methods for registering new users (with their character data)
 * and verifying user credentials (for login).
 */
public interface UserService {

    /**
     * Registers a new user along with associated character information.
     *
     * @param userDTO the user data transfer object, default Role = USER
     * @param characterDTO the character data transfer object associated with the user
     */
    void register(UserDTO userDTO, CharacterDTO characterDTO);

    /**
     * Verifies the user's credentials and returns a JWT token upon successful authentication.
     *
     * @param userDTO the user data transfer object containing login credentials
     * @return a JWT token if authentication is successful, otherwise an error message
     */
    String verify(UserDTO userDTO);

    /**
     * Updates the role of a specific user.
     *
     * <p>This method assigns a new role to the user with the given {@code userId}. The new role should be provided
     * as a valid role identifier (for example, "USER", "ADMIN", etc.).
     *
     * @param userId  the unique identifier of the user whose role is to be updated
     * @param newRole the new role to assign to the user
     */
    void updateUserRole(Long userId, String newRole);

}
