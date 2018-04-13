import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;

public class ParamNamesTest {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    public static class Pojo {
        private final String firstAttribute;

        private final Long secondAttribute;

        public Pojo(String firstAttribute,
                    Long secondAttribute) {
            this.firstAttribute = "not-from-jackson";
            this.secondAttribute = secondAttribute;
        }

        public String getFirstAttribute() {
            return firstAttribute;
        }

        public Long getSecondAttribute() {
            return secondAttribute;
        }

        @Override
        public String toString() {
            return "Collaboration{" +
                    "firstAttribute='" + firstAttribute + '\'' +
                    ", secondAttribute=" + secondAttribute +
                    '}';
        }
    }

    @Test
    public void jacksonUsesConstructorOnly() throws IOException {
        String str = "{\"first_attribute\":\"alice\", \"second_attribute\":1267518}";
        Pojo pojo = mapper.readValue(str, Pojo.class);

        System.out.println(pojo);
        Assert.assertThat(pojo.getFirstAttribute(), equalTo("not-from-jackson"));
    }
}
