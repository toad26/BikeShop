package com.bikeshop.demo.coverter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MultipartConverter implements Converter<MultipartFile, String> {

	@Override
	public String convert(MultipartFile arg0) {
		try {
			return arg0.getOriginalFilename();
		} catch (Exception e) {
			return "";
		}
	}
}
