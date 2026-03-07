package com.cap.BookStroreRest.Controller;

import com.cap.BookStroreRest.DataTransferObject.PageResponse;
import com.cap.BookStroreRest.DataTransferObject.UserDto;
import com.cap.BookStroreRest.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "User Management APIs")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    @Operation(
            summary = "Add a new user",
            description = "Creates a new user in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<UserDto> createUser(
            @RequestBody @Valid UserDto userDto) {

        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping("/getAllUsers")
    @Operation(
            summary = "Get all users",
            description = "Retrieves all users from the database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched successfully")
    })
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser/{id}")
    @Operation(
            summary = "Get user by ID",
            description = "Retrieves a single user using its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "ID of the user")
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }

    @PutMapping("/updateUser/{id}")
    @Operation(
            summary = "Update user",
            description = "Updates user details using ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> updateUser(

            @Parameter(description = "ID of the user to update")
            @PathVariable Long id,

            @RequestBody @Valid UserDto userDto) {

        return ResponseEntity.ok(
                userService.updateUserById(id, userDto)
        );
    }

    @DeleteMapping("/deleteUser/{id}")
    @Operation(
            summary = "Delete user",
            description = "Deletes a user using its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> deleteUser(

            @Parameter(description = "ID of the user to delete")
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.deleteUserById(id)
        );
    }

    @GetMapping("/page")
    @Operation(
            summary = "Get users with pagination and sorting",
            description = "Fetch users using page number, size, sorting field and direction"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched successfully")
    })
    public ResponseEntity<PageResponse<UserDto>> getUsers(

            @Parameter(description = "Page number (starts from 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of records per page")
            @RequestParam(defaultValue = "5") int size,

            @Parameter(description = "Field to sort by (id, name, email)")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sorting direction: asc or desc")
            @RequestParam(defaultValue = "asc") String direction
    ) {

        return ResponseEntity.ok(
                userService.getUsers(page, size, sortBy, direction)
        );
    }
}