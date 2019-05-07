package com.example.demo.common.utilities;

import com.example.demo.common.MainVerticle;
import com.example.demo.common.controller.CommonController;
import com.example.demo.common.dao.CommonDao;
import com.example.demo.common.manager.CommonManager;
import com.google.inject.AbstractModule;



public class DefineDependencies extends AbstractModule {

    @Override
    public void configure() {

        bind(CommonManager.class).to((Class)CommonDao.class);

        //bind(MainVerticle.class);
        //bind(CommonController.class);

    }


}
