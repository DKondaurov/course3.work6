package pro.sky.java.course3.work6.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.java.course3.work6.model.Faculty;
import pro.sky.java.course3.work6.repository.FacultyRepository;
import pro.sky.java.course3.work6.service.impl.FacultyServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.java.course3.work6.constants.HogwartsConstants.*;

@WebMvcTest
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private AvatarController avatarController;

    @MockBean
    private StudentController studentController;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void addFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", FACULTY_NAME);
        facultyObject.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(FACULTY_ID);
        faculty.setName(FACULTY_NAME);
        faculty.setColor(COLOR);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value((int) FACULTY_ID))
                .andExpect(jsonPath("$.name").value(FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void findFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", FACULTY_NAME);
        facultyObject.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(FACULTY_ID);
        faculty.setName(FACULTY_NAME);
        faculty.setColor(COLOR);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + FACULTY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value((int) FACULTY_ID))
                .andExpect(jsonPath("$.name").value(FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void removeFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", FACULTY_NAME);
        facultyObject.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(FACULTY_ID);
        faculty.setName(FACULTY_NAME);
        faculty.setColor(COLOR);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + FACULTY_ID, faculty.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void editFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", FACULTY_NAME);
        facultyObject.put("color", COLOR);

        Faculty faculty = new Faculty();
        faculty.setId(FACULTY_ID);
        faculty.setName(FACULTY_NAME);
        faculty.setColor(COLOR);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value((int) FACULTY_ID))
                .andExpect(jsonPath("$.name").value(FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }
}


