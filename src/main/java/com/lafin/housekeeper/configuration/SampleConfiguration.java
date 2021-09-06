package com.lafin.housekeeper.configuration;

import com.lafin.housekeeper.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SampleConfiguration {

    private final SampleService sampleService;

}
