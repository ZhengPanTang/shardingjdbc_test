package com.tzp.test.shardingjdbc_test.dao.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class TzpTestPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String shardingKeyValue = shardingValue.getValue();
        String logicTableName = shardingValue.getLogicTableName();
        if (shardingKeyValue.length() < 3){
            return logicTableName + "_0";
        }else {
            String shardingKeyActualValue = shardingKeyValue.substring(shardingKeyValue.length()-3,shardingKeyValue.length()-1);
            Integer shardingKeyActualIntValue = Integer.parseInt(shardingKeyActualValue);
            int moduloBy2 = shardingKeyActualIntValue % 2;
            return logicTableName + '_' + moduloBy2;
        }
    }
}
