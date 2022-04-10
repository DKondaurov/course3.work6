package pro.sky.java.course3.work6.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pro.sky.java.course3.work6.model.Student;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static pro.sky.java.course3.work6.constants.HogwartsConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setName(NAME);
        student.setAge(AGE);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void findStudent() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + ID_STUDENT, String.class))
                .isNotNull();
    }


    @Test
    void filterStudentByAge() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?age=" + AGE, String.class))
                .isNotNull();
    }

    @Test
    void filterStudentByAgeBetween() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/between?min=" + AGE + "&max=100", String.class))
                .isNotNull();
    }
}