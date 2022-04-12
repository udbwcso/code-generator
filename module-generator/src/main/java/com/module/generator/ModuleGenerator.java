package com.module.generator;


import com.common.util.TemplateUtil;
import com.config.Entity;
import com.config.Module;
import com.database.util.EntityGenerator;
import de.hunsicker.jalopy.Jalopy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Map;


@Slf4j
public class ModuleGenerator {

    public static void main(String[] args) throws Exception {
        String yamlPath = "D:\\projects\\code-generator\\module-generator\\src\\main\\resources\\module.yml";
        Yaml yaml = new Yaml();
        Module module = yaml.loadAs(new FileInputStream(yamlPath), Module.class);

        for (Entity entity : module.getEntities()) {
            log.info("entityName:{}", entity.getName());
            Map<String, Object> tableInfo = EntityGenerator.getTableInfo(null, null,
                    entity.getTable(), null, module.getDatabase());
            tableInfo.put("entity", entity.getName());
            tableInfo.put("firstLowerCaseEntity", EntityGenerator.lowercase(entity.getName(), 0, 1));
            tableInfo.put("package", entity.getPackageName());
            tableInfo.put("packagePath", entity.getPackageName().replace(".", "\\"));
            tableInfo.put("module", module.getName());

            String encoding = "UTF-8";

            Collection<File> templateList = FileUtils.listFiles(new File(module.getTemplate()), null, true);
            for (File file : templateList) {
                String templatePath = file.getAbsolutePath();
                String targetPath = templatePath.replace(module.getTemplate(), module.getTarget());
                targetPath = new StrSubstitutor(tableInfo, "${", "}").replace(targetPath);
                log.info("code path:{}", targetPath);
                String code = TemplateUtil.getText(tableInfo, FileUtils.readFileToString(file, encoding));
                if (templatePath.endsWith(".java")) {
                    code = format(code);
                }
                FileUtils.writeStringToFile(new File(targetPath), code, encoding);
            }
        }
    }

    public static String format(String source) {
        Jalopy jalopy = new Jalopy();
        jalopy.setEncoding("UTF-8");
        jalopy.setInput(source, "Temp.java");
        StringBuffer stringBuffer = new StringBuffer();
        jalopy.setOutput(stringBuffer);
        jalopy.format();
        return stringBuffer.toString();
    }


}
