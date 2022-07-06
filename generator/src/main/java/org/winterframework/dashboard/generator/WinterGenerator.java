package org.winterframework.dashboard.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class WinterGenerator {
    public static void main(String[] args) {
        String moduleName = "user";
        String[] tables = new String[]{
                "user"
        };

        String url =
                "jdbc:mysql://localhost:3306/winter-dashboard?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "123456";
        FastAutoGenerator.create(url, username, password)
                         .globalConfig(builder -> {
                             builder.author("Kyun") // 设置作者
                                    .enableSwagger() // 开启 swagger 模式
                                    .outputDir("C:\\temp"); // 指定输出目录
                         })
                         .packageConfig(builder -> {
                             builder.parent("org.winterframework.ankenail.api") // 设置父包名
                                    .moduleName(moduleName) // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml,
                                            "C:\\temp\\mapper\\" + moduleName)); // 设置mapperXml生成路径
                         })
                         .strategyConfig(builder -> {
                             builder.addInclude(tables) // 设置需要生成的表名
                                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀

                             builder.entityBuilder().logicDeleteColumnName("deleted")
                                    .addTableFills(new Column("create_time", FieldFill.INSERT))
                                    .addTableFills(new Column("create_by", FieldFill.INSERT))
                                    .addTableFills(new Column("update_time", FieldFill.UPDATE))
                                    .addTableFills(new Column("update_by", FieldFill.UPDATE))
                                    .disableSerialVersionUID()
                                    .enableLombok();

                             builder.serviceBuilder().formatServiceFileName("%sService");

                             builder.controllerBuilder().enableRestStyle();

                         })
                         .templateConfig(builder -> {
                             builder.disable(TemplateType.SERVICEIMPL)
                                    .entity("/templates/entity.java")
                                    .service("/templates/service.java")
                                    .mapper("/templates/mapper.java")
                                    .xml("/templates/mapper.xml")
                                    .controller("/templates/controller.java")
                             ;
                         })
                         .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                         .execute();

    }
}
