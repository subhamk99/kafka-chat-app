package com.example.chatterbox.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record BroadcastMessageDto(
        @NotBlank(message = "User name cannot be blank") String userName,
        @NotBlank(message = "User message cannot be blank") String message
        ) { }
