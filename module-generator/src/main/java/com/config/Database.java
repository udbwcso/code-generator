package com.config;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

@Setter
@Getter
public class Database implements Serializable {

    private Properties properties;

    private Map<String, String> javaType;

    private Map<String, String> jdbcType;
}
