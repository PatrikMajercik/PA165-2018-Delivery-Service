package cz.muni.fi.pa165.project;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomas Terem
 */
@Service
public interface BeanMappingService {

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();
}