package kr.co.gd.service.impl;

import kr.co.gd.Entity.Kiosk;
import kr.co.gd.dto.KioskDTO;
import kr.co.gd.mqtt.dto.AddedObject;
import kr.co.gd.mqtt.dto.Event;
import kr.co.gd.mqtt.dto.MQTTDTO;
import kr.co.gd.mqtt.dto.Parameters;
import kr.co.gd.mqtt.dto.Properties;
import kr.co.gd.mqtt.service.MQTTService;
import kr.co.gd.repository.KioskRepository;
import kr.co.gd.service.KioskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class KioskServiceImpl implements KioskService {
    @Autowired
    private KioskRepository kioskRepository;
    @Autowired
    private MQTTService mqttService;

    @Override
    public void bulkInsert() {
        List<Kiosk> insertList = new ArrayList<>();

        Kiosk topicOne = new Kiosk();
        topicOne.setCommand("order");
        topicOne.setTopic("/CloudRobot/objectAdded");
        topicOne.setSeq(1);

        Kiosk topicTwo = new Kiosk();
        topicTwo.setCommand("event");
        topicTwo.setStatus("StartCook");
        topicTwo.setTopic("/CloudRobot/event");
        topicTwo.setSeq(2);

        Kiosk topicThree = new Kiosk();
        topicThree.setCommand("event");
        topicThree.setStatus("EndCook");
        topicThree.setTopic("/CloudRobot/event");
        topicThree.setSeq(3);

        Kiosk topicFour = new Kiosk();
        topicFour.setCommand("event");
        topicFour.setStatus("Ejected");
        topicFour.setTopic("/CloudRobot/event");
        topicFour.setSeq(4);

        insertList.add(topicOne);
        insertList.add(topicTwo);
        insertList.add(topicThree);
        insertList.add(topicFour);

        kioskRepository.saveAll(insertList);
    }

    @Override
    public Kiosk findByStatus(String status) {
        return kioskRepository.findByStatus(status);
    }

    @Override
    public void sendOrderByMqtt(KioskDTO kioskDTO) {
        MQTTDTO mqttDTO = new MQTTDTO();
        mqttDTO.setTopic("/CloudRobot/" + kioskDTO.getTopic());
        mqttDTO.setDeviceId(kioskDTO.getDeviceId());

        if ("objectAdded".equalsIgnoreCase(kioskDTO.getTopic())) {
            List<Properties> propertyList = new ArrayList<>();
            propertyList.add(setProperty("OrderId", kioskDTO.getOrderId()));
            propertyList.add(setProperty("OrderType", kioskDTO.getOrderType()));
            propertyList.add(setProperty("Duration", "100"));
            propertyList.add(setProperty("Quantity", "1"));
            propertyList.add(setProperty("DropoffPOIID", kioskDTO.getDropOffPoiId()));
            List<AddedObject> addedObjects = new ArrayList<>();
            AddedObject addedObject = AddedObject.builder()
                    .properties(propertyList)
                    .build();
            addedObjects.add(addedObject);
            mqttDTO.setAddedObjects(addedObjects);
        } else {
            List<Parameters> parameterList = new ArrayList<>();
            parameterList.add(setParameter("OrderId", kioskDTO.getOrderId()));
            parameterList.add(setParameter("Status", kioskDTO.getStatus()));
            Event event = Event.builder()
                    .parameters(parameterList)
                    .build();
            mqttDTO.setEvent(event);
        }

        mqttService.publish(mqttDTO);
    }

    private Properties setProperty(String propertyId, String value) {
        String valueType = "string";

        switch(propertyId) {
            case "Duration":
            case "Quantity":
                valueType = "int";
                break;
        }

        return Properties.builder()
                .propertyId(propertyId)
                .value(value)
                .valueType(valueType)
                .build();
    }

    private Parameters setParameter(String parameterId, String value) {
        return Parameters.builder()
                .parameterId(parameterId)
                .value(value)
                .build();
    }

    @Override
    public void nextStep(KioskDTO kioskDTO) {
        ExecutorService es = Executors.newCachedThreadPool();
        CompletableFuture<Void> future = new CompletableFuture<>();
        es.submit(() -> future.complete(doSome(kioskDTO)));
    }

    public Void doSome(KioskDTO kioskDTO) {
        try {
            Thread.sleep(5000);
            kioskDTO.setTopic("event");
            kioskDTO.setStatus("StartCook");
            this.sendOrderByMqtt(kioskDTO);
            Thread.sleep(30000);
            kioskDTO.setStatus("EndCook");
            this.sendOrderByMqtt(kioskDTO);
            Thread.sleep(30000);
            kioskDTO.setStatus("Ejected");
            this.sendOrderByMqtt(kioskDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
