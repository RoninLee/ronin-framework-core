package com.ronin.base.util.text;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SensitiveSerializer extends JsonSerializer<String> implements ContextualSerializer {

	private Sensitive sensitive;

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
			throws JsonMappingException {
		if (property != null) {
			if (Objects.equals(property.getType().getRawClass(), String.class)) {
				Sensitive sensitive = property.getAnnotation(Sensitive.class);
				if (sensitive == null) {
					sensitive = property.getContextAnnotation(Sensitive.class);
				}
				if (sensitive != null) {
					return new SensitiveSerializer(sensitive);
				}
			}
			return prov.findValueSerializer(property.getType(), property);
		}
		return prov.findNullValueSerializer(property);
	}

	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		Class<? extends Sensitivable> sensitivableType = sensitive.provider();
		Sensitivable sensitivable;
		if (sensitivableType == Sensitivable.class) {
			sensitivable = new Sensitivable() {
			};
		} else {
			try {
				sensitivable = sensitivableType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				sensitivable = new Sensitivable() {
				};// 降级处理
				log.error("实例化对象错误", e);
			}
		}
		int end = sensitive.end();
		if (end == -1) {
			end = value.length();
		}
		gen.writeString(sensitivable.apply(value, sensitive.start(), end, sensitive.replacement()));
	}

}
