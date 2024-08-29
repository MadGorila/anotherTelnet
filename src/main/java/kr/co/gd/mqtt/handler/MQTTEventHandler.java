package kr.co.gd.mqtt.handler;

import lombok.extern.java.Log;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.event.EventListener;
import org.springframework.integration.mqtt.core.MqttPahoComponent;
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent;
import org.springframework.stereotype.Component;

@Log
@Component
public class MQTTEventHandler {

    @EventListener
    public void connectLost(MqttConnectionFailedEvent event) {
        MqttPahoComponent source = event.getSourceAsType();
        MqttConnectOptions options = source.getConnectionInfo();
        log.info("MQTT Connection is broken!!");
    }
}
