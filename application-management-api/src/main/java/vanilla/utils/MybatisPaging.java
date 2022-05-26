package vanilla.utils;

import lombok.Data;

@Data
public class MybatisPaging {

    private final static int SIZE = 20;
    private int page;
    private int size;
    private int start;
    private int end;
    
    public MybatisPaging() {
        this(1, SIZE);
    }
    
    public MybatisPaging(int page) {
        this(page, SIZE);
    }
    
    public MybatisPaging(int page, int size) {
        this.page = page;
        this.size = size;
        this.start = ((this.page - 1) * this.size) + 1;
        this.end = this.page * this.size;
    }
}
