package com.gmail.osbornroad.repository.jdbc;

import com.gmail.osbornroad.model.jdbc.FinishPart;
import com.gmail.osbornroad.repository.FirebirdRepository;
import com.gmail.osbornroad.repository.jdbc.jdbctemplate.FireBirdJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

/*    @Override
    public List<FinishPart> getUnsavedShippingList(int maxSavedId) {
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
    }*/

    //FinishPart repository


/*    @Override
    public List<FinishPart> getUnsavedShippingList(int maxSavedId) {
        return fireBirdJdbcTemplate.query(
                "",
                (rs, rowNum) -> {
                    FinishPart finishPart = new FinishPart(
                            rs.getInt("field_key"),
                            rs.getString("name"),
                            rs.getInt("cnt"),
                            rs.getTimestamp("date_time").toLocalDateTime()
                    );
                    return finishPart;
                }
        );    }*/

    @Override
    public List<FinishPart> getUnsavedShippingList(int maxSavedId) {
        return fireBirdJdbcTemplate.query(
                "select \n" +
                        "    bd_tovar.name,\n" +
                        "    sum( bd_shipping_table.cnt ) sum_of_cnt\n" +
                        "from bd_shipping_table\n" +
                        "   inner join bd_tovar on (bd_shipping_table.fk_tovar = bd_tovar.field_key)\n" +
                "group by bd_tovar.name",

                (rs, rowNum) -> {
                    FinishPart finishPart = new FinishPart(
//                            rs.getInt("field_key"),
                            rs.getString("name"),
                            rs.getInt("sum_of_cnt")
//                            rs.getTimestamp("date_time").toLocalDateTime()
                    );
                    return finishPart;
                }
        );    }



    public List<FinishPart> getUnsavedRecievingList(int maxSavedId) {
        int counter = 0;
        return fireBirdJdbcTemplate.query(
                "select \n" +
                        "    bd_tovar.name,\n" +
                        "    sum( bd_prihod_table.cnt ) sum_of_cnt\n" +
                        "from bd_prihod_table\n" +
                        "   inner join bd_tovar on (bd_prihod_table.fk_tovar = bd_tovar.field_key)\n" +
                        "group by bd_tovar.name",
                (rs, rowNum) -> {
                    FinishPart finishPart = new FinishPart(
//                            rs.getInt("field_key"),
                            rs.getString("name"),
                            rs.getInt("sum_of_cnt")
//                            rs.getTimestamp("date_time").toLocalDateTime()
                    );
                    return finishPart;
                }
        );
    }

}
