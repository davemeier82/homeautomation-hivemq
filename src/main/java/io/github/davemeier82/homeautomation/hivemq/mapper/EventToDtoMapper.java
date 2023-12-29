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

import io.github.davemeier82.homeautomation.core.device.Device;
import io.github.davemeier82.homeautomation.core.device.property.DeviceProperty;
import io.github.davemeier82.homeautomation.core.event.*;
import io.github.davemeier82.homeautomation.core.repositories.DevicePropertyRepository;
import io.github.davemeier82.homeautomation.hivemq.EventDto;

import java.util.Optional;

/**
 * Maps {@link DevicePropertyEvent} to {@link EventDto}.
 *
 * @author David Meier
 * @since 0.1.0
 */
public class EventToDtoMapper {

  public final DevicePropertyRepository devicePropertyRepository;

  public EventToDtoMapper(DevicePropertyRepository devicePropertyRepository) {
    this.devicePropertyRepository = devicePropertyRepository;
  }

  public EventDto<?> map(DevicePropertyEvent event) {
    return devicePropertyRepository.getByDevicePropertyId(event.getDevicePropertyId()).map(deviceProperty -> {
      Device device = deviceProperty.getDevice();
      return switch (event) {
        case RelayStateChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.isOn(), "RelayStateChangedEvent");
        case TemperatureChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getTemperatureInDegree(), "TemperatureChangedEvent");
        case HumidityChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getRelativeHumidityInPercent(), "HumidityChangedEvent");
        case DimmingLevelChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getDimmingLevelInPercent(), "DimmingLevelChangedEvent");
        case IlluminanceChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getLux(), "IlluminanceChangedEvent");
        case RollerStateChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getState(), "RollerStateChangedEvent");
        case RollerPositionChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getPositionInPercent(), "RollerPositionChangedEvent");
        case BatteryLevelChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getBatteryLevelInPercent(), "BatteryLevelChangedEvent");
        case PowerChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getWatt(), "PowerChangedEvent");
        case WindowStateChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.isOpen(), "WindowStateChangedEvent");
        case MotionChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.motionDetected(), "MotionChangedEvent");
        case SmokeStateChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.isSmokeDetected(), "SmokeStateChangedEvent");
        case Co2LevelChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getPpm(), "Co2LevelChangedEvent");
        case AlarmStateChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getState(), "AlarmStateChangedEvent");
        case PressureChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getPressureInMbar(), "PressureChangedEvent");
        case UvIndexChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getUvIndex(), "UvIndexChangedEvent");
        case CloudBaseChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getCloudBaseInMeter(), "CloudBaseChangedEvent");
        case WindSpeedChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometerPerHour(), "WindSpeedChangedEvent");
        case WindGustSpeedChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometerPerHour(), "WindGustSpeedChangedEvent");
        case WindDirectionChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getDegree(), "WindDirectionChangedEvent");
        case WindGustDirectionChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getDegree(), "WindGustDirectionChangedEvent");
        case WindRunChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometer(), "WindRunChangedEvent");
        case RainIntervalAmountChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeter(), "RainIntervalAmountChangedEvent");
        case RainTodayAmountChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeter(), "RainTodayAmountChangedEvent");
        case RainRateChangedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeterPerHour(), "RainRateChangedEvent");
        case RelayStateUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.isOn(), "RelayStateUpdatedEvent");
        case TemperatureUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getTemperatureInDegree(), "TemperatureUpdatedEvent");
        case HumidityUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getRelativeHumidityInPercent(), "HumidityUpdatedEvent");
        case DimmingLevelUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getDimmingLevelInPercent(), "DimmingLevelUpdatedEvent");
        case IlluminanceUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getLux(), "IlluminanceUpdatedEvent");
        case RollerStateUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getState(), "RollerStateUpdatedEvent");
        case RollerPositionUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getPositionInPercent(), "RollerPositionUpdatedEvent");
        case BatteryLevelUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getBatteryLevelInPercent(), "BatteryLevelUpdatedEvent");
        case PowerUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getWatt(), "PowerUpdatedEvent");
        case WindowStateUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.isOpen(), "WindowStateUpdatedEvent");
        case MotionUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.motionDetected(), "MotionUpdatedEvent");
        case SmokeStateUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.isActive(), "SmokeStateUpdatedEvent");
        case Co2LevelUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getPpm(), "Co2LevelUpdatedEvent");
        case AlarmStateUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getState(), "AlarmStateUpdatedEvent");
        case PressureUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getPressureInMbar(), "PressureUpdatedEvent");
        case UvIndexUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getUvIndex(), "UvIndexUpdatedEvent");
        case CloudBaseUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getCloudBaseInMeter(), "CloudBaseUpdatedEvent");
        case WindSpeedUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometerPerHour(), "WindSpeedUpdatedEvent");
        case WindGustSpeedUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometerPerHour(), "WindGustSpeedUpdatedEvent");
        case WindDirectionUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getDegree(), "WindDirectionUpdatedEvent");
        case WindGustDirectionUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getDegree(), "WindGustDirectionUpdatedEvent");
        case WindRunUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometer(), "WindRunUpdatedEvent");
        case RainIntervalAmountUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeter(), "RainIntervalAmountUpdatedEvent");
        case RainTodayAmountUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeter(), "RainTodayAmountUpdatedEvent");
        case RainRateUpdatedEvent e ->
            toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeterPerHour(), "RainRateUpdatedEvent");
        default -> null;
      };

    }).orElseThrow();
  }

  @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "unchecked"})
  private <T> EventDto<T> toEvent(DeviceProperty deviceProperty,
                                  Device device,
                                  Optional<DataWithTimestamp<?>> previousValue,
                                  DataWithTimestamp<T> value,
                                  String propertyType
  ) {
    return new EventDto<>(device.getType(),
        device.getId(),
        propertyType,
        deviceProperty.getLabel(),
        deviceProperty.getId(),
        value == null ? null : value.getValue(),
        (T) previousValue.map(DataWithTimestamp::getValue).orElse(null),
        value == null ? null : value.getDateTime(),
        previousValue.map(DataWithTimestamp::getDateTime).orElse(null));
  }
}
