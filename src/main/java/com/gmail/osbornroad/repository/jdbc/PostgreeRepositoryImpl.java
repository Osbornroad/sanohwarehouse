package com.gmail.osbornroad.repository.jdbc;

import com.gmail.osbornroad.model.Shipping;
import com.gmail.osbornroad.repository.PostgreeRepository;
import com.gmail.osbornroad.repository.jdbc.jdbctemplate.PostgreeJdbcTemplate;
import com.gmail.osbornroad.repository.jdbc.jdbctemplate.PostgreeNamedParameterJdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostgreeRepositoryImpl implements PostgreeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgreeRepositoryImpl.class);

    @Autowired
    PostgreeNamedParameterJdbcTemplate postgreeNamedParameterJdbcTemplate;

    @Autowired
    PostgreeJdbcTemplate postgreeJdbcTemplate;

    //Shipping repository

    @Override
    public int saveShipping(Shipping shipping) {
        LOGGER.info("Saving in Postgree:" + shipping.toString());

        Map<String, Object> params = new HashMap<>();
        params.put("shippingId", shipping.getShippingId());
        params.put("clustercode", shipping.getClusterCode());
        params.put("barcode", shipping.getBarcode());
        params.put("shippingdatetime", Timestamp.valueOf(shipping.getShippingDateTime()));

        return  postgreeNamedParameterJdbcTemplate.update("INSERT INTO shipping (shippingid, clustercode, barcode, shippingdatetime) VALUES (:shippingId, :clustercode, :barcode, :shippingdatetime)", params);
    }

    @Override
    public int getMaxSavedShippingId() {
        List<Integer> listMaxShippingId = postgreeJdbcTemplate.query("SELECT shippingid FROM shipping ORDER BY shippingid DESC LIMIT 1", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("shippingid");
            }
        });
        return listMaxShippingId.size() == 0 ? 0 : listMaxShippingId.get(0);
    }

    //FinishPart repository
/*
    @Override
    public int saveRecieving(FinishPart recieving) {

        Map<String, Object> params = new HashMap<>();
        params.put("recieving_id", recieving.getRecievingId());
        params.put("recieving_date_time", Timestamp.valueOf(recieving.getRecievingDateTime()));
        params.put("comment", recieving.getComment());

        return postgreeNamedParameterJdbcTemplate.update("INSERT INTO recieving_fg (recieving_id, recieving_date_time, comment) VALUES (:recieving_id, :recieving_date_time, :comment)", params);
    }*/

    @Override
    public int getMaxSavedRecievingId() {
        return 0;
    }
}
