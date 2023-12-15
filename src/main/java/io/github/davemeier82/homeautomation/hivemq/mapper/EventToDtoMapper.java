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
import io.github.davemeier82.homeautomation.hivemq.EventDto;

import java.util.Optional;

/**
 * Maps {@link DevicePropertyEvent} to {@link EventDto}.
 *
 * @author David Meier
 * @since 0.1.0
 */
public class EventToDtoMapper {

  public EventDto<?> map(DevicePropertyEvent event) {
    DeviceProperty deviceProperty = event.getDeviceProperty();
    Device device = deviceProperty.getDevice();
    // TODO change to switch statement
    if (event instanceof RelayStateChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.isOn(), e.getClass().getSimpleName());
    } else if (event instanceof TemperatureChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getTemperatureInDegree(), e.getClass().getSimpleName());
    } else if (event instanceof HumidityChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getRelativeHumidityInPercent(), e.getClass().getSimpleName());
    } else if (event instanceof DimmingLevelChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getDimmingLevelInPercent(), e.getClass().getSimpleName());
    } else if (event instanceof IlluminanceChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getLux(), e.getClass().getSimpleName());
    } else if (event instanceof RollerStateChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getState(), e.getClass().getSimpleName());
    } else if (event instanceof RollerPositionChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getPositionInPercent(), e.getClass().getSimpleName());
    } else if (event instanceof BatteryLevelChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getBatteryLevelInPercent(), e.getClass().getSimpleName());
    } else if (event instanceof PowerChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getWatt(), e.getClass().getSimpleName());
    } else if (event instanceof WindowStateChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.isOpen(), e.getClass().getSimpleName());
    } else if (event instanceof MotionChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.motionDetected(), e.getClass().getSimpleName());
    } else if (event instanceof SmokeStateChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.isSmokeDetected(), e.getClass().getSimpleName());
    } else if (event instanceof Co2LevelChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getPpm(), e.getClass().getSimpleName());
    } else if (event instanceof AlarmStateChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getState(), e.getClass().getSimpleName());
    } else if (event instanceof PressureChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getPressureInMbar(), e.getClass().getSimpleName());
    } else if (event instanceof UvIndexChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getUvIndex(), e.getClass().getSimpleName());
    } else if (event instanceof CloudBaseChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getCloudBaseInMeter(), e.getClass().getSimpleName());
    } else if (event instanceof WindSpeedChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometerPerHour(), e.getClass().getSimpleName());
    } else if (event instanceof WindGustSpeedChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometerPerHour(), e.getClass().getSimpleName());
    } else if (event instanceof WindDirectionChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getDegree(), e.getClass().getSimpleName());
    } else if (event instanceof WindGustDirectionChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getDegree(), e.getClass().getSimpleName());
    } else if (event instanceof WindRunChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometer(), e.getClass().getSimpleName());
    } else if (event instanceof RainIntervalAmountChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeter(), e.getClass().getSimpleName());
    } else if (event instanceof RainTodayAmountChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeter(), e.getClass().getSimpleName());
    } else if (event instanceof RainRateChangedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeterPerHour(), e.getClass().getSimpleName());
    } else if (event instanceof RelayStateUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.isOn(), e.getClass().getSimpleName());
    } else if (event instanceof TemperatureUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getTemperatureInDegree(), e.getClass().getSimpleName());
    } else if (event instanceof HumidityUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getRelativeHumidityInPercent(), e.getClass().getSimpleName());
    } else if (event instanceof DimmingLevelUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getDimmingLevelInPercent(), e.getClass().getSimpleName());
    } else if (event instanceof IlluminanceUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getLux(), e.getClass().getSimpleName());
    } else if (event instanceof RollerStateUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getState(), e.getClass().getSimpleName());
    } else if (event instanceof RollerPositionUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getPositionInPercent(), e.getClass().getSimpleName());
    } else if (event instanceof BatteryLevelUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getBatteryLevelInPercent(), e.getClass().getSimpleName());
    } else if (event instanceof PowerUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getWatt(), e.getClass().getSimpleName());
    } else if (event instanceof WindowStateUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.isOpen(), e.getClass().getSimpleName());
    } else if (event instanceof MotionUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.motionDetected(), e.getClass().getSimpleName());
    } else if (event instanceof SmokeStateUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.isActive(), e.getClass().getSimpleName());
    } else if (event instanceof Co2LevelUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getPpm(), e.getClass().getSimpleName());
    } else if (event instanceof AlarmStateUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getState(), e.getClass().getSimpleName());
    } else if (event instanceof PressureUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getPressureInMbar(), e.getClass().getSimpleName());
    } else if (event instanceof UvIndexUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getUvIndex(), e.getClass().getSimpleName());
    } else if (event instanceof CloudBaseUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getCloudBaseInMeter(), e.getClass().getSimpleName());
    } else if (event instanceof WindSpeedUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometerPerHour(), e.getClass().getSimpleName());
    } else if (event instanceof WindGustSpeedUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometerPerHour(), e.getClass().getSimpleName());
    } else if (event instanceof WindDirectionUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getDegree(), e.getClass().getSimpleName());
    } else if (event instanceof WindGustDirectionUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getDegree(), e.getClass().getSimpleName());
    } else if (event instanceof WindRunUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getKilometer(), e.getClass().getSimpleName());
    } else if (event instanceof RainIntervalAmountUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeter(), e.getClass().getSimpleName());
    } else if (event instanceof RainTodayAmountUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeter(), e.getClass().getSimpleName());
    } else if (event instanceof RainRateUpdatedEvent e) {
      return toEvent(deviceProperty, device, e.getPreviousValue(), e.getMillimeterPerHour(), e.getClass().getSimpleName());
    }

    return null;
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
