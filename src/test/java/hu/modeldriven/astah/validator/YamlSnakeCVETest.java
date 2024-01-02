package hu.modeldriven.astah.validator;

import org.junit.Test;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

public class YamlSnakeCVETest {

    @Test(expected = Exception.class)
    public void testCVE() {

        String cveInput = "some_var: !!javax.script.ScriptEngineManager " +
                "[!!java.net.URLClassLoader [[!!java.net.URL [\"http://localhost:8080\"]]]]";

        Reader reader = new StringReader(cveInput);

        LoaderOptions options = new LoaderOptions();
        options.setTagInspector(tag -> false);
        Yaml yamlParser = new Yaml(options);
        Map<String, Object> yamlData = yamlParser.load(reader);
        System.out.println(yamlData);
    }

}
