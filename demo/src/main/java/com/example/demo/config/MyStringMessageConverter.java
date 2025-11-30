package com.example.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyStringMessageConverter extends AbstractHttpMessageConverter<String> {
    private final ObjectMapper objectMapper;
    public MyStringMessageConverter(ObjectMapper objectMapper) {
        super(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN);
        this.objectMapper = objectMapper;
    }
    @Override
    public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
        super.setSupportedMediaTypes(supportedMediaTypes);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return super.getSupportedMediaTypes();
    }

    @Override
    public void setDefaultCharset(Charset defaultCharset) {
        super.setDefaultCharset(defaultCharset);
    }

    @Override
    public Charset getDefaultCharset() {
        return super.getDefaultCharset();
    }

    @Override
    public boolean canRead(Class<?> clazz, @Nullable MediaType mediaType) {
        log.info("canRead({}, {})", clazz, mediaType);
        return clazz == String.class && (mediaType == null
                || mediaType.isCompatibleWith(MediaType.TEXT_PLAIN)
                || mediaType.isCompatibleWith(MediaType.APPLICATION_JSON)); // JSON 허용
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return super.canWrite(clazz, mediaType);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        log.info("1#Execute AbstractHttpMessageConverter - supports");
        System.out.println(clazz.equals(String.class));
        return clazz.equals(String.class);
    }

    @Override
    protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage)
            throws IOException {
        log.info("내가 처음 만든 internal");
        return StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
    }

    // 메시지 컨버터가 RequestBody , RequestParam 에 있는 데이터를 읽어오거나,
    // ResponseBody 에도 사용이 되나...

    @Override
    protected void writeInternal(String resultMessage, HttpOutputMessage outputMessage)
            throws IOException {
        log.info(">>> MY writeInternal()");
        // String을 JSON으로 내보내고 싶다면 이렇게(이중 인코딩 주의)
        objectMapper.writeValue(outputMessage.getBody(), resultMessage);
    }
}
