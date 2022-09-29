package com.example.demo.equalhashcode;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
// Single lombok annotation for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields,
// and @RequiredArgsConstructor!
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String city;
    private String state;
    private String country;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserDto userDto = (UserDto) o;
        return username.equals(userDto.username) && email.equals(userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }


}
