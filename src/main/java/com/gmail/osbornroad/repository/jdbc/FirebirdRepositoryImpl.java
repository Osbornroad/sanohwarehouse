package com.gmail.osbornroad.repository.jdbc;

import com.gmail.osbornroad.model.Recieving;
import com.gmail.osbornroad.model.Shipping;
import com.gmail.osbornroad.repository.FirebirdRepository;
import com.gmail.osbornroad.repository.jdbc.jdbctemplate.FireBirdJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FirebirdRepositoryImpl implements FirebirdRepository {

    @Autowired
    private FireBirdJdbcTemplate fireBirdJdbcTemplate;

    //Shipping repository

/*    @Override
    public Shipping getShipping(int id) {
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
    }*/

    @Override
    public List<Shipping> getUnsavedShippingList(int maxSavedId) {
        return fireBirdJdbcTemplate.query(
                "    SELECT BD_SHIPPING.FIELD_KEY, \n" +
                        "    bd_shipping.date_time,\n" +
                        "    bd_shipping.barcode,\n" +
                        "    bd_delivery.tcwi129\n" +
                        "FROM BD_SHIPPING\n" +
                        "   inner join bd_delivery on (bd_shipping.fk_delivery = bd_delivery.field_key)\n" +
                        "   where BD_SHIPPING.FIELD_KEY > " + maxSavedId +
                        " ORDER BY BD_SHIPPING.FIELD_KEY",
                (rs, rowNum) -> {
                            Shipping shipping = new Shipping(
                                    rs.getInt("FIELD_KEY"),
                                    rs.getString("tcwi129"),
                                    rs.getString("barcode"),
                                    rs.getTimestamp("date_time").toLocalDateTime()
                            );
                            return shipping;
                        }
        );
    }

    //Recieving repository

    @Override
    public List<Recieving> getUnsavedRecievingList(int maxSavedId) {
        return fireBirdJdbcTemplate.query(
                "SELECT field_key, date_time, comment " +
                        "FROM bd_prihod " +
                        "WHERE field_key > " + maxSavedId +
                        "ORDER BY field_key",
                (rs, rowNum) -> {
                    Recieving recieving = new Recieving(
                            rs.getInt("field_key"),
                            rs.getTimestamp("date_time").toLocalDateTime(),
                            rs.getString("comment")
                    );
                    return recieving;
                }
        );
    }
}
