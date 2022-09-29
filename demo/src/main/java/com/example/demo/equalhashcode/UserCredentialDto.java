package com.example.demo.equalhashcode;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class UserCredentialDto {
    private String email;
    private String username;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserCredentialDto userDto = (UserCredentialDto) o;
        return username.equals(userDto.username) && email.equals(userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
