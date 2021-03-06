package github.zhanggang.version1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

@Slf4j
public class DynamicSwitchDBTypeUtil {

    @Value("${spring.datasource.slavecnt}")
    private static int slaveCnt;

    private static final ThreadLocal<DBTypeEnum> CONTEXT_HAND = new ThreadLocal<>();

    public static void set(DBTypeEnum dbTypeEnum) {
        CONTEXT_HAND.set(dbTypeEnum);
        log.info("切换数据源：" + dbTypeEnum);
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
    }

    public static void slave() {
        //随机切换
        if (System.currentTimeMillis() % 2 == 0) {
            set(DBTypeEnum.SLAVE1);
        } else {
            set(DBTypeEnum.SLAVE2);
        }

    }

    public static DBTypeEnum get() {
        return CONTEXT_HAND.get();
    }
}
