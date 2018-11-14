package com.bikeshop.demo.coverter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DoubleConverter implements Converter<String, Double> {

	@Override
	public Double convert(String arg0) {
		try {
			return Double.parseDouble(arg0);
		} catch (Exception e) {
			return 0.0;
		}
	}
}
