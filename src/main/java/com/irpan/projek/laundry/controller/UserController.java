package com.irpan.projek.laundry.controller;

import com.irpan.projek.laundry.common.ResponseSuccess;
import com.irpan.projek.laundry.dto.UserDTO;
import com.irpan.projek.laundry.entity.User;
import com.irpan.projek.laundry.service.UserService;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    JMapper<User, UserDTO> mapper2Entity = new JMapper<>(User.class, UserDTO.class, "jmapper/user/entity_mapper.xml");
    JMapper<UserDTO, User> mapper2Dto = new JMapper<>(UserDTO.class, User.class, "jmapper/user/dto_mapper.xml");

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserDTO>> create(@RequestBody @Validated UserDTO userDTO) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        User userEntity = mapper2Entity.getDestination(userDTO);
        User userCreate = userService.create(userEntity);
        userDTO = mapper2Dto.getDestination(userCreate);

        /* Response */
        ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Success Create Data");
        responseSuccess.setService(nameofCurrMethod);
        responseSuccess.setTimestamp(new Timestamp(System.currentTimeMillis()));
        responseSuccess.setData(userDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseSuccess);
    }

    @PatchMapping(value = "{id}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserDTO>> patch(@PathVariable("id") Integer id, @RequestBody @Validated UserDTO userDto) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        User userEntity = mapper2Entity.getDestination(userDto);
        User userUpdated = userService.updateById(id, userEntity);
        userDto = mapper2Dto.getDestination(userUpdated);

        ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Success Update Data");
        responseSuccess.setService(nameofCurrMethod);
        responseSuccess.setData(userDto);

        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON)
                .body(responseSuccess);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<List<UserDTO>>> findAll(Pageable pageable) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        List<UserDTO> userDtoList = new ArrayList<>();
        List<User> userEntity = userService.findAll(pageable);
        userEntity.forEach(user -> userDtoList.add(mapper2Dto.getDestination(user)));

        ResponseSuccess<List<UserDTO>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Success Show Data");
        responseSuccess.setService(nameofCurrMethod);
        responseSuccess.setData(userDtoList);

        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON)
                .body(responseSuccess);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserDTO>> findById(@PathVariable("id") Integer id) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        User userUpdated = userService.findById(id);

        UserDTO userDto = mapper2Dto.getDestination(userUpdated);

        ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Success Update Data");
        responseSuccess.setService(nameofCurrMethod);
        responseSuccess.setData(userDto);

        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON)
                .body(responseSuccess);

    }



}
