package by.tms.projectinterpol.util;

import by.tms.projectinterpol.entity.*;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
public class TestDataImporter {

    private final SessionFactory sessionFactory;

    @Autowired
    public TestDataImporter(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void importTestData() {
        Session session = sessionFactory.getCurrentSession();

        User user1 = saveUser(session, "user1", "1234", Role.USER);
        User user2 = saveUser(session, "user2", "12345", Role.USER);
        User user3 = saveUser(session, "user3", "12354", Role.USER);
        User user4 = saveUser(session, "user4", "12534", Role.USER);
        User user5 = saveUser(session, "user5", "15234", Role.USER);
        User user6 = saveUser(session, "user6", "12346", Role.USER);
        User user7 = saveUser(session, "user7", "12364", Role.USER);
        User user8 = saveUser(session, "user8", "12634", Role.USER);
        User user9 = saveUser(session, "user9", "16234", Role.USER);
        User user0 = saveUser(session, "user0", "12347", Role.USER);
        User admin = saveUser(session, "admin", "12374", Role.ADMIN);

        Requests requests0 = saveRequest(session, "Jim", "Doe", 20, Gender.MALE, "USA", "fhdjhkfds", 2500, Status.WANTED, true, user0);
        Requests requests1 = saveRequest(session, "Andy", "Big", 30, Gender.FEMALE, "Russia", "fhdhgkfds", 2000, Status.WANTED, true, user0);
        Requests requests2 = saveRequest(session, "John", "Small", 25, Gender.MALE, "Mexico", "fhdjgkfds", 3000, Status.WANTED, true, user0);
        Requests requests3 = saveRequest(session, "Kira", "Middle", 35, Gender.FEMALE, "Spain", "fhdjhgkfds", 2000, Status.WANTED, true, user1);
        Requests requests4 = saveRequest(session, "Carl", "Zoo", 40, Gender.MALE, "France", "fhdjhkfds", 4000, Status.WANTED, true, user2);
        Requests requests5 = saveRequest(session, "Sofia", "Gim", 45, Gender.FEMALE, "Belgium", "fhdjhgkfds", 2000, Status.MISSING, true, user3);
        Requests requests6 = saveRequest(session, "Lip", "Goe", 50, Gender.MALE, "Poland", "fhdjhgkfds", 2000, Status.MISSING, true, user4);
        Requests requests7 = saveRequest(session, "Emma", "Doe", 55, Gender.FEMALE, "China", "fhdhgkfds", 2000, Status.MISSING, true, user5);
        Requests requests8 = saveRequest(session, "Jim", "Wick", 60, Gender.MALE, "Japan", "fhdjhgkfds", 9000, Status.MISSING, true, user6);
        Requests requests9 = saveRequest(session, "Olivia", "Phone", 75, Gender.FEMALE, "Korea", "fhjhgkfds", 2000, Status.MISSING, true, user7);
        Requests requests10 = saveRequest(session, "Jim", "Woolf", 80, Gender.MALE, "Russian", "fhdjhkfds", 2000, Status.WANTED, false, user8);
        Requests requests11 = saveRequest(session, "Isabella", "Mouse", 90, Gender.FEMALE, "USA", "fhdhgkfds", 2000, Status.WANTED, false, user8);
        Requests requests12 = saveRequest(session, "Harry", "Stool", 22, Gender.MALE, "USA", "fhdjhgfds", 2000, Status.WANTED, false, user9);
        Requests requests13 = saveRequest(session, "Emily", "Table", 26, Gender.FEMALE, "Germany", "fhjhgkfds", 2000, Status.MISSING, false, user9);
        Requests requests14 = saveRequest(session, "Oliver", "Soap", 31, Gender.MALE, "Spain", "fhdjgkfds", 2000, Status.MISSING, false, user5);
        Requests requests15 = saveRequest(session, "Harper", "Book", 29, Gender.FEMALE, "USA", "fhdjhkfds", 2000, Status.MISSING, false, user7);

        Tag tag0 = saveTag(session, "Murder");
        Tag tag1 = saveTag(session, "Raped");
        Tag tag2 = saveTag(session, "Terror");
        Tag tag3 = saveTag(session, "Drugs");
        Tag tag4 = saveTag(session, "UFO");
        Tag tag5 = saveTag(session, "Kidnap");
        Tag tag6 = saveTag(session, "Heist");
        Tag tag7 = saveTag(session, "Corruption");

        News news1 = saveNews(session, "Murderdfsdf", "fdfas dfsasdfs sadfdf", LocalDate.now(), Arrays.asList(tag2));
        News news2 = saveNews(session, "Rapefdsfsdd", "hghdfg dsfaefc fcdsfas", LocalDate.of(2022, Month.APRIL, 10), Arrays.asList(tag2, tag0));
        News news3 = saveNews(session, "Terrsdfsdfor", "sfgc fgscfsg fdscgdfs", LocalDate.of(2022, Month.APRIL, 10), Arrays.asList(tag0, tag1, tag3));
        News news4 = saveNews(session, "Drfdsugs", "dfshfcgsd fcgsfdgc cfdsgfd", LocalDate.of(2020, 5, 5), Arrays.asList(tag7, tag4));
        News news5 = saveNews(session, "Kidsfdnap", "fdgcvfg sgcfdc fscgfd", LocalDate.of(2020, 3, 25), Arrays.asList(tag6, tag5));

    }

    private User saveUser(Session session, String username, String password, Role role) {
        User user = User.builder().username(username).password(password).role(role).build();
        session.save(user);
        return user;
    }

    private Requests saveRequest(Session session,
                                 String firstName,
                                 String lastName,
                                 int age, Gender gender,
                                 String nationality,
                                 String details,
                                 int reward,
                                 Status status,
                                 boolean approved,
                                 User user) {
        Requests requests = Requests.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .gender(gender)
                .nationality(nationality)
                .details(details)
                .reward(reward)
                .status(status)
                .approved(approved)
                .users(user).build();
        session.save(requests);
        return requests;
    }

    private News saveNews(Session session, String headline, String text, LocalDate publicationDate, List<Tag> tags) {
        News news = News.builder().headline(headline).text(text).publicationDate(publicationDate).tags(tags).build();
        session.save(news);
        return news;
    }

    private Tag saveTag(Session session, String tag) {
        Tag tags = Tag.builder().tag(tag).build();
        session.save(tags);
        return tags;
    }

    public void cleanTestData() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from Requests ").executeUpdate();
        session.createQuery("delete from News ").executeUpdate();
        session.createQuery("delete from Tag ").executeUpdate();
        session.createQuery("delete from users ").executeUpdate();
    }
}
