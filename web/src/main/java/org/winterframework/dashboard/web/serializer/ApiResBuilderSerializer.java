package org.winterframework.dashboard.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.winterframework.dashboard.web.model.ApiRes;
import org.winterframework.dashboard.web.model.ApiResBuilder;

import java.io.IOException;

public class ApiResBuilderSerializer extends StdSerializer<ApiResBuilder<?>> {

    protected ApiResBuilderSerializer(Class<ApiResBuilder<?>> t) {
        super(t);
    }

    @Override
    public void serialize(ApiResBuilder<?> apiResBuilder, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        ApiRes<?> apiRes = apiResBuilder.get();
        jsonGenerator.writeObject(apiRes);
        jsonGenerator.writeEndObject();
    }
}
