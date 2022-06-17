package vanilla.stocks.ebestapi.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RES DESCRIPTION value object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResFileData {

    private String description;
    private String column;
    private String type;
    private String length;

    public ResFileData set(String[] datas) {
        this.description = datas[0];
        this.column = datas[1];
        this.type = datas[3];
        this.length = datas[4].replaceAll(";", "");
        return this;
    }
}
