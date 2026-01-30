package org.xyz.usersvc.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

public record RegisterCustomerRequest(
        @NotEmpty(message = "email cannot be empty") String email,
        @NotEmpty(message = "password cannot be empty")String password,
        String firstName,
        String lastName,
        String phone,
        AddressRequest addressRequest
) {
}
