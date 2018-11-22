package cz.muni.fi.pa165.project;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.dozer.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomas Terem
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    @Inject
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        return objects.stream().map(o -> mapTo(o, mapToClass)).collect(Collectors.toList());
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        if (u == null)
            return null;
        return dozer.map(u, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return dozer;
    }
}