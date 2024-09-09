package kr.co.gd.mqtt.config;

import org.eclipse.paho.mqttv5.client.IMqttClient;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class MqttConfig {
//    private static final String clientId = "FPROD-N-001";
//    private static final String hostName = "192.168.100.105";
//    private static final String port = "1883";
    private static final String MQTT_USERNAME = "cloud";
    private static final String MQTT_PASSWORD = "zmfhatm*0";

    @Bean
    @ConfigurationProperties(prefix="mqtt")
    public MqttConnectionOptions mqttConnectionOptions() {
        MqttConnectionOptions connOptions = new MqttConnectionOptions();
        connOptions.setUserName(MQTT_USERNAME);
        connOptions.setPassword(MQTT_PASSWORD.getBytes());
        return connOptions;
    }

    @Bean
    public IMqttClient mqttClient() throws MqttException {
        IMqttClient mqttClient = new MqttClient("tcp://192.168.100.105:1883", "FPROD-D-001");
        MqttConnectionOptions options = new MqttConnectionOptions();
        options.setUserName(MQTT_USERNAME);
        options.setPassword(MQTT_PASSWORD.getBytes());
        options.setCleanStart(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);
//        mqttClient.connect(mqttConnectionOptions());
        mqttClient.connect(options);
        return mqttClient;
    }
    @Bean
    public IMqttClient noodleMqttClient() throws MqttException {
        IMqttClient mqttClient = new MqttClient("tcp://192.168.100.105:1883", "FPROD-N-001");
        MqttConnectionOptions options = new MqttConnectionOptions();
        options.setUserName(MQTT_USERNAME);
        options.setPassword(MQTT_PASSWORD.getBytes());
        options.setCleanStart(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);
//        mqttClient.connect(mqttConnectionOptions());
        mqttClient.connect(options);
        return mqttClient;
    }
//
//    @Bean
//    public MessageChannel mqttInputChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageProducer inbound() {
//        MqttPahoMessageDrivenChannelAdapter adapter =
//                new MqttPahoMessageDrivenChannelAdapter("tcp://192.168.100.105:1883", "FPROD-D-001", "/CloudRobot/operation");
//        adapter.setCompletionTimeout(5000);
//        adapter.setConverter(new DefaultPahoMessageConverter());
//        adapter.setQos(1);
//        adapter.setOutputChannel(mqttInputChannel());
//        return adapter;
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "mqttInputChannel")
//    public MessageHandler handler() {
//        return message -> System.out.println("MQTT MessageHandler: " + message.getPayload());
//    }
}
