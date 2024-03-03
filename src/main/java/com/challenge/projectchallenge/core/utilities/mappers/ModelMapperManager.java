package com.challenge.projectchallenge.core.utilities.mappers;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService {

    private ModelMapper modelMapper;

    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PUBLIC)
                .setMatchingStrategy(MatchingStrategies.LOOSE);//gevşek bağlılık için eksik gelen alanları map etme ne geliyorsa onu map et
        return this.modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)//olmayan verileri veya belirsizlik durumlarında o alanı ignore etmek için
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setDestinationNamingConvention(NamingConventions.NONE);
        return this.modelMapper;
    }

}
