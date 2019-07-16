package ${package};

import io.javalin.core.util.FileUtil;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.util.Map;

public class BundledServerSideRenderer implements ServerSideRenderer {

    private final Context context;

    public BundledServerSideRenderer(String filename) {
        Context.newBuilder("js")
                .build();
        Context context = Context.create("js");
        String source = FileUtil.readResource(filename);
        context.eval("js", source);
        this.context = context;
    }

    public String render(String componentName, Map<String, Object> props) {
        Value renderToString = context.eval("js", "global.renderToString");
        Value component = context.eval("js", "global." + componentName);
        return renderToString.execute(component, ProxyObject.fromMap(props)).asString();
    }
}
