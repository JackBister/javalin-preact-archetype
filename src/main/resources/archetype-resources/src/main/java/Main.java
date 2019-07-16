package ${package};

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;

import java.util.Arrays;
import java.util.Collections;

import static io.javalin.plugin.rendering.template.TemplateUtil.model;

public class Main {

    private static boolean isHot = false;
    private static String frontendPath = "/frontend";
    private static Location location = Location.CLASSPATH;

    private static ServerSideRenderer serverSideRenderer;

    public static void main(String[] args) {
        if (Arrays.asList(args).contains("--hot")) {
            isHot = true;
            frontendPath = "./src/main/resources/frontend";
            location = Location.EXTERNAL;
            serverSideRenderer = new HotServerSideRenderer(frontendPath + "/server.js");
        } else {
            serverSideRenderer = new BundledServerSideRenderer(frontendPath + "/server.js");
        }

        JavalinRenderer.register(JavalinThymeleaf.INSTANCE, ".html");

        Javalin app = Javalin.create(config -> {
            config.addSinglePageRoot("/", frontendPath + "/index.html", location);
            config.addStaticFiles(frontendPath, location);
        }).start(29999);

        app.get("/", context -> {
            String renderResult = serverSideRenderer.render("HomeComponent", Collections.singletonMap("name", "Server"));
            // TODO: not sure how to make the HTML hot when SSRing since JavalinThymeleafRenderer takes a FilePath rather than string and no Location argument
            context.render("/frontend/index.html", model("appContent", renderResult));
        });
    }
}
