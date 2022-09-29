package com.example.demo;

import com.example.demo.equalhashcode.UserCredentialDto;
import com.example.demo.equalhashcode.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class EqualAndHashcodeTests {

	/**
	 * Since we override username and email, equality of the 2 objects
	 * will be decided on the basis of username and email
	 */
	@Test
	void testUserEqualsAndHashcode() {
		final UserDto ramesh = UserDto.builder()
				.username("rssingh").firstName("Ratan").lastName("Singh")
				.city("Mumbai").country("India").email("rsingh@abc.com").build();

		final UserDto ashish = UserDto.builder()
				.username("ajoshi").firstName("Ashish").lastName("Joshi")
				.city("Mumbai").country("India").email("asingh@abc.com").build();

		// ramesh and ashish have different email and username
		// Hence these 2 users are not equal
		assertNotEquals(ramesh, ashish);

		final UserDto sudhir = UserDto.builder()
				.username("spathak").email("shtr029@abc.com").firstName("Sudhir").lastName("Rana")
				.city("Delhi").country("India").build();

		final UserDto sunil = UserDto.builder()
				.username("spathak").email("shtr029@abc.com").firstName("Sunil").lastName("Pathak")
				.city("Delhi").country("India").build();

		// sudhir and sunil both have same email and username
		// Hence these 2 users are equal
		assertEquals(sudhir, sunil);
		// If 2 objects are same then they will always have same hashcode
		assertEquals(sudhir.hashCode(), sunil.hashCode());

		final UserDto shridhar = UserDto.builder()
				.username("tharihar3").email("shridhart3@abc.com").firstName("Shridhar").lastName("T")
				.city("Kolkata").country("India").build();

		final UserDto rohit = UserDto.builder()
				.username("tharihar3").email("rdev@abc.com").firstName("Rohit").lastName("Deo")
				.city("Thane").country("India").build();

		// shridhar and rohit have same username but different emails
		// Hence these 2 users are not equal
		assertNotEquals(shridhar, rohit);
	}

	/**
	 * If we want
	 */
	@Test
	void testCustomKeyMap()  {
		Map<UserCredentialDto, UserDto> usersMap = new HashMap<>();
		final UserDto ramesh = UserDto.builder()
				.username("rssingh").firstName("Ratan").lastName("Singh")
				.city("Mumbai").country("India").email("rsingh@abc.com").build();

		final UserCredentialDto rameshCreds = UserCredentialDto.builder()
				.email(ramesh.getEmail()).username(ramesh.getUsername())
				.build();

		final UserDto ashish = UserDto.builder()
				.username("ajoshi").firstName("Ashish").lastName("Joshi")
				.city("Mumbai").country("India").email("asingh@abc.com").build();

		final UserCredentialDto ashishCreds = UserCredentialDto.builder()
				.email(ashish.getEmail()).username(ashish.getUsername())
				.build();

		usersMap.put(rameshCreds, ramesh);
		usersMap.put(ashishCreds, ashish);

		assertEquals(usersMap.size(), 2);

		final UserDto sudhir = UserDto.builder()
				.username("spathak").email("shtr029@abc.com").firstName("Sudhir").lastName("Rana")
				.city("Delhi").country("India").build();

		final UserCredentialDto sudhirCreds = UserCredentialDto.builder()
				.email(sudhir.getEmail()).username(sudhir.getUsername())
				.build();

		final UserDto sunil = UserDto.builder()
				.username("spathak").email("shtr029@abc.com").firstName("Sunil").lastName("Pathak")
				.city("Delhi").country("India").build();

		final UserCredentialDto sunilcreds = UserCredentialDto.builder()
				.email(sunil.getEmail()).username(sunil.getUsername())
				.build();

		usersMap.put(sudhirCreds, sudhir);
		assertEquals(usersMap.size(), 3);
		final UserDto sudhirDto = usersMap.get(sudhirCreds);
		assertEquals(sudhirDto.getFirstName(), "Sudhir");

		// sudhirCreds and sunilcreds both have same email and username
		// Hence below put operation will override the first entry and map will only have information related to sunil
		usersMap.put(sunilcreds, sunil);
		assertEquals(usersMap.size(), 3);
		final UserDto sunilDto = usersMap.get(sudhirCreds);
		assertEquals(sunilDto.getFirstName(), "Sunil");
	}

}
