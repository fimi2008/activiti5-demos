package com.lionxxw.activiti.pd;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * <p>Description: 流程定义 </p>
 * 1.部署
 * 2.查询部署
 * 3.查看图片
 * 4.删除部署
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/3 下午10:38
 */
public class PDTest {

    /**
     * 通过classpath路径进行部署
     *  涉及到的表
     *      act_ge_bytearray
     *          1.英文解释
     *              act:activiti
     *              ge:general
     *              bytearray:二进制
     *          2.字段
     *              name:文件的路径+名称
     *              bytes_:存放内容
     *              deployment_id:部署id
     *          3.说明
     *              如果要查询文件(bpmn和png),需要知道deloymentId
     *      act_re_deployment
     *          1.解释
     *              re:repository:资源
     *              deployment:部署 用于描述一次部署
     *          2.字段
     *              id_:部署id 主键
     *      act_re_procdef
     *          1.解释
     *              procdef:process definition 流程定义
     *          2.字段
     *              id_:pdid 由pdkey:pdversion:随机数 组成
     *              name:名称
     *              key:名称
     *              version:版本号
     *                  如果名称不变,每次部署,版本号加1
     *                  如果名称改变,则版本号从1开始计算
     *              deployment_id:部署id
     *
     */
    @Test
    public void testDeployFromClasspath() {
        // 得到流程引擎
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService()
                .createDeployment()
                .addClasspathResource("qingjia.bpmn")
                .addClasspathResource("qingjia.png")
                .deploy();
    }

    /**
     * 通过inputstream完成部署
     */
    @Test
    public void testDeployFromInputStream(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("qingjia.bpmn");
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().createDeployment().addInputStream("qingjia.bpmn", inputStream)
        .deploy();
    }

    /**
     * 通过zipinputstream完成部署
     */
    @Test
    public void testDeployFromZipinputStream(){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("qingjia.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService()
                .createDeployment()
                .addZipInputStream(zipInputStream).deploy();
    }

    /**
     * 删除流程
     */
    @Test
    public void testDelete(){
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
        pe.getRepositoryService().deleteDeployment("1", true);
    }
}
