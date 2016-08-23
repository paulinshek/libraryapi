package library_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import library_project.models.Library;
import library_project.models.Reservation;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Iterator;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class LibraryControllerTests {

    private MockMvc mockMvc;

    private static Library library;

    @Before
    public void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

        MockitoAnnotations.initMocks(this);
        library = Mockito.mock(Library.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LibraryController(library)).build();

        when(library.requestBook(any(Integer.class))).thenReturn((long)1);

    }

    @Test
    public void displayAllReservations() throws Exception {
        Iterator<Reservation> resIt = Arrays.asList(
                new Reservation(1, 1, "08-Aug-2016", "20-Aug-2016", true),
                new Reservation(2, 2, "01-May-2016", "15-May-2016", true)).iterator();

        when(library.getAllReservations()).thenReturn(resIt);

        mockMvc.perform(get("/api/library/reservations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].bookId").value(1))
                .andExpect(jsonPath("$[0].startDate").value("08-Aug-2016"))
                .andExpect(jsonPath("$[0].endDate").value("20-Aug-2016"))
                .andExpect(jsonPath("$[0].isOut").value(true));
    }
}
