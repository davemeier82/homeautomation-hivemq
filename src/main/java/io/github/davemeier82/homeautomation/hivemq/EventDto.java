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

import java.time.OffsetDateTime;

public class EventDto {

  private final String type;
  private final String id;
  private final String propertyType;

  private final String displayName;
  private final String propertyId;
  private final Object oldValue;
  private final Object newValue;
  private final OffsetDateTime eventTime;
  private final OffsetDateTime oldValueTime;

  public EventDto(String type,
                  String id,
                  String propertyType,
                  String displayName,
                  String propertyId,
                  Object newValue,
                  Object oldValue,
                  OffsetDateTime eventTime,
                  OffsetDateTime oldValueTime
  ) {
    this.type = type;
    this.id = id;
    this.propertyType = propertyType;
    this.displayName = displayName;
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

  public String getDisplayName() {
    return displayName;
  }

  public String getPropertyType() {
    return propertyType;
  }

  public String getPropertyId() {
    return propertyId;
  }

  public Object getOldValue() {
    return oldValue;
  }

  public Object getNewValue() {
    return newValue;
  }

  public OffsetDateTime getEventTime() {
    return eventTime;
  }

  public OffsetDateTime getOldValueTime() {
    return oldValueTime;
  }
}
