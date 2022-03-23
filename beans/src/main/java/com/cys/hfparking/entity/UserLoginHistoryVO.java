package com.cys.hfparking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginHistoryVO {
    private Integer id;
    private Integer uid;
    private String ip;
    private Date loginTime;
    private User users;
}
