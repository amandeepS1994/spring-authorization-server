package com.abidevel.oauth.authorization.utility;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public final class ObjectMapperUtility {
    private static final ModelMapper modelMapper;

    private ObjectMapperUtility () {
    }

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <T, T1> T1 mapEntity (T entity, Class<T1> targetEntity) {
        return modelMapper.map(entity, targetEntity);
    }

    public static <T, T1> Collection<T1> mapAllEntities (Collection<T> collectionEntities, Class<T1> targeClass) {
        return collectionEntities.stream()
            .map(col -> mapEntity(col, targeClass))
            .collect(Collectors.toList());
        
    }
}
