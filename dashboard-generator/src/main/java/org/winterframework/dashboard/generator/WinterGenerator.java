package org.winterframework.dashboard.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class WinterGenerator {
    public static void main(String[] args) {
        String url =
                "jdbc:mysql://localhost:3306/winter-dashboard?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username = "root";
        String password = "123456";
        FastAutoGenerator.create(url, username, password)
                         .globalConfig(builder -> {
                             builder.author("Kyun") // 设置作者
                                    .enableSwagger() // 开启 swagger 模式
                                    .outputDir("C:\\temp"); // 指定输出目录

                         })
                         .packageConfig(builder -> {
                             builder.parent("org.winterframework.dashboard") // 设置父包名
                                    .moduleName("base") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml,
                                            "C:\\temp\\xml")); // 设置mapperXml生成路径
                         })
                         .strategyConfig(builder -> {
                             builder.addInclude("user") // 设置需要生成的表名
                                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀

                             builder.entityBuilder().logicDeleteColumnName("deleted")
                                    .addTableFills(new Column("create_time", FieldFill.INSERT))
                                    .enableLombok()
                             ;
                         })
                         .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                         .execute();

    }
}
