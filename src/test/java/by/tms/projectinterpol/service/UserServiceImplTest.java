package by.tms.projectinterpol.service;

import by.tms.projectinterpol.config.DBConfigTest;
import by.tms.projectinterpol.dto.UserDTO;
import by.tms.projectinterpol.entity.Role;
import by.tms.projectinterpol.util.TestDataImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DBConfigTest.class)
@Transactional
@WebAppConfiguration
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private TestDataImporter dataImporter;


    @BeforeEach
    public void init() {
        dataImporter.cleanTestData();
        dataImporter.importTestData();
    }

    @Test
    void saveUser() {
        long testUser = userService.save(UserDTO.builder().username("testUser").role(Role.USER).password("1234").build());
        Assertions.assertNotNull(testUser);
    }

    @Test
    void findUserByUsername() {
        Optional<UserDTO> user1 = userService.findUserByUsername("user1");
        Assertions.assertNotNull(user1);
    }

//    @Test
//    void deleteUser() {
//        Optional<UserDTO> user1 = userService.findUserByUsername("user1");
//        userService.delete(user1.get());
//        Assertions.assertEquals(10, userService.findAllUsers().size());
//    }

    @Test
    void updateUser() {
        Optional<UserDTO> user1 = userService.findUserByUsername("user1");
        user1.get().setUsername("user12");
        userService.update(user1.get());
        Assertions.assertNotNull(userService.findUserByUsername("user12").get());
    }

    @Test
    void findAllUsers() {
        Assertions.assertEquals(11, userService.findAllUsers().size());
    }

    @Test
    void loadUserByUsername() {
        UserDetails user1 = userService.loadUserByUsername("user1");
        Assertions.assertNotNull(user1);
    }
}