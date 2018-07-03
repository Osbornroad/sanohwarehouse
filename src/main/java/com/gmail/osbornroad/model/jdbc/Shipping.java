package com.gmail.osbornroad.model.jdbc;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Shipping {

    private Integer id;
    private Integer shippingId;              //from BD_SHIPPING (FIELD_KEY)
    private String clusterCode;              //from BD_DELIVERY (TCW129)
    private String barcode;                  //from BD_SHIPPING (BARCODE)
    private LocalDateTime shippingDateTime;  //from BD_SHIPPING (DATE_TIME)

    public Shipping(int id, int shippingId, String clusterCode, String barcode, LocalDateTime shippingDateTime) {
        this.id = id;
        this.shippingId = shippingId;
        this.clusterCode = clusterCode;
        this.barcode = barcode;
        this.shippingDateTime = shippingDateTime;
    }

    public Shipping(Integer shippingId, String clusterCode, String barcode, LocalDateTime shippingDateTime) {
        this.id = null;
        this.shippingId = shippingId;
        this.clusterCode = clusterCode;
        this.barcode = barcode;
        this.shippingDateTime = shippingDateTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public String getClusterCode() {
        return clusterCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public LocalDateTime getShippingDateTime() {
        return shippingDateTime;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", shippingId=" + shippingId +
                ", clusterCode='" + clusterCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", shippingDateTime=" + shippingDateTime +
                '}';
    }
}
