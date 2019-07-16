package ${package};

import io.javalin.core.util.FileUtil;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.util.Map;

public class HotServerSideRenderer implements ServerSideRenderer {

    private final String filename;

    public HotServerSideRenderer(String filename) {
        this.filename = filename;
    }

    @Override
    public String render(String componentName, Map<String, Object> props) {
        Context context = refreshContext();
        Value renderToString = context.eval("js", "global.renderToString");
        Value component = context.eval("js", "global." + componentName);
        return renderToString.execute(component, ProxyObject.fromMap(props)).asString();
    }

    private Context refreshContext() {
        Context.newBuilder("js")
                .build();
        Context context = Context.create("js");
        String source = FileUtil.readFile(filename);
        context.eval("js", source);
        return context;
    }
}
