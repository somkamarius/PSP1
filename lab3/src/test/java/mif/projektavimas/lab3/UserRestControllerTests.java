package mif.projektavimas.lab3;

import mif.projektavimas.lab3.controller.UserRestController;
import mif.projektavimas.lab3.model.User;
import mif.projektavimas.lab3.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {UserRestController.class})
public class UserRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repositoryMock;

    @Test
    void findUserById_successfullyFindsUser() throws Exception {
        User mockUser = new User("Testas", "Testaitis", "+37065473826", "testas@testas.com", "Savanoriu g. 53-32", "BlogasSl4ppazodis!");
        when(repositoryMock.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(mockUser));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expected = "{\"id\":null, \"name\":\"Testas\", \"lastName\":\"Testaitis\", \"phoneNumber\":\"+37065473826\", \"emailAddress\":\"testas@testas.com\", \"homeAddress\":\"Savanoriu g. 53-32\", \"password\":\"BlogasSl4ppazodis!\" }";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void findUserById_throwsUserNotFoundException() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/22")
                .accept(MediaType.APPLICATION_JSON);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        });

        String expectedMessage = "User not found id = 22";
        String actualMessage = exception.getCause().getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void createUser_successfullyAddsUser() throws Exception {
        User mockUser = new User("Testas", "Testaitis", "+3706930345", "testas@testas.com", "Savanoriu g. 53-32", "BlogasSl4ppazodis!");
        when(repositoryMock.save(Mockito.any(User.class))).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user")
                .content("{\"name\":\"Testas\", \"lastName\":\"Testaitis\", \"phoneNumber\":\"+37065473826\", \"emailAddress\":\"testas@testas.com\", \"homeAddress\":\"Savanoriu g. 53-32\" ,\"password\":\"ewfeAAA12*\" }")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(repositoryMock).save(Mockito.any(User.class));
    }


    @Test
    void createUser_throwsInvalidPhoneNumberException() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user")
                .content("{\"name\":\"Testas\", \"lastName\":\"Testaitis\", \"phoneNumber\":\"+65473826\", \"emailAddress\":\"testas@testas.com\", \"homeAddress\":\"Savanoriu g. 53-32\" ,\"password\":\"ewfeAAA12*\" }")
                .contentType(MediaType.APPLICATION_JSON);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            ;
        });

        String expectedMessage = "This phone Number is not valid! +65473826";
        String actualMessage = exception.getCause().getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void createUser_throwsInvalidPasswordException() {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user")
                .content("{\"name\":\"Testas\", \"lastName\":\"Testaitis\", \"phoneNumber\":\"+65473826\", \"emailAddress\":\"testas@testas.com\", \"homeAddress\":\"Savanoriu g. 53-32\" ,\"password\":\"ew2*\" }")
                .contentType(MediaType.APPLICATION_JSON);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            ;
        });

        String expectedMessage = "This password is not valid! ew2*";
        String actualMessage = exception.getCause().getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void createUser_throwsInvalidEmailException() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user")
                .content("{\"name\":\"Testas\", \"lastName\":\"Testaitis\", \"phoneNumber\":\"+65473826\", \"emailAddress\":\"testastestas.com\", \"homeAddress\":\"Savanoriu g. 53-32\" ,\"password\":\"ew2*\" }")
                .contentType(MediaType.APPLICATION_JSON);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            ;
        });

        String expectedMessage = "This email address is not valid! testastestas.com";
        String actualMessage = exception.getCause().getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void deleteById_successfullyDeletedFromDB() throws Exception {
        User mockUser = new User("Testas", "Testaitis", "+37065473826", "testas@testas.com", "Savanoriu g. 53-32", "BlogasSl4ppazodis!");
        when(repositoryMock.save(mockUser)).thenReturn(mockUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(repositoryMock).deleteById(Mockito.anyLong());
    }
}