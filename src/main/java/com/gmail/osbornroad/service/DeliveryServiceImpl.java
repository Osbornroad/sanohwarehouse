
package com.gmail.osbornroad.service;

        import com.gmail.osbornroad.model.Delivery;
        import com.gmail.osbornroad.repository.DeliveryRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    public Delivery get(int id) {
        return deliveryRepository.get(id);
    }

    @Override
    public List<Delivery> getAll() {
        return deliveryRepository.getAll();
    }
}