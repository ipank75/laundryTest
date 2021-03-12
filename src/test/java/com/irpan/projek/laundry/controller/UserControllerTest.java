package com.irpan.projek.laundry.controller;

import com.irpan.projek.laundry.entity.User;
import com.irpan.projek.laundry.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    private User userMock;
    private List<User> userListMock;

    Date date =new Date();
    SimpleDateFormat now =new SimpleDateFormat("dd-MM-yyyy");
    @Before
    public void predefine() {
        userMock = new User();
        userMock.setIdActive(true);
        userMock.setId(1);
        userMock.setNamaUser("Irpan Maulana");
        userMock.setAlamatUser("Tegal");
        userMock.setIdLevel(1);
        userMock.setCreatedAt(now.format(date));
        userMock.setUpdateAt(now.format(date));

        userListMock = new ArrayList<>();

        User userMock1 = new User();
        userMock1 = new User();
        userMock1.setIdActive(true);
        userMock1.setId(1);
        userMock1.setNamaUser("AKu Irpan");
        userMock1.setAlamatUser("Jakarta");
        userMock1.setIdLevel(1);
        userMock1.setCreatedAt(now.format(date));
        userMock1.setUpdateAt(now.format(date));

        User userMock2 = new User();
        userMock2 = new User();
        userMock2.setIdActive(true);
        userMock2.setId(1);
        userMock2.setNamaUser("Ipank Maulana");
        userMock2.setAlamatUser("Brebes");
        userMock2.setIdLevel(1);
        userMock2.setCreatedAt(now.format(date));
        userMock2.setUpdateAt(now.format(date));

        User userMock3 = new User();
        userMock3 = new User();
        userMock3.setIdActive(true);
        userMock3.setId(1);
        userMock3.setNamaUser("Irpan M");
        userMock3.setAlamatUser("Mejasem");
        userMock3.setIdLevel(1);
        userMock3.setCreatedAt(now.format(date));
        userMock3.setUpdateAt(now.format(date));

        userListMock.add(userMock1);
        userListMock.add(userMock2);
        userListMock.add(userMock3);
    }

    @Test
    public void testCreate() throws Exception {
        Mockito.when(userService.create(Mockito.any(User.class))).thenReturn(userMock);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/irpan/laundry/user")
                .servletPath("/user")
                .contextPath("/irpan/laundry")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1,\"namaUser\" : \"Irpan MM\",\"alamatUser\" : \"Kota Tegal\",\"idLevel\" : 1,\"idActive\" : 0,\"createdAt\" : \"12-03-2021\",\"updatedAt\" : \"12-03-2021\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPatch() throws Exception {
        Mockito.when(userService.updateById(Mockito.anyInt(), Mockito.any(User.class))).thenReturn(userMock);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/irpan/laundry/user" + userMock.getId())
                .servletPath("/user/" + userMock.getId())
                .contextPath("/irpan/laundry")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1,\"namaUser\" : \"Irpan MM\",\"alamatUser\" : \"Kota Tegal\",\"idLevel\" : 1,\"idActive\" : 0,\"createdAt\" : \"12-03-2021\",\"updatedAt\" : \"12-03-2021\"}");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findAll() throws Exception {
        Mockito.when(userService.findAll(Mockito.any(Pageable.class))).thenReturn(userListMock);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/irpan/laundry/user")
                .servletPath("/user")
                .contextPath("/irpan/laundry")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findById() throws Exception {
        Mockito.when(userService.findById(Mockito.anyInt())).thenReturn(userMock);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/irpan/laundry/user/" + userMock.getId())
                .servletPath("/user/" + userMock.getId())
                .contextPath("/irpan/laundry")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
