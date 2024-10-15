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
import io.github.davemeier82.homeautomation.core.event.AlarmStateChangedEvent;
import io.github.davemeier82.homeautomation.core.event.AlarmStateUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.BatteryLevelChangedEvent;
import io.github.davemeier82.homeautomation.core.event.BatteryLevelUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.CloudBaseChangedEvent;
import io.github.davemeier82.homeautomation.core.event.CloudBaseUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.Co2LevelChangedEvent;
import io.github.davemeier82.homeautomation.core.event.Co2LevelUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.DevicePropertyEvent;
import io.github.davemeier82.homeautomation.core.event.DimmingLevelChangedEvent;
import io.github.davemeier82.homeautomation.core.event.DimmingLevelUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.HumidityChangedEvent;
import io.github.davemeier82.homeautomation.core.event.HumidityUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.IlluminanceChangedEvent;
import io.github.davemeier82.homeautomation.core.event.IlluminanceUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.LightningCountChangedEvent;
import io.github.davemeier82.homeautomation.core.event.LightningCountUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.LightningDistanceChangedEvent;
import io.github.davemeier82.homeautomation.core.event.LightningDistanceUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.MotionChangedEvent;
import io.github.davemeier82.homeautomation.core.event.MotionUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.PowerChangedEvent;
import io.github.davemeier82.homeautomation.core.event.PowerUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.PressureChangedEvent;
import io.github.davemeier82.homeautomation.core.event.PressureUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.RainIntervalAmountChangedEvent;
import io.github.davemeier82.homeautomation.core.event.RainIntervalAmountUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.RainRateChangedEvent;
import io.github.davemeier82.homeautomation.core.event.RainRateUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.RainTodayAmountChangedEvent;
import io.github.davemeier82.homeautomation.core.event.RainTodayAmountUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.RelayStateChangedEvent;
import io.github.davemeier82.homeautomation.core.event.RelayStateUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.RollerPositionChangedEvent;
import io.github.davemeier82.homeautomation.core.event.RollerPositionUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.RollerStateChangedEvent;
import io.github.davemeier82.homeautomation.core.event.RollerStateUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.SmokeStateChangedEvent;
import io.github.davemeier82.homeautomation.core.event.SmokeStateUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.TemperatureChangedEvent;
import io.github.davemeier82.homeautomation.core.event.TemperatureUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.UvIndexChangedEvent;
import io.github.davemeier82.homeautomation.core.event.UvIndexUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.WindDirectionChangedEvent;
import io.github.davemeier82.homeautomation.core.event.WindDirectionUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.WindGustDirectionChangedEvent;
import io.github.davemeier82.homeautomation.core.event.WindGustDirectionUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.WindGustSpeedChangedEvent;
import io.github.davemeier82.homeautomation.core.event.WindGustSpeedUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.WindRunChangedEvent;
import io.github.davemeier82.homeautomation.core.event.WindRunUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.WindSpeedChangedEvent;
import io.github.davemeier82.homeautomation.core.event.WindSpeedUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.WindowStateChangedEvent;
import io.github.davemeier82.homeautomation.core.event.WindowStateUpdatedEvent;
import io.github.davemeier82.homeautomation.core.event.WindowTiltAngleChangedEvent;
import io.github.davemeier82.homeautomation.core.event.WindowTiltAngleUpdatedEvent;
import io.github.davemeier82.homeautomation.core.repositories.DevicePropertyRepository;
import io.github.davemeier82.homeautomation.hivemq.EventDto;

public class EventToDtoMapper {

  public final DevicePropertyRepository devicePropertyRepository;

  public EventToDtoMapper(DevicePropertyRepository devicePropertyRepository) {
    this.devicePropertyRepository = devicePropertyRepository;
  }

  // TODO replace switch
  public EventDto map(DevicePropertyEvent<?> event) {
    return devicePropertyRepository.findByDevicePropertyId(event.getDevicePropertyId()).map(deviceProperty -> switch (event) {
      case AlarmStateUpdatedEvent e -> toEvent(deviceProperty, e, "AlarmStateUpdatedEvent");
      case AlarmStateChangedEvent e -> toEvent(deviceProperty, e, "AlarmStateChangedEvent");
      case BatteryLevelUpdatedEvent e -> toEvent(deviceProperty, e, "BatteryLevelUpdatedEvent");
      case BatteryLevelChangedEvent e -> toEvent(deviceProperty, e, "BatteryLevelChangedEvent");
      case CloudBaseUpdatedEvent e -> toEvent(deviceProperty, e, "CloudBaseUpdatedEvent");
      case CloudBaseChangedEvent e -> toEvent(deviceProperty, e, "CloudBaseChangedEvent");
      case Co2LevelUpdatedEvent e -> toEvent(deviceProperty, e, "Co2LevelUpdatedEvent");
      case Co2LevelChangedEvent e -> toEvent(deviceProperty, e, "Co2LevelChangedEvent");
      case DimmingLevelUpdatedEvent e -> toEvent(deviceProperty, e, "DimmingLevelUpdatedEvent");
      case DimmingLevelChangedEvent e -> toEvent(deviceProperty, e, "DimmingLevelChangedEvent");
      case HumidityUpdatedEvent e -> toEvent(deviceProperty, e, "HumidityUpdatedEvent");
      case HumidityChangedEvent e -> toEvent(deviceProperty, e, "HumidityChangedEvent");
      case IlluminanceUpdatedEvent e -> toEvent(deviceProperty, e, "IlluminanceUpdatedEvent");
      case IlluminanceChangedEvent e -> toEvent(deviceProperty, e, "IlluminanceChangedEvent");
      case LightningCountUpdatedEvent e -> toEvent(deviceProperty, e, "LightningCountUpdatedEvent");
      case LightningCountChangedEvent e -> toEvent(deviceProperty, e, "LightningCountChangedEvent");
      case LightningDistanceUpdatedEvent e -> toEvent(deviceProperty, e, "LightningDistanceUpdatedEvent");
      case LightningDistanceChangedEvent e -> toEvent(deviceProperty, e, "LightningDistanceChangedEvent");
      case MotionUpdatedEvent e -> toEvent(deviceProperty, e, "MotionUpdatedEvent");
      case MotionChangedEvent e -> toEvent(deviceProperty, e, "MotionChangedEvent");
      case PowerUpdatedEvent e -> toEvent(deviceProperty, e, "PowerUpdatedEvent");
      case PowerChangedEvent e -> toEvent(deviceProperty, e, "PowerChangedEvent");
      case PressureUpdatedEvent e -> toEvent(deviceProperty, e, "PressureUpdatedEvent");
      case PressureChangedEvent e -> toEvent(deviceProperty, e, "PressureChangedEvent");
      case RainIntervalAmountUpdatedEvent e -> toEvent(deviceProperty, e, "RainIntervalAmountUpdatedEvent");
      case RainIntervalAmountChangedEvent e -> toEvent(deviceProperty, e, "RainIntervalAmountChangedEvent");
      case RainRateUpdatedEvent e -> toEvent(deviceProperty, e, "RainRateUpdatedEvent");
      case RainRateChangedEvent e -> toEvent(deviceProperty, e, "RainRateChangedEvent");
      case RainTodayAmountUpdatedEvent e -> toEvent(deviceProperty, e, "RainTodayAmountUpdatedEvent");
      case RainTodayAmountChangedEvent e -> toEvent(deviceProperty, e, "RainTodayAmountChangedEvent");
      case RelayStateUpdatedEvent e -> toEvent(deviceProperty, e, "RelayStateUpdatedEvent");
      case RelayStateChangedEvent e -> toEvent(deviceProperty, e, "RelayStateChangedEvent");
      case RollerPositionUpdatedEvent e -> toEvent(deviceProperty, e, "RollerPositionUpdatedEvent");
      case RollerPositionChangedEvent e -> toEvent(deviceProperty, e, "RollerPositionChangedEvent");
      case RollerStateUpdatedEvent e -> toEvent(deviceProperty, e, "RollerStateUpdatedEvent");
      case RollerStateChangedEvent e -> toEvent(deviceProperty, e, "RollerStateChangedEvent");
      case SmokeStateUpdatedEvent e -> toEvent(deviceProperty, e, "SmokeStateUpdatedEvent");
      case SmokeStateChangedEvent e -> toEvent(deviceProperty, e, "SmokeStateChangedEvent");
      case TemperatureUpdatedEvent e -> toEvent(deviceProperty, e, "TemperatureUpdatedEvent");
      case TemperatureChangedEvent e -> toEvent(deviceProperty, e, "TemperatureChangedEvent");
      case UvIndexUpdatedEvent e -> toEvent(deviceProperty, e, "UvIndexUpdatedEvent");
      case UvIndexChangedEvent e -> toEvent(deviceProperty, e, "UvIndexChangedEvent");
      case WindowStateUpdatedEvent e -> toEvent(deviceProperty, e, "WindowStateUpdatedEvent");
      case WindowStateChangedEvent e -> toEvent(deviceProperty, e, "WindowStateChangedEvent");
      case WindowTiltAngleUpdatedEvent e -> toEvent(deviceProperty, e, "WindowTiltAngleUpdatedEvent");
      case WindowTiltAngleChangedEvent e -> toEvent(deviceProperty, e, "WindowTiltAngleChangedEvent");
      case WindSpeedUpdatedEvent e -> toEvent(deviceProperty, e, "WindSpeedUpdatedEvent");
      case WindSpeedChangedEvent e -> toEvent(deviceProperty, e, "WindSpeedChangedEvent");
      case WindGustSpeedUpdatedEvent e -> toEvent(deviceProperty, e, "WindGustSpeedUpdatedEvent");
      case WindGustSpeedChangedEvent e -> toEvent(deviceProperty, e, "WindGustSpeedChangedEvent");
      case WindDirectionUpdatedEvent e -> toEvent(deviceProperty, e, "WindDirectionUpdatedEvent");
      case WindDirectionChangedEvent e -> toEvent(deviceProperty, e, "WindDirectionChangedEvent");
      case WindGustDirectionUpdatedEvent e -> toEvent(deviceProperty, e, "WindGustDirectionUpdatedEvent");
      case WindGustDirectionChangedEvent e -> toEvent(deviceProperty, e, "WindGustDirectionChangedEvent");
      case WindRunUpdatedEvent e -> toEvent(deviceProperty, e, "WindRunUpdatedEvent");
      case WindRunChangedEvent e -> toEvent(deviceProperty, e, "WindRunChangedEvent");
      default -> null;
    }).orElseThrow(() -> new IllegalArgumentException("event not supported " + event.getClass().getSimpleName()));
  }

  private EventDto toEvent(DeviceProperty deviceProperty,
                           DevicePropertyEvent<?> event,
                                  String propertyType
  ) {
    DevicePropertyId devicePropertyId = deviceProperty.getId();
    return new EventDto(devicePropertyId.deviceId().type().getTypeName(),
        devicePropertyId.deviceId().id(),
        propertyType,
        deviceProperty.getDisplayName(),
        devicePropertyId.id(),
        event.getNewValue(),
        event.getPreviousValue().orElse(null),
        event.getNewTimestamp(),
        event.getPreviousTimestamp().orElse(null)
    );
  }
}
