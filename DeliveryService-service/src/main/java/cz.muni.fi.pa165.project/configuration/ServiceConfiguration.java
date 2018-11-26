package cz.muni.fi.pa165.project.configuration;

import cz.muni.fi.pa165.project.AddressServiceImpl;
import cz.muni.fi.pa165.project.ApplicationContext;
import cz.muni.fi.pa165.project.ArticleServiceImpl;
import cz.muni.fi.pa165.project.dto.AddressDTO;
import cz.muni.fi.pa165.project.dto.ArticleDTO;
import cz.muni.fi.pa165.project.dto.PersonDTO;
import cz.muni.fi.pa165.project.entity.Article;
import cz.muni.fi.pa165.project.dto.DeliveryDTO;
import cz.muni.fi.pa165.project.entity.Address;
import cz.muni.fi.pa165.project.entity.Delivery;
import cz.muni.fi.pa165.project.entity.Person;
import cz.muni.fi.pa165.project.facade.AddressFacadeImpl;
import cz.muni.fi.pa165.project.facade.ArticleFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.dozer.loader.api.BeanMappingBuilder;

/**
 * @author Tomas Terem
 */
@Configuration
@Import(ApplicationContext.class)
@ComponentScan(basePackageClasses = {AddressServiceImpl.class, AddressFacadeImpl.class, ArticleServiceImpl.class, ArticleFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Person.class, PersonDTO.class);
            mapping(Address.class, AddressDTO.class);
            mapping(Article.class, ArticleDTO.class);
            mapping(Delivery.class, DeliveryDTO.class);
        }
    }
}
