package vanilla.os.collector.service.system.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ComputerSystemInfo {

    private String manufacturer;
    private String model;
    private String serialNumber;
    private String hardwareUUID;
    private BaseboardInfo baseboard;
    private FirmwareInfo firmware;
    
    public BaseboardInfo createBaseboard(String manufacturer, String model, String version, String serialNumber) {
        this.baseboard = new BaseboardInfo(manufacturer, model, version, serialNumber);
        return this.baseboard;
    }
    
    public FirmwareInfo createFirmware(String manufacturer, String name, String version, String description, String releaseDate) {
        this.firmware = new FirmwareInfo(manufacturer, name, version, description, releaseDate);
        return this.firmware;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class BaseboardInfo {
        private String manufacturer;
        private String model;
        private String version;
        private String serialNumber;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class FirmwareInfo {
        private String manufacturer;
        private String name;
        private String version;
        private String description;
        private String releaseDate;
    }
}
