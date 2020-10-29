package com.tzp.test.shardingjdbc_test.dao.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class BasePo implements Serializable {
    private static final long serialVersionUID = 4153680828573374192L;
    private transient Integer startIndex;//当期也开始需要
    private transient Integer endIndex;//当前页结束序号
    private transient Integer currentPage;
    private transient  Integer pageSize;
    private transient Map<String, Object> queryData = new HashMap<>();

    public BasePo(){
        setCurrentPage(1);
        setPageSize(10);
    }

    public BasePo(boolean pagination){
        this();
        if (!pagination){
            this.startIndex = null;
            this.endIndex = null;
        }
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage= currentPage;
        if (pageSize == null) return;
        startIndex=(currentPage-1)*pageSize;
        endIndex=currentPage*pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (currentPage == null) return;
        startIndex=(currentPage-1)*pageSize;
        endIndex=currentPage*pageSize;
    }

    public void addQueryData(String key, Object value) {
        if (this.queryData == null) {
            this.queryData = new HashMap<>();
        }
        this.queryData.put(key, value);
    }

}
