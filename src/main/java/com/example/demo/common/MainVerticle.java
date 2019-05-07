package com.example.demo.common;


import com.example.demo.common.controller.CommonController;
import com.example.demo.common.utilities.DefineDependencies;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;



public class MainVerticle extends AbstractVerticle {

    private CommonController commonController;


    @Override
    public void start() {

        Injector injector = Guice.createInjector(new DefineDependencies());
        commonController = injector.getInstance(CommonController.class);

        Router router = Router.router(vertx);
        router.route("/static/*").handler(StaticHandler.create());
        router.route().handler(CookieHandler.create());
        router.route().handler(BodyHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        final FreeMarkerTemplateEngine templateEngine = FreeMarkerTemplateEngine.create(vertx);
        commonController.defineRoutes(router, templateEngine);

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }

}
