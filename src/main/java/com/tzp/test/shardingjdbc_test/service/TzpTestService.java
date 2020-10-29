package com.tzp.test.shardingjdbc_test.service;

import com.google.common.collect.Lists;
import com.tzp.test.shardingjdbc_test.dao.mapper.TzpTestDao;
import com.tzp.test.shardingjdbc_test.dao.po.TzpTest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("tzpTestService")
public class TzpTestService {
    @Resource
    private TzpTestDao tzpTestDao;

    public int addTzpTest(){
        TzpTest insertPo = new TzpTest();
        insertPo.setId(1L);
        insertPo.setPhone("15800000010");
        insertPo.setName("tzp");
        return tzpTestDao.batchInsertEntry(Lists.newArrayList(insertPo));
    }
}
