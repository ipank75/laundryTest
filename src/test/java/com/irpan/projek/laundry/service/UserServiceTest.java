package com.irpan.projek.laundry.service;

import com.irpan.projek.laundry.entity.User;
import com.irpan.projek.laundry.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

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
    public void testFindAll() {
        Mockito.when(userRepository.findAll()).thenReturn(userListMock);
        Pageable pageable = PageRequest.of(0, 3);
        List<User> actual = userService.findAll(pageable);
        Assert.assertNotNull(actual);
    }

    @Test
    public void testFindById() {
        Mockito.when(userRepository.find(Mockito.anyInt())).thenReturn(userMock);
        User actual = userService.findById(userMock.getId());
        Assert.assertEquals(actual.getId(), userMock.getId());

    }

    @Test
    public void testUpdateById() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(userMock);
        User actual = userService.updateById(userMock.getId(), userMock);
        Assert.assertEquals(actual.getNamaUser(), userMock.getNamaUser());
    }
    @Test
    public void testCreate() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(userMock);
        User actual = userService.create(userMock);
        Assert.assertEquals(actual.getId(),userMock.getId());
        Assert.assertEquals(actual.getIdLevel(),userMock.getIdLevel());
        Assert.assertEquals(actual.getIdActive(),userMock.getIdActive());
        Assert.assertEquals(actual.getNamaUser(),userMock.getNamaUser());
        Assert.assertEquals(actual.getAlamatUser(),userMock.getAlamatUser());
        Assert.assertEquals(actual.getCreatedAt(),userMock.getCreatedAt());
        Assert.assertEquals(actual.getUpdateAt(),userMock.getUpdateAt());
    }
    @Test
    public void testDelete() {
        userMock.setIdActive(false);
        Mockito.when(userRepository.delete(Mockito.anyInt())).thenReturn(userMock);
        User actual = userService.delete(userMock.getId());
        Assert.assertTrue(actual.getIdActive());
    }
   
}
