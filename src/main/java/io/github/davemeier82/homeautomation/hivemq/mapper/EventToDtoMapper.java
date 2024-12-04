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

package io.github.davemeier82.homeautomation.hivemq.mapper;

import io.github.davemeier82.homeautomation.core.device.property.DeviceProperty;
import io.github.davemeier82.homeautomation.core.device.property.DevicePropertyId;
import io.github.davemeier82.homeautomation.core.event.DevicePropertyEvent;
import io.github.davemeier82.homeautomation.core.repositories.DevicePropertyRepository;
import io.github.davemeier82.homeautomation.hivemq.EventDto;

import java.util.Optional;

public class EventToDtoMapper {

  public final DevicePropertyRepository devicePropertyRepository;

  public EventToDtoMapper(DevicePropertyRepository devicePropertyRepository) {
    this.devicePropertyRepository = devicePropertyRepository;
  }

  public Optional<EventDto> map(DevicePropertyEvent<?> event) {
    return devicePropertyRepository.findByDevicePropertyId(event.getDevicePropertyId()).map(deviceProperty -> toEvent(deviceProperty, event));
  }

  private EventDto toEvent(DeviceProperty deviceProperty,
                           DevicePropertyEvent<?> event
  ) {
    DevicePropertyId devicePropertyId = deviceProperty.getId();
    return new EventDto(devicePropertyId.deviceId().type().getTypeName(),
        devicePropertyId.deviceId().id(),
        event.getEventName(),
        deviceProperty.getDisplayName(),
        devicePropertyId.id(),
        event.getNewValue(),
        event.getPreviousValue().orElse(null),
        event.getNewTimestamp(),
        event.getPreviousTimestamp().orElse(null)
    );
  }
}
