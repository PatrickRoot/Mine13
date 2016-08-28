/* 
 ************************************************************************
 * sixlab.eu.org 2015/10/19 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ************************************************************************
 */
package cn.sixlab.sixrecords.module.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/10/19
 * 功能描述：
 * 版本：1.0-snapshot
 */
@Transactional
@Service
public class DaoService{
    private static Logger logger = LoggerFactory.getLogger(DaoService.class);
    
    @Autowired
    private DaoBusiness daoBusiness;

    public void add(String className, String data) {
        logger.info(">>>>>Enter service method--");

    }
}
