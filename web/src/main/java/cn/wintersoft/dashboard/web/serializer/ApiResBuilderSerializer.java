package cn.wintersoft.dashboard.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import cn.wintersoft.dashboard.web.model.ApiRes;
import cn.wintersoft.dashboard.web.model.ApiResBuilder;

import java.io.IOException;

@JsonComponent
public class ApiResBuilderSerializer extends JsonSerializer<ApiResBuilder<?>> {
    @Override
    public void serialize(ApiResBuilder apiResBuilder, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        ApiRes<?> o = apiResBuilder.get();
        jsonGenerator.writeObject(o);
    }

}
