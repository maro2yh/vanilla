package vanilla.os.collector.service.system.info;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SystemInfo {

    private String platform;
    private OSInfo operatingSystem;
    private UserInfo user;
    private NetworkInfo network;
    private List<NetworkInterfaceInfo> networkIFs;
    private ComputerSystemInfo cpu;
    private List<DisplayInfo> displays;
    private List<GraphicsCardInfo> graphicsCards;
    private List<LogicalVolumeGroupInfo> logicalVolumnGroups;
    private GlobalMemoryInfo globalMemory;
    private List<PowerSourceInfo> powerSources;
    private List<HWDiskStoreInfo> diskStores;
    
    public void toPrint() {
        StringBuffer print = new StringBuffer();
        print.append("[===== SYSTEM INFORMATION =====]\n");
        print.append("OS:[").append("\n");
        print.append("\t").append("PLATFORM     : ").append(platform).append("\n");
        print.append("\t").append("NAME         : ").append(operatingSystem.getName()).append("\n");
        print.append("\t").append("VERSION      : ").append(operatingSystem.getVersion()).append("\n");
        print.append("\t").append("BIT          : ").append(operatingSystem.getBit()).append("\n");
        print.append("\t").append("BUILD NUMBER : ").append(operatingSystem.getBuildNumber()).append("\n");
        print.append("\t").append("CODE NAME    : ").append(operatingSystem.getCodeName()).append("\n");
        print.append("\t").append("ARCH         : ").append(operatingSystem.getArch()).append("\n");
        print.append("\t").append("FAMILY       : ").append(operatingSystem.getFamaily()).append("\n");
        print.append("\t").append("MANUFACTURER : ").append(operatingSystem.getManufacturer()).append("\n");
        print.append("\t").append("BOOT TIME    : ").append(operatingSystem.getBootTime()).append("\n");
        print.append("\t").append("UP TIME      : ").append(operatingSystem.getUpTime()).append("\n");
        print.append("]").append("\n");
        
        print.append("USER:[").append("\n");
        print.append("\t").append("HOSTNAME : ").append(user.getHostname()).append("\n");
        print.append("\t").append("NAME     : ").append(user.getName()).append("\n");
        print.append("\t").append("DIR      : ").append(user.getDir()).append("\n");
        print.append("\t").append("HOME     : ").append(user.getHome()).append("\n");
        print.append("\t").append("COUNTRY  : ").append(user.getCountry()).append("\n");
        print.append("\t").append("LANGUAGE : ").append(user.getLanguage()).append("\n");
        print.append("\t").append("TIMEZONE : ").append(user.getTimezone()).append("\n");
        print.append("]").append("\n");
        
        print.append("NETWORK:[").append("\n");
        print.append("\t").append("IP                   : ").append(network.getIp()).append("\n");
        print.append("\t").append("DNS                  : ").append(network.getDns()).append("\n");
        print.append("\t").append("DOMAIN NAME          : ").append(network.getDomainName()).append("\n");
        print.append("\t").append("IPv4 DEFAULT GATEWAY : ").append(network.getIpv4DefaultGateway()).append("\n");
        print.append("\t").append("IPv6 DEFAULT GATEWAY : ").append(network.getIpv6DefaultGateway()).append("\n");
        print.append("]").append("\n");
        
        print.append("NETWORK INTERFACES:[").append("\n");
        
        for (NetworkInterfaceInfo netIF : networkIFs) {
            print.append("\t").append("NAME : ").append(netIF.getName()).append("\n");
            print.append("\t").append("\t").append("INDEX                     : ").append(netIF.getIndex()).append("\n");
            print.append("\t").append("\t").append("DISPLAY NAME              : ").append(netIF.getDisplayName()).append("\n");
            print.append("\t").append("\t").append("ALIAS                     : ").append(netIF.getAlias()).append("\n");
            print.append("\t").append("\t").append("TYPE                      : ").append(netIF.getType()).append("\n");
            print.append("\t").append("\t").append("NDIS PHYSICAL MEDIUM TYPE : ").append(netIF.getNdisPhysicalMediumType()).append("\n");
            print.append("\t").append("\t").append("STATUS                    : ").append(netIF.getStatus()).append("\n");
            print.append("\t").append("\t").append("IPv4 ADDRESS              : ").append(netIF.getIpv4Address()).append("\n");
            print.append("\t").append("\t").append("IPv6 ADDRESS              : ").append(netIF.getIpv6Address()).append("\n");
            print.append("\t").append("\t").append("SUBNET MASK               : ").append(netIF.getSubnetMask()).append("\n");
            print.append("\t").append("\t").append("TIMESTAMP                 : ").append(netIF.getTimestamp()).append("\n");
        }
        
        print.append("]").append("\n");
        
        print.append("COMPUTER SYSTEM:[").append("\n");
        print.append("\t").append("MANUFACTURER  : ").append(cpu.getManufacturer()).append("\n");
        print.append("\t").append("MODEL         : ").append(cpu.getModel()).append("\n");
        print.append("\t").append("SERIAL NUMBER : ").append(cpu.getSerialNumber()).append("\n");
        print.append("\t").append("HARDWARE UUID : ").append(cpu.getHardwareUUID()).append("\n");
        
        print.append("\t").append("BASEBOARD:[").append("\n");
        print.append("\t").append("\t").append("MANUFACTURER  : ").append(cpu.getBaseboard().getManufacturer()).append("\n");
        print.append("\t").append("\t").append("MODEL         : ").append(cpu.getBaseboard().getModel()).append("\n");
        print.append("\t").append("\t").append("VERSION       : ").append(cpu.getBaseboard().getVersion()).append("\n");
        print.append("\t").append("\t").append("SERIAL NUMBER : ").append(cpu.getBaseboard().getSerialNumber()).append("\n");
        print.append("\t").append("]").append("\n");
        
        print.append("\t").append("FIRMWARE:[").append("\n");
        print.append("\t").append("\t").append("MANUFACTURER : ").append(cpu.getFirmware().getManufacturer()).append("\n");
        print.append("\t").append("\t").append("NAME         : ").append(cpu.getFirmware().getName()).append("\n");
        print.append("\t").append("\t").append("VERSION      : ").append(cpu.getFirmware().getVersion()).append("\n");
        print.append("\t").append("\t").append("DESCRIPTION  : ").append(cpu.getFirmware().getDescription()).append("\n");
        print.append("\t").append("\t").append("RELEASE DATE : ").append(cpu.getFirmware().getReleaseDate()).append("\n");
        print.append("\t").append("]").append("\n");
        
        print.append("]").append("\n"); // END COMPUTER SYSTEM
        
        print.append("DISPLAY:[").append("\n");
        
        for (DisplayInfo display : displays) {
            print.append("\t").append("EDID : ").append(display.getEdid()).append("\n");
        }
        
        print.append("]").append("\n"); // END DISPLAY
        
        print.append("GRAPHICS CARDS:[").append("\n");
        
        for (GraphicsCardInfo graphicsCard: graphicsCards) {
            print.append("\t").append("NAME : ").append(graphicsCard.getName()).append("\n");
            print.append("\t").append("\t").append("VENDOR    : ").append(graphicsCard.getVendor()).append("\n");
            print.append("\t").append("\t").append("VERSION   : ").append(graphicsCard.getVersion()).append("\n");
            print.append("\t").append("\t").append("DEVICE ID : ").append(graphicsCard.getDeviceId()).append("\n");
            print.append("\t").append("\t").append("RAM       : ").append(graphicsCard.getRam()).append("\n");
        }
        
        print.append("]").append("\n"); // END GRAPHICS CARDS
        
        print.append("LOGICAL VOLUME GROUPS:[").append("\n");
        
        for (LogicalVolumeGroupInfo logicalVolumnGroup : logicalVolumnGroups) {
            print.append("\t").append("NAME : ").append(logicalVolumnGroup.getGroupName()).append("\n");
            
            Map<String, Set<String>> logicalVolumes = logicalVolumnGroup.getLogicalVolumes();
            Iterator<String> logicalKeys = logicalVolumes.keySet().iterator();
            
            while (logicalKeys.hasNext()) {
                String key = logicalKeys.next();
                Set<String> logicalVolume = logicalVolumes.get(key);
                print.append("\t").append("\t").append("LOGICAL VOLUMES").append("\n");
                print.append("\t").append("\t").append("\t").append(key).append("\n");
                print.append("\t").append("\t").append("\t").append("\t").append(logicalVolume).append("\n");
            }
            
            Set<String> physicalVolumes = logicalVolumnGroup.getPhysicalVolumes();
            Iterator<String> physicals = physicalVolumes.iterator();
            
            while (physicals.hasNext()) {
                print.append("\t").append("\t").append("PHYSICAL VOLUMES").append("\n");
                print.append("\t").append("\t").append("\t").append(physicals.next()).append("\n");
            }
        }
        
        print.append("]").append("\n"); // END LOGICAL VOLUME GROUPS
        
        print.append("GLOBAL MEMORY:[").append("\n");
        print.append("\t").append("TOTAL     : ").append(globalMemory.getTotal()).append("\n");
        print.append("\t").append("AVAILABLE : ").append(globalMemory.getAvailable()).append("\n");
        print.append("\t").append("PAGE SIZE : ").append(globalMemory.getPageSize()).append("\n");
        print.append("\t").append("VIRTUAL MEMORY:[").append("\n");
        print.append("\t").append("\t").append("SWAP TOTAL     : ").append(globalMemory.getVirtualMemory().getSwapTotal()).append("\n");
        print.append("\t").append("\t").append("SWAP PAGES IN  : ").append(globalMemory.getVirtualMemory().getSwapPagesIn()).append("\n");
        print.append("\t").append("\t").append("SWAP PAGES OUT : ").append(globalMemory.getVirtualMemory().getSwapPagesOut()).append("\n");
        print.append("\t").append("\t").append("SWAP USED      : ").append(globalMemory.getVirtualMemory().getSwapUsed()).append("\n");
        print.append("\t").append("\t").append("VIRTUAL MAX    : ").append(globalMemory.getVirtualMemory().getVirtualMax()).append("\n");
        print.append("\t").append("\t").append("VIRTUAL IN USE : ").append(globalMemory.getVirtualMemory().getVirtualInUse()).append("\n");
        print.append("\t").append("]").append("\n"); // END VIRTUAL MEMORY
        
        print.append("\t").append("PHYSICAL MEMORY:[").append("\n");
        
        for (GlobalMemoryInfo.PhysicalMemoryInfo physicalMemory : globalMemory.getPhysicalMemorys()) {
            print.append("\t").append("\t").append("BANK LABEL : ").append(physicalMemory.getBankLabel()).append("\n");
            print.append("\t").append("\t").append("\t").append("MANUFACTURER : ").append(physicalMemory.getManufacturer()).append("\n");
            print.append("\t").append("\t").append("\t").append("MEMORY TYPE  : ").append(physicalMemory.getMemoryType()).append("\n");
            print.append("\t").append("\t").append("\t").append("CAPACITY     : ").append(physicalMemory.getCapacity()).append("\n");
            print.append("\t").append("\t").append("\t").append("CLOCK SPEED  : ").append(physicalMemory.getClockSpeed()).append("\n");
        }
        
        print.append("\t").append("]").append("\n"); // END PHYSICAL MEMORY
        print.append("]").append("\n"); // END GLOBAL MEMORY
        
        print.append("POWER SOURCES:[").append("\n");
        
        for (PowerSourceInfo powerSource : powerSources) {
            print.append("\t").append("NAME : ").append(powerSource.getName()).append("\n");
            print.append("\t").append("\t").append("DEVICE NAME                : ").append(powerSource.getDeviceName()).append("\n");
            print.append("\t").append("\t").append("MANUFACTURER               : ").append(powerSource.getManufacturer()).append("\n");
            print.append("\t").append("\t").append("CHEMISTRY                  : ").append(powerSource.getChemistry()).append("\n");
            print.append("\t").append("\t").append("SERIAL NUMBER              : ").append(powerSource.getSerialNumber()).append("\n");
            print.append("\t").append("\t").append("AMPERAGE                   : ").append(powerSource.getAmperage()).append("\n");
            print.append("\t").append("\t").append("CURRENT CAPACITY           : ").append(powerSource.getCurrentCapacity()).append("\n");
            print.append("\t").append("\t").append("CYCLE COUNT                : ").append(powerSource.getCycleCount()).append("\n");
            print.append("\t").append("\t").append("DESIGN CAPACITY            : ").append(powerSource.getDesignCapacity()).append("\n");
            print.append("\t").append("\t").append("MAX CAPACITY               : ").append(powerSource.getMaxCapacity()).append("\n");
            print.append("\t").append("\t").append("POWER USAGE RATE           : ").append(powerSource.getPowerUsageRate()).append("\n");
            print.append("\t").append("\t").append("REMAINING CAPACITY PERCENT : ").append(powerSource.getRemainingCapacityPercent()).append("\n");
            print.append("\t").append("\t").append("TEMPERATURE                : ").append(powerSource.getTemperature()).append("\n");
            print.append("\t").append("\t").append("TIME REMAINING ESTIMATED   : ").append(powerSource.getTimeRemainingEstimated()).append("\n");
            print.append("\t").append("\t").append("TIME REMAINING INSTANT     : ").append(powerSource.getTimeRemainingInstant()).append("\n");
            print.append("\t").append("\t").append("VOLTAGE                    : ").append(powerSource.getVoltage()).append("\n");
            print.append("\t").append("\t").append("CHARGING                   : ").append(powerSource.isCharging()).append("\n");
            print.append("\t").append("\t").append("DISCHARGING                : ").append(powerSource.isDischarging()).append("\n");
            print.append("\t").append("\t").append("POWER ON LINE              : ").append(powerSource.isPowerOnLine()).append("\n");
            print.append("\t").append("\t").append("UPDATE ATTRIBUTES          : ").append(powerSource.isUpdateAttributes()).append("\n");
        }
        
        print.append("]").append("\n"); // END POWER SOURCES
        
        for (HWDiskStoreInfo diskStore : diskStores) {
            print.append("DISK STORES:[").append("\n");
            print.append("\t").append("NAME : ").append(diskStore.getName()).append("\n");
            print.append("\t").append("\t").append("MODEL                : ").append(diskStore.getModel()).append("\n");
            print.append("\t").append("\t").append("SERIAL               : ").append(diskStore.getSerial()).append("\n");
            print.append("\t").append("\t").append("SIZE                 : ").append(diskStore.getSize()).append("\n");
            print.append("\t").append("\t").append("CURRENT QUEUE LENGTH : ").append(diskStore.getCurrentQueueLength()).append("\n");
            print.append("\t").append("\t").append("TRANSFER TIME        : ").append(diskStore.getTransferTime()).append("\n");
            print.append("\t").append("\t").append("TIME STAMP           : ").append(diskStore.getTimeStamp()).append("\n");
            print.append("\t").append("\t").append("READ BYTES           : ").append(diskStore.getReadBytes()).append("\n");
            print.append("\t").append("\t").append("READS                : ").append(diskStore.getReads()).append("\n");
            print.append("\t").append("\t").append("WRITE BYTES          : ").append(diskStore.getWriteBytes()).append("\n");
            print.append("\t").append("\t").append("WRITES               : ").append(diskStore.getWrites()).append("\n");
            
            for (HWDiskStoreInfo.HWPartitionInfo partition : diskStore.getPartitions()) {
                print.append("\t").append("\t").append("PARTITIONS:[").append("\n");
                print.append("\t").append("\t").append("\t").append("NAME           : ").append(partition.getName()).append("\n");
                print.append("\t").append("\t").append("\t").append("UUID           : ").append(partition.getUuid()).append("\n");
                print.append("\t").append("\t").append("\t").append("TYPE           : ").append(partition.getType()).append("\n");
                print.append("\t").append("\t").append("\t").append("MAJOR          : ").append(partition.getMajor()).append("\n");
                print.append("\t").append("\t").append("\t").append("MINOR          : ").append(partition.getMinor()).append("\n");
                print.append("\t").append("\t").append("\t").append("SIZE           : ").append(partition.getSize()).append("\n");
                print.append("\t").append("\t").append("\t").append("IDENTIFICATION : ").append(partition.getIdentification()).append("\n");
                print.append("\t").append("\t").append("\t").append("MOUNT POINT    : ").append(partition.getMountPoint()).append("\n");
                print.append("\t").append("\t").append("]").append("\n"); // END PARTITIONS
            }
            
            print.append("]").append("\n"); // END DISK STORES
        }
        
        log.info(print.toString());
    }
}
