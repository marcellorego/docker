package br.com.fourmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import br.com.fourmart.config.MqttConfig;

import java.util.UUID;

/**
 * Subscriber client that connects to broker and receives messages from a
 * specific topic or wildcard with MQTT.
 */

@SpringBootApplication
@IntegrationComponentScan
@EnableAutoConfiguration
@Component
@ComponentScan(basePackages = { "br.com.fourmart" })

// TODO: Update code with a ConfigDefaultVariables Properties file

public class MqttSubscriber {

    @Autowired
    private MqttConfig config;

    public long count = 0;

    /**
     * Main method starts the Subscriber client that establishes a connection to the
     * broker and receive numMessages from a subscribed topic.
     *
     * @param args CLA if default need to be different
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(MqttSubscriber.class).web(false).run(args);
    }

    /**
     * MessageChannel method creates a new mqttInputChannel to connect to the Broker
     * with a single threaded connection. Downstream components are connected via
     * Direct Channel.
     *
     * @return DirectChannel single threaded connection
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * MqttPahoClientFactory method establishes the URL of the server along with the
     * host and port, the username, and the password for connecting to the
     * determined broker. Generates the Last Will and Testament for the publisher
     * clientId for a lost connection situation. SSL connection to the broker is
     * possible with the correct keyStore and trustStore provided.
     *
     * @return factory with given variables
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        java.util.Properties sslClientProps = new java.util.Properties();

        /*
         * sslClientProps.setProperty("com.ibm.ssl.keyStore",
         * "src/main/resources/client.ks");
         * sslClientProps.setProperty("com.ibm.ssl.keyStorePassword","password");
         * 
         * sslClientProps.setProperty("com.ibm.ssl.trustStore",
         * "src/main/resources/client.ts");
         * sslClientProps.setProperty("com.ibm.ssl.trustStorePassword","password");
         */

        // TODO: Provide better logic in case user uses something besides the default
        // MQTT ports
        if (config.getPort() == 1883) {
            factory.setServerURIs("tcp://" + config.getHost() + ":" + config.getPort());
        } else {
            factory.setServerURIs("ssl://" + config.getHost() + ":" + config.getPort());
        }

        //factory.setUserName(config.getUsername());
        //factory.setPassword(config.getPassword());
        factory.setSslProperties(sslClientProps);
        factory.setWill(new DefaultMqttPahoClientFactory.Will(config.getTopic(), 
            "I have died...".getBytes(), config.getQos(), true));
        return factory;
    }

    /**
     * MessageProducer generates a clientID for the subscriber by using a randomUUID
     * for creating a connection to a broker. Creates a new connection adapter to a
     * broker by setting the clientID, MqttClientFactory, topic given to subscribe
     * to, Completion Timeout, Converter, Qos, and the Output Channel for
     * numMessages to go to.
     *
     * @return adapter for the mqttInbound connection
     */
    @Bean
    public MessageProducer mqttInbound() {
        if (config.getClientId().equals("clientTest")) {
            config.setClientId(UUID.randomUUID().toString());
            System.out.println(config.getClientId());
        }

        MqttPahoMessageDrivenChannelAdapter adapter = 
            new MqttPahoMessageDrivenChannelAdapter(config.getClientId(),
                mqttClientFactory(), config.getTopic());

        adapter.setCompletionTimeout(1000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(config.getQos());
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * Message handler calculates the amount of messages that have come in from the
     * subscribed topic and posts the result.
     *
     * @return message is the numMessages amount
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {

            // if (count % 10 == 0) {
            //     System.out.println(String.format("Received %d numMessages from " + 
            //     config.getTopic() + " topic.", count));
            // }
            // if (count == config.getNumMessages()) {
            //     System.exit(0);
            // }
            // count++;
            System.out.println(message.getPayload());
        };
    }

    /**
     * Creates a new instance of the PropertySourcesPlaceholderConfigurer to pass
     * property sources from the application.properties file.
     *
     * @return PropertySourcesPlaceholderConfigurer new instance
     */
    // @Bean
    // public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
    //     return new PropertySourcesPlaceholderConfigurer();
    // }
}