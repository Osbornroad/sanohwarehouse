package com.gmail.osbornroad.repository.jdbc;

import com.gmail.osbornroad.model.Delivery;
import com.gmail.osbornroad.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcDeliveryRepository implements DeliveryRepository {

    private static final BeanPropertyRowMapper<Delivery> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Delivery.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Delivery get(int id) {
        return jdbcTemplate.queryForObject("SELECT FIELD_KEY, APOINT, NUMBER FROM BD_DELIVERY WHERE FIELD_KEY=3465", new RowMapper<Delivery>() {
            @Override
            public Delivery mapRow(ResultSet resultSet, int i) throws SQLException {
                Delivery delivery = new Delivery(
                        resultSet.getInt("FIELD_KEY"),
                        resultSet.getString("APOINT"),
                        resultSet.getString("NUMBER"));
                return delivery;
            }
        });
    }

    @Override
    public List<Delivery> getAll() {
        List<Delivery> deliveryList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT FIELD_KEY, APOINT, NUMBER FROM BD_DELIVERY");
        for (Map row : rows) {
            Delivery delivery = new Delivery(
                    (Integer)row.get("FIELD_KEY"),
                    (String)row.get("APOINT"),
                    (String)row.get("NUMBER")
            );
            deliveryList.add(delivery);
        }
        return deliveryList;
    }
}
