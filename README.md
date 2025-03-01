# homeautomation-hivemq

This is an [HiveMQ](https://github.com/hivemq/hivemq-mqtt-client) implementation of the MqttClient of the [homeautomation-core](https://github.com/davemeier82/homeautomation-core/blob/main/README.md)
home automation framework.

## Usage

Checkout the detailed usage in the Demo: [homeautomation-demo](https://github.com/davemeier82/homeautomation-demo/blob/main/README.md)

```xml

<project>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.github.davemeier82.homeautomation</groupId>
                <artifactId>homeautomation-bom</artifactId>
                <version>${homeautomation-bom.version}</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>io.github.davemeier82.homeautomation</groupId>
            <artifactId>homeautomation-hivemq</artifactId>
        </dependency>
    </dependencies>
</project>
```

### Configuration parameter

| Property                                             | Default Value        | Description                                                                                                         |
|------------------------------------------------------|----------------------|---------------------------------------------------------------------------------------------------------------------|
| homeautomation.hivemq.eventTopic                     | homeautomation/event | Topic to which events of the homeautomation framework should get published to.                                      |
| homeautomation.hivemq.server.host                    |                      | Hostname/IP-Address of the MQtt Server                                                                              |
| homeautomation.hivemq.server.port                    | 1883                 | Port of the MQtt Server                                                                                             |
| homeautomation.hivemq.server.username                |                      | Username to authenticate on the MQtt Server                                                                         |          
| homeautomation.hivemq.server.password                |                      | Password to authenticate on the MQtt Server                                                                         |
| homeautomation.hivemq.server.subscriptionTopicPrefix | $share/ha/           | This prefix will be added to each topic subscription on the server. This can be used i.e. for shared subscriptions. |

