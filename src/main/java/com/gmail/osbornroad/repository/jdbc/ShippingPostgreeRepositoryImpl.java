package com.gmail.osbornroad.repository.jdbc;

import com.gmail.osbornroad.model.Shipping;
import com.gmail.osbornroad.repository.ShippingPostgreeRepository;
import com.gmail.osbornroad.repository.jdbc.jdbctemplate.PostgreeJdbcTemplate;
import com.gmail.osbornroad.repository.jdbc.jdbctemplate.PostgreeNamedParameterJdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShippingPostgreeRepositoryImpl implements ShippingPostgreeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingPostgreeRepositoryImpl.class);

    @Autowired
    PostgreeNamedParameterJdbcTemplate postgreeNamedParameterJdbcTemplate;

    @Autowired
    PostgreeJdbcTemplate postgreeJdbcTemplate;

    @Override
    public Shipping get(int id) {
        return null;
    }

    @Override
    public List<Shipping> getAll() {
        return null;
    }

    @Override
    public boolean save(Shipping shipping) {
        LOGGER.info("Saving in Postgree:" + shipping.toString());

        Map<String, Object> params = new HashMap<>();
        params.put("shippingId", shipping.getShippingId());
        params.put("clustercode", shipping.getClusterCode());
        params.put("barcode", shipping.getBarcode());
//        params.put("shippingdatetime", shipping.getShippingDateTime());

        postgreeNamedParameterJdbcTemplate.update("INSERT INTO shipping (shippingid, clustercode, barcode) VALUES (:shippingId, :clustercode, :barcode)", params);

        return false;
    }


    @Override
    public int getMaxShippingId() {
        List<Integer> listMaxShippingId = postgreeJdbcTemplate.query("SELECT shippingid FROM shipping ORDER BY shippingid DESC LIMIT 1", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("shippingid");
            }
        });
        return listMaxShippingId.get(0);
    }
}
