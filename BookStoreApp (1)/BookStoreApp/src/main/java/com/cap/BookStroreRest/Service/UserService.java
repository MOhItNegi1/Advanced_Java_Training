package com.cap.BookStroreRest.Service;

import com.cap.BookStroreRest.DataTransferObject.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.cap.BookStroreRest.DataTransferObject.UserDto;
import com.cap.BookStroreRest.Entity.User;
import com.cap.BookStroreRest.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class UserService {

        private final UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        // Create User
        public UserDto createUser(UserDto userDto){

            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());

            User savedUser = userRepository.save(user);

            return mapToDto(savedUser);
        }

        // Get all users
        public List<UserDto> getAllUsers(){
            List<User> users = userRepository.findAll();

            return users.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }

        // Convert Entity → DTO
        private UserDto mapToDto(User user){

            UserDto dto = new UserDto();

            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());

            return dto;
        }

        public UserDto getUserById(Long id){

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return mapToDto(user);
        }

        public UserDto updateUserById(Long id, UserDto userDto){

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());

            User updatedUser = userRepository.save(user);

            return mapToDto(updatedUser);
        }

        public UserDto deleteUserById(Long id){

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            userRepository.delete(user);

            return mapToDto(user);
        }

        public PageResponse<UserDto> getUsers(int page, int size, String sortBy, String direction) {

            Sort sort = direction.equalsIgnoreCase("desc") ?
                    Sort.by(sortBy).descending() :
                    Sort.by(sortBy).ascending();

            Pageable pageable = PageRequest.of(page, size, sort);

            Page<User> userPage = userRepository.findAll(pageable);

            List<UserDto> users = userPage.getContent()
                    .stream()
                    .map(this::mapToDto)
                    .toList();

            return new PageResponse<>(
                    users,
                    userPage.getNumber(),
                    userPage.getSize(),
                    userPage.getTotalElements(),
                    userPage.getTotalPages()
            );
        }

    }

