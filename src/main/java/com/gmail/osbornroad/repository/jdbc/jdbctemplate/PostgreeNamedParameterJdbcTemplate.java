package com.gmail.osbornroad.repository.jdbc.jdbctemplate;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class PostgreeNamedParameterJdbcTemplate extends NamedParameterJdbcTemplate {
    public PostgreeNamedParameterJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }
}
