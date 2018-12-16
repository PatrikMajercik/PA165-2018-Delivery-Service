package cz.muni.fi.pa165.project;

import cz.muni.fi.pa165.project.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * TODO: create javadoc *
 *
 * @author Patrik Majercik
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    private final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);
    @Autowired
    private AddressService addressService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private PersonService personService;

    @Override
    public void loadData() throws IOException {
        log.info("Sample data loaded starts");

        Article article1 = article("Carrot", new BigDecimal(200));
        Article article2 = article("Notebook", new BigDecimal(2800));
        Article article3 = article("Apple", new BigDecimal(150));
        Article article4 = article("Magnet", new BigDecimal(850));
        Article article5 = article("Watches", new BigDecimal(770));
        Article article6 = article("Medicine", new BigDecimal(120));
        Article article7 = article("Paper", new BigDecimal(1250));
        Article article8 = article("Snake", new BigDecimal(1470));
        Article article9 = article("Globe", new BigDecimal(550));
        Article article10 = article("Cucumber", new BigDecimal(770));
        Article article11 = article("Table", new BigDecimal(12000));
        Article article12 = article("Chair", new BigDecimal(5400));
        Article article13 = article("Ring", new BigDecimal(250));
        Article article14 = article("Rope", new BigDecimal(550));
        Article article15 = article("Window", new BigDecimal(120));

        List<Article> articleList1 = new ArrayList<>(Arrays.asList(article1, article2));
        List<Article> articleList2 = new ArrayList<>(Arrays.asList(article3, article4));
        List<Article> articleList3 = new ArrayList<>(Arrays.asList(article5, article13, article14));
        List<Article> articleList4 = new ArrayList<>(Collections.singletonList(article6));
        List<Article> articleList5 = new ArrayList<>(Arrays.asList(article7, article15));
        List<Article> articleList6 = new ArrayList<>(Collections.singletonList(article8));
        List<Article> articleList7 = new ArrayList<>(Collections.singletonList(article9));
        List<Article> articleList8 = new ArrayList<>(Collections.singletonList(article10));
        List<Article> articleList9 = new ArrayList<>(Collections.singletonList(article12));
        List<Article> articleList10 = new ArrayList<>(Arrays.asList(article1, article11));

        Address address1 = Address.builder().city("Praha").postalCode("03608").street("Volgogradska").streetNumber("35").build();
        Address address2 = Address.builder().city("Zlin").postalCode("03609").street("Ceska").streetNumber("85").build();
        Address address3 = Address.builder().city("Brno").postalCode("60600").street("Matouci").streetNumber("1").build();
        Address address4 = Address.builder().city("Karvina").postalCode("84522").street("Kefirova").streetNumber("2").build();
        Address address5 = Address.builder().city("Brno").postalCode("60600").street("Botanicka").streetNumber("150").build();
        Address address6 = Address.builder().city("Karvina").postalCode("63544").street("Trnavska").streetNumber("36/54").build();
        Address address7 = Address.builder().city("Praha").postalCode("69857").street("Kefirova").streetNumber("478").build();
        Address address8 = Address.builder().city("Zlin").postalCode("03609").street("Botanicka").streetNumber("2").build();

        Person person1 = Person.builder().name("Patrik Majercik").email("dusan@gmail.com").phoneNumber("090826564").address(address1).build();
        Person person2 = Person.builder().name("Fero Kral").email("krava@gmail.com").phoneNumber("590826564").address(address2).build();
        Person person3 = Person.builder().name("Los Characteros").email("trava@gmail.com").phoneNumber("07416564").address(address3).build();
        Person person4 = Person.builder().name("Kebab Mazurek").email("drevo@gmail.com").phoneNumber("09065422164").address(address4).build();
        Person person5 = Person.builder().name("Laci Strajk").email("clivo@gmail.com").phoneNumber("09489564").address(address5).build();
        Person person6 = Person.builder().name("Andrej STBabis").email("myjava@gmail.com").phoneNumber("054621564").address(address6).build();
        Person person7 = Person.builder().name("Kapitan Danko").email("krivo@gmail.com").phoneNumber("4588526564").address(address7).build();
        Person person8 = Person.builder().name("Marian Zvocca").email("serenada@gmail.com").phoneNumber("15489564").address(address8).build();

        Delivery delivery1 = delivery(person1,person2,articleList1, LocalDateTime.now(),LocalDateTime.now().plusHours(5),new BigDecimal(250),DeliveryState.DELIVERED);
        Delivery delivery2 = delivery(person2,person3,articleList2, LocalDateTime.now(),LocalDateTime.now().plusHours(5),new BigDecimal(150),DeliveryState.DELIVERED);
        Delivery delivery3 = delivery(person3,person2,articleList3, LocalDateTime.now(),LocalDateTime.now().plusHours(5),new BigDecimal(2550),DeliveryState.DELIVERED);
        Delivery delivery4 = delivery(person4,person2,articleList4, LocalDateTime.now(),LocalDateTime.now().plusHours(5),new BigDecimal(50),DeliveryState.DELIVERED);
        Delivery delivery5 = delivery(person5,person2,articleList5, LocalDateTime.now(),LocalDateTime.now().plusHours(5),new BigDecimal(150),DeliveryState.DELIVERED);
        Delivery delivery6 = delivery(person6,person2,articleList6, LocalDateTime.now(),LocalDateTime.now().plusHours(5),new BigDecimal(350),DeliveryState.DELIVERED);
        Delivery delivery7 = delivery(person7,person2,articleList7, LocalDateTime.now(),LocalDateTime.now().plusHours(5),new BigDecimal(450),DeliveryState.DELIVERED);
        Delivery delivery8 = delivery(person8,person2,articleList8, LocalDateTime.now(),LocalDateTime.now().plusHours(5),new BigDecimal(650),DeliveryState.DELIVERED);

        log.info("Sample data loaded was completed successfully.");
    }

    private Article article(String name, BigDecimal weight) {
        Article article = new Article();
        article.setName(name);
        article.setWeight(weight);
        return article;
    }

    private Delivery delivery(Person customer,Person courier, List<Article> articles,LocalDateTime ordered,LocalDateTime delivered,BigDecimal price,DeliveryState deliveryState ){
        Delivery delivery = new Delivery();
        delivery.setArticles(articles);
        delivery.setCourier(courier);
        delivery.setCustomer(customer);
        delivery.setOrdered(ordered);
        delivery.setDelivered(delivered);
        delivery.setPrice(price);
        delivery.setDeliveryState(deliveryState);
        return delivery;
    }
}
