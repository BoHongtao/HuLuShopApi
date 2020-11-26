/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hulu.shop.common.domain;


import io.ebean.Model;
import io.ebean.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDate;

@DocStore
@Entity
@Table(name = "user")
@MappedSuperclass
public class User extends Model {
    @NotNull
    @Id
    @Size(max = 32)
    @Column(name = "userid", nullable = false)
    public String userid;

    @NotNull
    @Size(max = 64)
    @Column(name = "loginid", length = 64, nullable = false)
    public String loginid;

    @Size(max = 32)
    @Column(name = "name", length = 32)
    public String name;

    @NotNull
    @Size(max = 32)
    @Column(name = "phone", length = 32, nullable = false)
    public String phone;

    @Size(max = 64)
    @Column(name = "password", length = 64)
    public String password;

    @Size(max = 40)
    @Column(name = "wechat", length = 40)
    public String wechat;

    @Column(name = "idcardtype")
    public Integer idcardtype;

    @Size(max = 100)
    @Column(name = "idcardid",length = 100)
    public String idcardid;


    @Size(max = 10)
    @Column(name = "province",length = 10)
    public String province;

    @Size(max = 10)
    @Column(name = "city",length = 10)
    public String city;

    @Size(max = 10)
    @Column(name = "district",length = 10)
    public String district;

    @Size(max = 50)
    @Column(name = "address",length = 50)
    public String address;

    @Size(max = 1)
    @Column(name = "gender",length = 1)
    public String gender;

    @Column(name = "birthdate")
    public LocalDate birthdate;

    @Column(name = "regtime")
    public Timestamp regtime;

    @Size(max = 10)
    @Column(name = "regchannel",length = 10)
    public String regchannel;

    @Column(name = "status")
    public Integer status = 0;

    @Size(max = 100)
    @Column(name = "wx_authkey",length = 100)
    public String wx_authkey;

    @Size(max = 100)
    @Column(name = "wx_openid",length = 100)
    public String wx_openid;

    @Size(max = 200)
    @Column(name = "avatar_url",length = 200)
    public String avatar_url;

    @Size(max = 40)
    @Column(name = "real_name",length = 40)
    public String real_name;
}