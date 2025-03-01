/*
 * Copyright 2021-2025 the original author or authors.
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

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "homeautomation.hivemq")
public class HiveMqMqttProperties {
  private HiveMqMqttServerProperties server = new HiveMqMqttServerProperties();
  private String eventTopic = "homeautomation/event";

  public void setEventTopic(String eventTopic) {
    this.eventTopic = eventTopic;
  }

  public HiveMqMqttServerProperties getServer() {
    return server;
  }

  public String getEventTopic() {
    return eventTopic;
  }

  public void setServer(HiveMqMqttServerProperties server) {
    this.server = server;
  }
}
