package com.irpan.projek.laundry.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserDTO {
    private Integer id;
    private String namaUser;
    private Integer idLevel;
    private String alamatUser;
    private Boolean idActive;
    private Date createdAt;
    private Date updatedAt;
}
