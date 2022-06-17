package vanilla.os.collector.service.system.info;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;

@Data
public class HWDiskStoreInfo {

    private String name;
    private String model;
    private String serial;
    private long size;
    private long currentQueueLength;
    private long transferTime;
    private long timeStamp;
    private long readBytes;
    private long reads;
    private long writeBytes;
    private long writes;
    private List<HWPartitionInfo> partitions = new ArrayList<HWDiskStoreInfo.HWPartitionInfo>();

    public HWDiskStoreInfo() {
    }

    public HWDiskStoreInfo(HWDiskStore diskStore) {
        this.name = diskStore.getName();
        this.model = diskStore.getModel();
        this.serial = diskStore.getSerial();
        this.size = diskStore.getSize();
        this.currentQueueLength = diskStore.getCurrentQueueLength();
        this.transferTime = diskStore.getTransferTime();
        this.timeStamp = diskStore.getTimeStamp();
        this.readBytes = diskStore.getReadBytes();
        this.reads = diskStore.getReads();
        this.writeBytes = diskStore.getWriteBytes();
        this.writes = diskStore.getWrites();
        
        for (HWPartition hwPartition : diskStore.getPartitions()) {
            partitions.add(new HWPartitionInfo(hwPartition));
        }
    }

    @Data
    class HWPartitionInfo {
        
        private String name;
        private String uuid;
        private String type;
        private int major;
        private int minor;
        private long size;
        private String identification;
        private String mountPoint;

        public HWPartitionInfo() {
        }
        
        public HWPartitionInfo(HWPartition hwPartition) {
            this.name = hwPartition.getName();
            this.uuid = hwPartition.getUuid();
            this.type = hwPartition.getType();
            this.major = hwPartition.getMajor();
            this.minor = hwPartition.getMinor();
            this.size = hwPartition.getSize();
            this.identification = hwPartition.getIdentification();
            this.mountPoint = hwPartition.getMountPoint();
        }
    }
}
