/*
 * Copyright 2021-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.davemeier82.homeautomation.hivemq;

import com.github.davemeier82.homeautomation.core.event.EventFactory;
import com.github.davemeier82.homeautomation.core.event.EventPublisher;
import com.github.davemeier82.homeautomation.core.mqtt.MqttClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HiveMqMqttClient implements MqttClient {

  private static final Logger log = LoggerFactory.getLogger(HiveMqMqttClient.class);
  private final Mqtt3AsyncClient client;
  private final EventFactory eventFactory;
  private final EventPublisher eventPublisher;
  private boolean connected = false;

  public HiveMqMqttClient(
      EventFactory eventFactory,
      EventPublisher eventPublisher,
      String serverHost,
      int serverPort
  ) {
    this.eventFactory = eventFactory;
    this.eventPublisher = eventPublisher;
    client = com.hivemq.client.mqtt.MqttClient.builder()
        .useMqttVersion3()
        .identifier(UUID.randomUUID().toString())
        .serverHost(serverHost)
        .serverPort(serverPort)
        .automaticReconnectWithDefaultConfig()
        .buildAsync();
  }

  @Override
  public boolean isConnected() {
    return connected;
  }

  @Override
  public void connect() {
    client.connectWith()
        .willPublish().topic("homeautomation/will")
        .payload("homeautomation lost connection".getBytes())
        .applyWillPublish()
        .send()
        .whenComplete((connAck, throwable) -> {
          if (throwable != null) {
            log.error("failed to connect to server", throwable);
          } else {
            connected = true;
            eventPublisher.publishEvent(eventFactory.createMqttClientConnectedEvent(this));
          }
        });
  }

  @Override
  public void publish(String topic, byte[] payload) {
    client.publishWith()
        .topic(topic)
        .payload(payload)
        .qos(MqttQos.EXACTLY_ONCE)
        .send()
        .whenComplete((mqtt3Publish, throwable) -> {
          if (throwable != null) {
            log.error("failed to publish message to topic: {}", topic, throwable);
          }
        });
  }

  @Override
  public void publish(String topic, String payload) {
    publish(topic, payload.getBytes(UTF_8));
  }

  @Override
  public void subscribe(String topic, BiConsumer<String, Optional<ByteBuffer>> consumer) {
    client.subscribeWith()
        .topicFilter(topic)
        .callback(publish -> {
          consumer.accept(publish.getTopic().toString(), publish.getPayload());
        })
        .send()
        .whenComplete((subAck, throwable) -> {
          if (throwable != null) {
            log.error("failed to subscribe to topic: {}", topic, throwable);
          } else {
            log.info("successfully subscribed to topic: {}", topic);
          }
        });
  }

  @Override
  public void disconnect() {
    connected = false;
    client.disconnect();
  }
}
