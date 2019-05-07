package com.example.demo.common.manager;


import com.example.demo.common.dao.CommonDao;



public class CommonManager {

    private CommonDao commonDao = new CommonDao();

    public CommonManager() {

        Long id = new Long(1);
        commonDao.getEnvironment(id);

    }


}
