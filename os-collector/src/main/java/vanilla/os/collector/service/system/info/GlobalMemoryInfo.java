package vanilla.os.collector.service.system.info;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class GlobalMemoryInfo {

    @NonNull
    private Long total;
    
    @NonNull
    private Long available;
    
    @NonNull
    private Long pageSize;
    
    
    private VirtualMemoryInfo virtualMemory;
    private List<PhysicalMemoryInfo> physicalMemorys = new ArrayList<GlobalMemoryInfo.PhysicalMemoryInfo>();
    
    public void setVirtualMemoty(long swapTotal, long swapPagesIn, long swapPagesOut, long swapUsed, long virtualMax, long virtualInUse) {
        virtualMemory = new VirtualMemoryInfo(swapTotal, swapPagesIn, swapPagesOut, swapUsed, virtualMax, virtualInUse);
    }
    
    public void addPhysicalMemory(String bankLabel, String manufacturer, String memoryType, long capacity, long clockSpeed) {
        physicalMemorys.add(new PhysicalMemoryInfo(bankLabel, manufacturer, memoryType, capacity, clockSpeed));
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class VirtualMemoryInfo {
        private long swapTotal;
        private long swapPagesIn;
        private long swapPagesOut;
        private long swapUsed;
        private long virtualMax;
        private long virtualInUse;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class PhysicalMemoryInfo {
        private String bankLabel;
        private String manufacturer;
        private String memoryType;
        private long capacity;
        private long clockSpeed;
    }
}
