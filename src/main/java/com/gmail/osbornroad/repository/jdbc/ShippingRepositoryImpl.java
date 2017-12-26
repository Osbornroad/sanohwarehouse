package com.gmail.osbornroad.repository.jdbc;

import com.gmail.osbornroad.model.Shipping;
import com.gmail.osbornroad.repository.ShippingRepository;
import com.gmail.osbornroad.repository.jdbc.jdbctemplate.FireBirdJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ShippingRepositoryImpl implements ShippingRepository {

    @Autowired
    private FireBirdJdbcTemplate fireBirdJdbcTemplate;

    @Override
    public Shipping get(int id) {
        return fireBirdJdbcTemplate.queryForObject(
                "    SELECT BD_SHIPPING.FIELD_KEY, \n" +
                "    bd_shipping.date_time,\n" +
                "    bd_shipping.barcode,\n" +
                "    bd_delivery.tcwi129\n" +
                "FROM BD_SHIPPING\n" +
                "   inner join bd_delivery on (bd_shipping.fk_delivery = bd_delivery.field_key)\n" +
                "   where BD_SHIPPING.FIELD_KEY = " + id, new RowMapper<Shipping>() {
            @Override
            public Shipping mapRow(ResultSet resultSet, int i) throws SQLException {
                Shipping shipping = new Shipping(
                        resultSet.getInt("FIELD_KEY"),
                        resultSet.getString("tcwi129"),
                        resultSet.getString("barcode"),
                        resultSet.getTimestamp("date_time").toLocalDateTime()
                );
                return shipping;
            }
        });
    }

    @Override
    public List<Shipping> getAll() {
        return null;
    }
}
