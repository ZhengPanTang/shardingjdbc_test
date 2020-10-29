package com.tzp.test.shardingjdbc_test.dao.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TzpTest extends BasePo implements java.io.Serializable{
    private static final long serialVersionUID = 7093935232512612938L;

    private Long id;
    private String phone;
    private String name;
}
