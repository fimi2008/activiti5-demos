package com.lionxxw.activiti.db;

import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * <p>Description: 建表Test</p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/3 下午8:38
 */
public class TableTest {
    @Test
    public void testCreateTable(){
        ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
    }
}
