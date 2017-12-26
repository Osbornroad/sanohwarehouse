package com.gmail.osbornroad.repository.jdbc.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class PostgreeJdbcTemplate extends NamedParameterJdbcTemplate {
    public PostgreeJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }
}
