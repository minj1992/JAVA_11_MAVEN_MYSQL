package net.codejava;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserByEmail() {
        // Given
        User user = new User();
        user.setEmail("ravikumar@gmail.com");
        user.setPassword("ravi2020"); // Ensure correct method name 'setPassword'
        user.setFirstName("Ravi");
        user.setLastName("Kumar");

        // When
        User savedUser = entityManager.persistFlushFind(user);

        // Then
        User foundUser = userRepository.findByEmail("ravikumar@gmail.com");
        assertThat(foundUser.getEmail()).isEqualTo(savedUser.getEmail());
    }
}
