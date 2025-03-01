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

public class HiveMqMqttServerProperties {
  private String host;
  private String username;
  private String password;
  private int port = 1883;
  private String subscriptionTopicPrefix = "$share/ha/";


  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getSubscriptionTopicPrefix() {
    return subscriptionTopicPrefix;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void setSubscriptionTopicPrefix(String subscriptionTopicPrefix) {
    this.subscriptionTopicPrefix = subscriptionTopicPrefix;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
