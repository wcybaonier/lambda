package com.lambda.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 注入乐观锁插件
     *
     * 注意事项：
     * 支持的类型只有：int，Integer,long,Long,Date,Timestamp,LocalDateTime
     * 整数类型下newVerison = oldVersion+1
     * newVersion会写到entity中
     * 仅支持updateById(id)与update(entity,wrapper)方法
     * 在update(entiry,wrapper)方法下，wrapper不能复用
     * @return 1
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * 性能分析插件
     * @return
     */
    @Bean
    // @Profile({"dev,test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 格式化sql输出
        performanceInterceptor.setFormat(true);
        // 设置sql执行最大时间，单位（ms）
        performanceInterceptor.setMaxTime(5000L);

        return performanceInterceptor;
    }
}
