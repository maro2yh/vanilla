package vanilla.application.management.api.handler.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestParam {

    private String name;
    private Object value;
    private boolean required;
    private String defaultValue;
}
