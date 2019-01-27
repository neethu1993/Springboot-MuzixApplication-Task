package com.stackroute.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.MuzixNotFoundException;
import com.stackroute.service.MuzixService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
@RunWith(SpringRunner.class)

//Used when a test focuses only on Spring MVC components.
@WebMvcTest
public class MuzixControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Muzix muzix;

    //Any existing single bean of the same type defined in the context will be replaced by the mock.
    @MockBean
    private MuzixService muzixService;

    //Inject the mocks as dependencies into MuzixServiceImpl
    @InjectMocks
    private MuzixController muzixController;

    private List<Muzix> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzixController).build();
        muzix = new Muzix();
        muzix.setTrackName("Jonny");
        muzix.setTrackId(101);
        muzix.setComment("Jenny");
        list = new ArrayList();
        list.add(muzix);
    }

    @Test
    public void saveMuzixSuccess() throws Exception {
        when(muzixService.saveMuzix(any())).thenReturn(muzix);
        mockMvc.perform(post("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getAllMuzix() throws Exception {
        when(muzixService.getAllMuzixs()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateMuzixSuccess() throws Exception {
        when(muzixService.saveMuzix(muzix)).thenReturn(muzix);

        when(muzixService.updateMuzix(anyInt(),anyString())).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/muzix/101")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteMuzix() throws Exception {
        when(muzixService.removeMuzix(anyInt())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/muzix/101")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getByTrackId() throws Exception {
        when(muzixService.trackByTrackId(anyInt())).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix/101")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getByTrackIdFailure() throws Exception {
        when(muzixService.trackByTrackId(anyInt())).thenThrow(MuzixNotFoundException.class);
        mockMvc.perform(post("/api/v1/muzix/123")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getByTrackName() throws Exception {
        when(muzixService.trackByTrackName(anyString())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzixs/Jonny")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getByTrackNameFailure() throws Exception {
        when(muzixService.trackByTrackName(anyString())).thenThrow(MuzixNotFoundException.class);
        mockMvc.perform(post("/api/v1/muzixs/abcd")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


}
