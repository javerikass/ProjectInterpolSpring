package by.tms.projectinterpol.service;

import by.tms.projectinterpol.config.DBConfigTest;
import by.tms.projectinterpol.dto.RequestsDTO;
import by.tms.projectinterpol.dto.UserDTO;
import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Role;
import by.tms.projectinterpol.entity.Status;
import by.tms.projectinterpol.util.TestDataImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DBConfigTest.class)
@Transactional
@WebAppConfiguration
class RequestsServiceImplTest {

    @Autowired
    private RequestsService requestsService;
    @Autowired
    private TestDataImporter dataImporter;


    @BeforeEach
    public void init() {
        dataImporter.cleanTestData();
        dataImporter.importTestData();
    }

    @Test
    void save() {
        UserDTO testUser = UserDTO.builder().username("testUser").role(Role.USER).password("1234").build();
        long save = requestsService.save(RequestsDTO.builder()
                .firstName("Jim")
                .lastName("Doe")
                .age(20)
                .gender(Gender.MALE)
                .nationality("USA")
                .details("details")
                .reward(2000)
                .status(Status.WANTED)
                .approved(true).user(testUser).build());
        Assertions.assertNotNull(save);
    }

    @Test
    void update() {
        RequestsDTO requestsDTO = requestsService.findRequestByName("Jim", "Doe").get(0);
        requestsDTO.setFirstName("Jimm");
        requestsService.update(requestsDTO);
        RequestsDTO jimm = requestsService.findRequestByName("Jimm", "Doe").get(0);
        Assertions.assertNotNull(jimm);
    }

    @Test
    void delete() {
        RequestsDTO requestsDTO = requestsService.findRequestByName("Jim", "Doe").get(0);
        requestsService.delete(requestsDTO);
        Assertions.assertEquals(15, requestsService.findAll().size());
    }

    @Test
    void findAll() {
        Assertions.assertEquals(16, requestsService.findAll().size());
    }

    @Test
    void findRequestByName() {
        Assertions.assertNotNull(requestsService.findRequestByName("Jim", "Doe").get(0));
    }

    @Test
    void findRequestByApproval() {
        Assertions.assertEquals(10, requestsService.findRequestsByApproval(true).size());
    }

    @Test
    void findRequestByAge() {
        Assertions.assertNotNull(requestsService.findRequestByAge(20).get(0));
    }

    @Test
    void findRequestByNationality() {
        Assertions.assertNotNull(requestsService.findRequestByNationality("Mexico").get(0));
    }

    @Test
    void findAmountRequestByStatusAndApproval() {
        Assertions.assertEquals(5, requestsService.findAmountRequestByStatusAndApproval(Status.MISSING, true));
    }

    @Test
    void findRequestsByGander() {
        Assertions.assertEquals(8, requestsService.findRequestsByGender(Gender.MALE).size());
    }

    @Test
    void findRequestsByStatusAndApproval() {
        Assertions.assertEquals(3, requestsService.findRequestsByStatusAndApprovalWithLimitAndOffset(Status.WANTED, true, "3", "2").size());
    }
}