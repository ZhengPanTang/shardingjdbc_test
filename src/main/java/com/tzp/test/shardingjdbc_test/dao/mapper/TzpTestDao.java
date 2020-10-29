package com.tzp.test.shardingjdbc_test.dao.mapper;

import com.tzp.test.shardingjdbc_test.dao.po.TzpTest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tzpTestDao")
public interface TzpTestDao {
    List<TzpTest> selectListByCondition(TzpTest condition);
    int selectCountByConditon(TzpTest condition);
    int batchInsertEntry(List<TzpTest> list);
}
