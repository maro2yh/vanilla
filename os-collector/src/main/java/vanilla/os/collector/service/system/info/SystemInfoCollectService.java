package vanilla.os.collector.service.system.info;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.stereotype.Service;

import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Display;
import oshi.hardware.Firmware;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.LogicalVolumeGroup;
import oshi.hardware.NetworkIF;
import oshi.hardware.PhysicalMemory;
import oshi.hardware.PowerSource;
import oshi.software.os.NetworkParams;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.OSVersionInfo;

@Service
public class SystemInfoCollectService {

    public SystemInfo getSystem() {
        oshi.SystemInfo si = new oshi.SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        OSVersionInfo osVersion = os.getVersionInfo();
        NetworkParams netParams = os.getNetworkParams();
        HardwareAbstractionLayer hardware = si.getHardware();

        OSInfo osInfo = new OSInfo();
        osInfo.setName(SystemUtils.OS_NAME);
        osInfo.setVersion(SystemUtils.OS_VERSION);
        osInfo.setBit(os.getBitness());
        osInfo.setBuildNumber(osVersion.getBuildNumber());
        osInfo.setCodeName(osVersion.getCodeName());
        osInfo.setArch(SystemUtils.OS_ARCH);
        osInfo.setFamaily(os.getFamily());
        osInfo.setManufacturer(os.getManufacturer());
        osInfo.setBootTime(os.getSystemBootTime());
        osInfo.setUpTime(os.getSystemUptime());

        UserInfo user = new UserInfo();
        user.setHostname(SystemUtils.getHostName());
        user.setName(SystemUtils.USER_NAME);
        user.setDir(SystemUtils.USER_DIR);
        user.setHome(SystemUtils.USER_HOME);
        user.setCountry(SystemUtils.USER_COUNTRY);
        user.setLanguage(SystemUtils.USER_LANGUAGE);
        user.setTimezone(SystemUtils.USER_TIMEZONE);

        NetworkInfo network = new NetworkInfo();
        InetAddress inetAddress = null;
        String ip = "";

        try {
            inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            ip = "Unknown";
        }

        network.setIp(ip);
        network.setDns(Arrays.toString(netParams.getDnsServers()));
        network.setDomainName(netParams.getDomainName());
        network.setIpv4DefaultGateway(netParams.getIpv4DefaultGateway());
        network.setIpv6DefaultGateway(netParams.getIpv6DefaultGateway());

        List<NetworkInterfaceInfo> netInterfaceList = new ArrayList<NetworkInterfaceInfo>();

        for (NetworkIF netIF : hardware.getNetworkIFs()) {
            NetworkInterfaceInfo netInterface = new NetworkInterfaceInfo();
            netInterface.setIndex(netIF.getIndex());
            ;
            netInterface.setName(netIF.getName());
            netInterface.setDisplayName(netIF.getDisplayName());
            netInterface.setAlias(netIF.getIfAlias());
            netInterface.setType(netIF.getIfType());
            netInterface.setNdisPhysicalMediumType(netIF.getNdisPhysicalMediumType());
            netInterface.setCollisions(netIF.getCollisions());
            netInterface.setStatus(netIF.getIfOperStatus().name());
            netInterface.setIpv4Address(Arrays.toString(netIF.getIPv4addr()));
            netInterface.setIpv6Address(Arrays.toString(netIF.getIPv6addr()));
            netInterface.setMacAddress(netIF.getMacaddr());
            netInterface.setSubnetMask(Arrays.toString(netIF.getSubnetMasks()));
            netInterface.setTimestamp(netIF.getTimeStamp());

            netInterfaceList.add(netInterface);
        }

        ComputerSystem cs = hardware.getComputerSystem();
        ComputerSystemInfo cpu = new ComputerSystemInfo();
        cpu.setManufacturer(cs.getManufacturer());
        cpu.setModel(cs.getModel());
        cpu.setSerialNumber(cs.getSerialNumber());
        cpu.setHardwareUUID(cs.getHardwareUUID());

        Baseboard bb = cs.getBaseboard();
        cpu.createBaseboard(bb.getManufacturer(), bb.getModel(), bb.getVersion(), bb.getSerialNumber());

        Firmware firmware = cs.getFirmware();
        cpu.createFirmware(firmware.getManufacturer(), firmware.getName(), firmware.getVersion(), firmware.getDescription(), firmware.getReleaseDate());

        List<DisplayInfo> displays = new ArrayList<DisplayInfo>();

        for (Display display : hardware.getDisplays()) {
            displays.add(new DisplayInfo(display.getEdid()));
        }

        List<GraphicsCardInfo> graphicsCards = new ArrayList<GraphicsCardInfo>();

        for (GraphicsCard graphicsCard : hardware.getGraphicsCards()) {
            graphicsCards.add(new GraphicsCardInfo(graphicsCard.getDeviceId(), graphicsCard.getVendor(), graphicsCard.getName(), graphicsCard.getVendor(), graphicsCard.getVRam()));
        }

        List<LogicalVolumeGroupInfo> logicalVolumnGroups = new ArrayList<LogicalVolumeGroupInfo>();

        for (LogicalVolumeGroup logicalVolumnGroup : hardware.getLogicalVolumeGroups()) {
            logicalVolumnGroups.add(new LogicalVolumeGroupInfo(logicalVolumnGroup.getName(), logicalVolumnGroup.getLogicalVolumes(), logicalVolumnGroup.getPhysicalVolumes()));
        }

        GlobalMemoryInfo globalMemory = new GlobalMemoryInfo(hardware.getMemory().getTotal(), hardware.getMemory().getAvailable(), hardware.getMemory().getPageSize());
        globalMemory.setVirtualMemoty(hardware.getMemory().getVirtualMemory().getSwapTotal(), hardware.getMemory().getVirtualMemory().getSwapPagesIn(),
                hardware.getMemory().getVirtualMemory().getSwapPagesOut(), hardware.getMemory().getVirtualMemory().getSwapUsed(),
                hardware.getMemory().getVirtualMemory().getVirtualMax(), hardware.getMemory().getVirtualMemory().getVirtualInUse());

        for (PhysicalMemory physicalMemory : hardware.getMemory().getPhysicalMemory()) {
            globalMemory.addPhysicalMemory(physicalMemory.getBankLabel(), physicalMemory.getManufacturer(), physicalMemory.getMemoryType(),  physicalMemory.getCapacity(),
                    physicalMemory.getClockSpeed());
        }

        List<PowerSourceInfo> powerSources = new ArrayList<PowerSourceInfo>();
        
        for (PowerSource powerSource : hardware.getPowerSources()) {
            powerSources.add(new PowerSourceInfo(powerSource));
        }
        
        List<HWDiskStoreInfo> diskStores = new ArrayList<HWDiskStoreInfo>();
        
        for (HWDiskStore diskStore : hardware.getDiskStores()) {
            diskStores.add(new HWDiskStoreInfo(diskStore));
        }

        SystemInfo system = new SystemInfo();
        system.setPlatform(oshi.SystemInfo.getCurrentPlatform().getName());
        system.setOperatingSystem(osInfo);
        system.setUser(user);
        system.setNetwork(network);
        system.setNetworkIFs(netInterfaceList);
        system.setCpu(cpu);
        system.setDisplays(displays);
        system.setGraphicsCards(graphicsCards);
        system.setLogicalVolumnGroups(logicalVolumnGroups);
        system.setGlobalMemory(globalMemory);
        system.setPowerSources(powerSources);
        system.setDiskStores(diskStores);

        return system;
    }
}
