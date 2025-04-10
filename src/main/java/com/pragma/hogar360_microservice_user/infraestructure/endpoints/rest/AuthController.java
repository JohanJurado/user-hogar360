package com.pragma.hogar360_microservice_user.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_user.application.dtos.request.LoginRequest;
import com.pragma.hogar360_microservice_user.application.dtos.request.UserRequest;
import com.pragma.hogar360_microservice_user.application.dtos.response.LoginResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "API to authenticate users")
public class AuthController {

    private final IUserService userService;

    @Operation(summary = "Login", description = "User login")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Credentials",
                    content = @Content
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveSeller(userRequest));
    }
}

