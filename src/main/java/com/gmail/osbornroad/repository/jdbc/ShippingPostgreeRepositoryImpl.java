package com.gmail.osbornroad.repository.jdbc;

import com.gmail.osbornroad.model.Shipping;
import com.gmail.osbornroad.repository.ShippingPostgreeRepository;
import com.gmail.osbornroad.repository.jdbc.jdbctemplate.PostgreeJdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShippingPostgreeRepositoryImpl implements ShippingPostgreeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingPostgreeRepositoryImpl.class);

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
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean save(Shipping shipping) {
        LOGGER.info("Saving in Postgree:" + shipping.toString());

        Map<String, Object> params = new HashMap<>();
        params.put("shippingId", shipping.getShippingId());
        params.put("clustercode", shipping.getClusterCode());
        params.put("barcode", shipping.getBarcode());
//        params.put("shippingdatetime", shipping.getShippingDateTime());

        postgreeJdbcTemplate.update("INSERT INTO shipping (shippingid, clustercode, barcode) VALUES (:shippingId, :clustercode, :barcode)", params);

        return false;
    }

    @Override
    public boolean update(Shipping shipping) {
        return false;
    }
}
