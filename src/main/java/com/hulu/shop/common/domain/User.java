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

@DocStore
@Entity
@Table(name = "user")
@MappedSuperclass
public class User extends Model {
    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    public Integer id;

    @NotNull
    @Size(max = 255)
    @Column(name = "phone", length = 255, nullable = false)
    public String phone;

    @NotNull
    @Size(max = 255)
    @Column(name = "password", length = 255, nullable = false)
    public String password;
}