package com.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Getter
@Setter
public class Module implements Serializable {

    private String name;

    private String template;

    private String target;

    private Database database;

    private List<Entity> entities;
}
