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

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.davemeier82.homeautomation.core.event.EventPublisher;
import io.github.davemeier82.homeautomation.core.event.factory.EventFactory;
import io.github.davemeier82.homeautomation.core.mqtt.MqttClient;
import io.github.davemeier82.homeautomation.hivemq.mapper.EventToDtoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * Auto configuration.
 *
 * @author David Meier
 * @since 0.4.0
 */
@Configuration
@AutoConfigureOrder(HIGHEST_PRECEDENCE)
public class HomeAutomationHiveMqAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  EventToDtoMapper eventToDtoMapper() {
    return new EventToDtoMapper();
  }

  @Bean
  @ConditionalOnMissingBean
  MqttClient mqttClient(EventFactory eventFactory,
                        EventPublisher eventPublisher,
                        @Value("${hivemq.server.host}") String serverHost,
                        @Value("${hivemq.server.port:1883}") int serverPort,
                        @Value("${hivemq.server.username:#{null}}") String username,
                        @Value("${hivemq.server.password:#{null}}") String password,
                        @Value("${hivemq.server.subscription-topic-prefix:$share/ha/}") String subscriptionTopicPrefix
  ) {
    return new HiveMqMqttClient(eventFactory, eventPublisher, serverHost, serverPort, username, password, subscriptionTopicPrefix);
  }

  @Bean
  @ConditionalOnMissingBean
  EventMqttPublisher eventMqttPublisher(EventToDtoMapper eventToDtoMapper,
                                        MqttClient mqttClient,
                                        ObjectMapper objectMapper,
                                        @Value("${hivemq.event-topic:homeautomation/event}") String topic
  ) {
    return new EventMqttPublisher(eventToDtoMapper, mqttClient, objectMapper, topic);
  }
}
