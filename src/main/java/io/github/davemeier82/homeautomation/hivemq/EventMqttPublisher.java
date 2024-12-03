/*
 * Copyright 2021-2023 the original author or authors.
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

package io.github.davemeier82.homeautomation.hivemq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.davemeier82.homeautomation.core.event.DevicePropertyEvent;
import io.github.davemeier82.homeautomation.core.mqtt.MqttClient;
import io.github.davemeier82.homeautomation.hivemq.mapper.EventToDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.io.UncheckedIOException;

public class EventMqttPublisher {

  private static final Logger log = LoggerFactory.getLogger(EventMqttPublisher.class);

  private final EventToDtoMapper eventToDtoMapper;
  private final MqttClient mqttClient;

  private final ObjectMapper objectMapper;

  private final String topic;

  public EventMqttPublisher(EventToDtoMapper eventToDtoMapper,
                            MqttClient mqttClient,
                            ObjectMapper objectMapper,
                            String topic
  ) {
    this.eventToDtoMapper = eventToDtoMapper;
    this.mqttClient = mqttClient;
    this.objectMapper = objectMapper;
    this.topic = topic;
  }

  @EventListener
  public synchronized void handleEvent(DevicePropertyEvent<?> event) {
    eventToDtoMapper.map(event).ifPresentOrElse(eventDto -> {
      try {
        mqttClient.publish(topic, objectMapper.writeValueAsBytes(eventDto));
      } catch (JsonProcessingException e) {
        throw new UncheckedIOException(e);
      }
    }, () -> log.error("failed to map event {}", event.getClass().getName()));
  }
}
