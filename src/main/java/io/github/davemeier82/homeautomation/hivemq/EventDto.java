/*
 * Copyright 2021-2022 the original author or authors.
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

import java.time.ZonedDateTime;

/**
 * Payload
 *
 * @author David Meier
 * @since 0.4.0
 */
public class EventDto<T> {

  private final String type;
  private final String id;
  private final String propertyType;

  private final String label;
  private final long propertyId;
  private final T oldValue;
  private final T newValue;
  private final ZonedDateTime eventTime;
  private final ZonedDateTime oldValueTime;

  public EventDto(String type,
                  String id,
                  String propertyType,
                  String label, long propertyId,
                  T newValue,
                  T oldValue,
                  ZonedDateTime eventTime,
                  ZonedDateTime oldValueTime
  ) {
    this.type = type;
    this.id = id;
    this.propertyType = propertyType;
    this.label = label;
    this.propertyId = propertyId;
    this.oldValue = oldValue;
    this.newValue = newValue;
    this.eventTime = eventTime;
    this.oldValueTime = oldValueTime;
  }

  public String getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public String getPropertyType() {
    return propertyType;
  }

  public long getPropertyId() {
    return propertyId;
  }

  public T getOldValue() {
    return oldValue;
  }

  public T getNewValue() {
    return newValue;
  }

  public ZonedDateTime getEventTime() {
    return eventTime;
  }

  public ZonedDateTime getOldValueTime() {
    return oldValueTime;
  }
}
