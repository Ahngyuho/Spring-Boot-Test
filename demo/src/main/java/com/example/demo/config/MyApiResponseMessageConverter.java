package com.example.demo.config;

import com.example.demo.core.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyApiResponseMessageConverter extends AbstractHttpMessageConverter<ApiResponse<?>> {

    private final ObjectMapper objectMapper;

    public MyApiResponseMessageConverter(ObjectMapper objectMapper) {
        super(MediaType.APPLICATION_JSON); // 지원 미디어 타입 명시
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return ApiResponse.class.isAssignableFrom(clazz);
    }

    // 읽기는 비활성화 (write-only)
    @Override
    public boolean canRead(Class<?> clazz, @org.springframework.lang.Nullable MediaType mediaType) {
        return false;
    }

    @Override
    protected ApiResponse<?> readInternal(Class<? extends ApiResponse<?>> clazz, HttpInputMessage inputMessage) {
        throw new UnsupportedOperationException("write-only");
    }

    @Override
    protected void writeInternal(ApiResponse<?> body, HttpOutputMessage outputMessage) throws IOException {
        log.info("MyApiResponseMessageConverter.writeInternal()");
        objectMapper.writeValue(outputMessage.getBody(), body); // 이중 문자열화 금지
    }
}
