package ${package};

import java.util.Map;

public interface ServerSideRenderer {
    String render(String componentName, Map<String, Object> props);
}
