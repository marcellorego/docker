package br.com.fourmart;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * Subscriber client that connects to broker and receives messages from a
 * specific topic or wildcard with MQTT.
 */

@SpringBootApplication
@IntegrationComponentScan
@EnableAutoConfiguration
@ComponentScan(basePackages = { "br.com.fourmart" })
public class MqttSubscriber {

    /**
     * Main method starts the Subscriber client that establishes a connection to the
     * broker and receive numMessages from a subscribed topic.
     *
     * @param args CLA if default need to be different
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(MqttSubscriber.class).web(false).run(args);
    }

}