package com.pragma.hogar360_microservice_user.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_user.application.dtos.request.UserRequest;
import com.pragma.hogar360_microservice_user.application.dtos.response.SaveUserResponse;
import com.pragma.hogar360_microservice_user.application.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pragma.hogar360_microservice_user.infraestructure.utils.constants.InfrastructureConstants.HAS_ROLE_ADMIN;
import static com.pragma.hogar360_microservice_user.infraestructure.utils.constants.InfrastructureConstants.HAS_ROLE_SELLER;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@Tag(name = "Users", description = "API to manage users")
public class UserController {

    private final IUserService userService;

    @Operation(summary = "Save User Seller", description = "Create a new seller")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Seller created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaveUserResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User invalid",
                    content = @Content
            )
    })
    @PostMapping("/")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<SaveUserResponse> saveSeller(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveSeller(userRequest));
    }
}
