package vanilla.os.collector.service.system.info;

import lombok.Data;
import oshi.hardware.PowerSource;

@Data
public class PowerSourceInfo {
    
    private String name;
    private String deviceName;
    private String manufacturer;
    private String chemistry;
    private String serialNumber;
    private double amperage;
    private int currentCapacity;
    private int cycleCount;
    private int designCapacity;
    private int maxCapacity;
    private double powerUsageRate;
    private double remainingCapacityPercent;
    private double temperature;
    private double timeRemainingEstimated;
    private double timeRemainingInstant;
    private double voltage;
    private boolean charging;
    private boolean discharging;
    private boolean powerOnLine;
    private boolean updateAttributes;
    
    public PowerSourceInfo() {
    }

    public PowerSourceInfo(PowerSource powerSource) {
        this.name = powerSource.getName();
        this.deviceName = powerSource.getDeviceName();
        this.manufacturer = powerSource.getManufacturer();
        this.chemistry = powerSource.getChemistry();
        this.serialNumber = powerSource.getSerialNumber();
        this.amperage = powerSource.getAmperage();
        this.currentCapacity = powerSource.getCurrentCapacity();
        this.cycleCount = powerSource.getCycleCount();
        this.designCapacity = powerSource.getDesignCapacity();
        this.maxCapacity = powerSource.getMaxCapacity();
        this.powerUsageRate = powerSource.getPowerUsageRate();
        this.remainingCapacityPercent = powerSource.getRemainingCapacityPercent();
        this.temperature = powerSource.getTemperature();
        this.timeRemainingEstimated = powerSource.getTimeRemainingEstimated();
        this.timeRemainingInstant = powerSource.getTimeRemainingInstant();
        this.voltage = powerSource.getVoltage();
        this.charging = powerSource.isCharging();
        this.discharging = powerSource.isDischarging();
        this.powerOnLine = powerSource.isPowerOnLine();
        this.updateAttributes = powerSource.updateAttributes();
//        powerSource.getCapacityUnits();
//        powerSource.getManufactureDate();
    }
}
