package com.anhn.hotel_reservation_system.config;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //Not null or empty in source
        Condition<?, ?> notNullOrEmpty = new Condition<Object, Object>() {
            @Override
            public boolean applies(MappingContext<Object, Object> mappingContext) {
                return mappingContext.getSource() != null && (!(mappingContext.getSource() instanceof String) || !((String) mappingContext.getSource()).isEmpty());
            }
        };
        modelMapper.getConfiguration().setPropertyCondition(notNullOrEmpty);
        return modelMapper;
    }
}
