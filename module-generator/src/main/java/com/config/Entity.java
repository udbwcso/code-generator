package com.config;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Entity implements Serializable {

    private String table;

    private String name;

    private String packageName;
}
