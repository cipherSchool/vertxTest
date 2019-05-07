package com.example.demo.common.controller;


import com.example.demo.common.manager.CommonManager;
import com.example.demo.common.utilities.DefineDependencies;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

public class CommonController {

    FreeMarkerTemplateEngine templateEngine;
    private CommonManager commonManager = new CommonManager();


    public void defineRoutes(Router router, FreeMarkerTemplateEngine templateEngine) {

        this.templateEngine = templateEngine;

        router.get("/").handler(this::getIndex);
        router.route("/static/*").handler(StaticHandler.create());

        router.get("/logon").handler(this::getLogon);
        //router.get("/s/*").handler(this::getSecure);

        //AuthProvider authProvider =
        //router.route("/private/*").handler(RedirectAuthHandler.create(authProvider, "/common/login.ftl"));
        //router.route("/private/*").handler(StaticHandler.create().setCachingEnabled(false).setWebRoot("private"));
        //router.route("/loginhandler").handler(FormLoginHandler.create(authProvider));

        //router.put("/products/:productID").handler(this::handleAddProduct);
        //router.get("/products").handler(this::handleListProducts);
    }


    public void getIndex(RoutingContext routingContext) {
        Map args = new HashMap<String, SortedSet<String>>();
        routingContext.response().putHeader("Content-Type", "text/html");
        routingContext.put("title", "test title");
        this.renderRoute(routingContext, args, "views", "common/index", "ftl");
    }

    public void getLogon(RoutingContext routingContext) {
        Map args = new HashMap<String, SortedSet<String>>();
        routingContext.response().putHeader("Content-Type", "text/html");
        this.renderRoute(routingContext, args, "views", "common/logon", "ftl");
    }

    public void renderRoute(RoutingContext routingContext, Map args, String path, String file, String type) {
        if ("ftl".equalsIgnoreCase(type)) {
            String loc = "/"+path+"/"+file+"."+type;
            templateEngine.render(args, loc, ar -> {
                if (ar.succeeded()) {
                    routingContext.response().end(ar.result());
                } else {
                    //write to error log
                    routingContext.fail(ar.cause());
                }
            });
        }
    }



}




