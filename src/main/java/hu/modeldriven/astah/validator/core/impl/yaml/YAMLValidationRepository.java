package hu.modeldriven.astah.validator.core.impl.yaml;

import hu.modeldriven.astah.validator.core.impl.script.DefaultScriptExecutorRepository;
import hu.modeldriven.astah.validator.core.impl.script.ScriptExecutorRepository;
import hu.modeldriven.astah.validator.core.ValidationRepository;
import hu.modeldriven.astah.validator.core.ValidationSuite;
import hu.modeldriven.astah.validator.core.impl.CachedValidationSuite;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YAMLValidationRepository implements ValidationRepository {

    private static final String SUITES_KEY = "suites";

    private final File file;

    public YAMLValidationRepository(File file) {
        this.file = file;
    }

    @Override
    public List<ValidationSuite> validationSuites() {

        ScriptExecutorRepository scriptRepository = new DefaultScriptExecutorRepository();

        try (Reader reader = new FileReader(this.file)) {
            Yaml yamlParser = new Yaml();
            Map<String, Object> yamlData = yamlParser.load(reader);

            List<ValidationSuite> result = new ArrayList<>();

            if (yamlData.containsKey(SUITES_KEY) && yamlData.get(SUITES_KEY) instanceof List) {
                for (Map<String, Object> suite : (List<Map<String, Object>>) yamlData.get(SUITES_KEY)) {
                    result.add(new CachedValidationSuite(new YAMLValidationSuite(suite, scriptRepository)));
                }
            }

            return result;
        } catch (Exception ex) {
            throw new YAMLReadFailedException(ex);
        }
    }

}
