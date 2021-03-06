package github.zhanggang.version1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Random;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Value("${spring.datasource.slavecnt}")
    private int slaveCnt;

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicSwitchDBTypeUtil.get();
//        if (dbTypeEnum == DBTypeEnum.MASTER) {
//            return dbTypeEnum;
//        }
//        Random random = new Random(System.currentTimeMillis());
//        int i = random.nextInt(slaveCnt);
//        return dbTypeEnum + Integer.toString(i);
    }
}
