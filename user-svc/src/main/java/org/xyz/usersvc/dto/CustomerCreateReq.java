package org.xyz.usersvc.dto;

public record CustomerCreateReq(
        Long id,
        String name,
        String imageUrl
) {
}
