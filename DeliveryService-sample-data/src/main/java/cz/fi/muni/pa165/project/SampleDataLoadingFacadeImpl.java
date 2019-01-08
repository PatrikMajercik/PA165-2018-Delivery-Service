package cz.fi.muni.pa165.project;

import cz.muni.fi.pa165.project.AddressService;
import cz.muni.fi.pa165.project.ArticleService;
import cz.muni.fi.pa165.project.DeliveryService;
import cz.muni.fi.pa165.project.PersonService;
import cz.muni.fi.pa165.project.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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

        Address address1 = address(Address.builder().city("Praha").postalCode("03608").street("Volgogradska").streetNumber("35").build());
        Address address2 = address(Address.builder().city("Zlin").postalCode("03609").street("Ceska").streetNumber("85").build());
        Address address3 = address(Address.builder().city("Brno").postalCode("60600").street("Matouci").streetNumber("1").build());
        Address address4 = address(Address.builder().city("Karvina").postalCode("84522").street("Kefirova").streetNumber("2").build());
        Address address5 = address(Address.builder().city("Brno").postalCode("60600").street("Botanicka").streetNumber("150").build());
        Address address6 = address(Address.builder().city("Karvina").postalCode("63544").street("Trnavska").streetNumber("36").build());
        Address address7 = address(Address.builder().city("Praha").postalCode("69857").street("Kefirova").streetNumber("478").build());
        Address address8 = address(Address.builder().city("Zlin").postalCode("03609").street("Botanicka").streetNumber("2").build());

        Person person1 = person(Person.builder().name("Patrik Majercik").email("dusan@gmail.com").phoneNumber("+421090826564").address(address1).admin(true).passwordHash(createHash("admin")).build());
        Person person2 = person(Person.builder().name("Fero Kral").email("krava@gmail.com").phoneNumber("+421590826564").address(address2).admin(false).passwordHash(createHash("pleb")).build());
        Person person3 = person(Person.builder().name("Los Characteros").email("trava@gmail.com").phoneNumber("+421074165644").address(address3).admin(false).passwordHash(createHash("pleb")).build());
        Person person4 = person(Person.builder().name("Severus Snape").email("drevo@gmail.com").phoneNumber("+421090654221").address(address4).admin(false).passwordHash(createHash("pleb")).build());
        Person person5 = person(Person.builder().name("Laci Strajk").email("clivo@gmail.com").phoneNumber("+421094895647").address(address5).admin(false).passwordHash(createHash("pleb")).build());
        Person person6 = person(Person.builder().name("Albus Dumbledore").email("myjava@gmail.com").phoneNumber("+421054621564").address(address6).admin(false).passwordHash(createHash("pleb")).build());
        Person person7 = person(Person.builder().name("Harry Potter").email("krivo@gmail.com").phoneNumber("+421458852654").address(address7).admin(false).passwordHash(createHash("pleb")).build());
        Person person8 = person(Person.builder().name("Ron Weasley").email("serenada@gmail.com").phoneNumber("+421154895640").address(address8).admin(false).passwordHash(createHash("pleb")).build());

        Delivery delivery1 = delivery(person1, person2, articleList1, null, null, new BigDecimal(250), DeliveryState.DELIVERED);
        Delivery delivery2 = delivery(person2, person3, articleList2, null, null, new BigDecimal(150), DeliveryState.DELIVERED);
        Delivery delivery3 = delivery(person3, person2, articleList3, null, null, new BigDecimal(2550), DeliveryState.DELIVERED);
        Delivery delivery4 = delivery(person4, person2, articleList4, null, null, new BigDecimal(50), DeliveryState.DELIVERED);
        Delivery delivery5 = delivery(person5, person2, articleList5, null, null, new BigDecimal(150), DeliveryState.DELIVERED);
        Delivery delivery6 = delivery(person6, person2, articleList6, null, null, new BigDecimal(350), DeliveryState.DELIVERED);
        Delivery delivery7 = delivery(person7, person2, articleList7, null, null, new BigDecimal(450), DeliveryState.DELIVERED);
        Delivery delivery8 = delivery(person8, person2, articleList8, null, null, new BigDecimal(650), DeliveryState.DELIVERED);

        log.info("Sample data loaded was completed successfully.");
    }

    private Article article(String name, BigDecimal weight) {
        Article article = new Article();
        article.setName(name);
        article.setWeight(weight);

        articleService.create(article);

        return article;
    }

    private Delivery delivery(Person customer, Person courier, List<Article> articles, LocalDateTime ordered, LocalDateTime delivered, BigDecimal price, DeliveryState deliveryState) {
        Delivery delivery = new Delivery();
        delivery.setArticles(articles);
        delivery.setCourier(courier);
        delivery.setCustomer(customer);
        delivery.setOrdered(ordered);
        delivery.setDelivered(delivered);
        delivery.setPrice(price);
        delivery.setDeliveryState(deliveryState);

        deliveryService.create(delivery);

        return delivery;
    }

    private Person person(Person person) {
        personService.create(person);
        return person;
    }

    private Address address(Address address) {
        addressService.create(address);
        return address;
    }
   
    
    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
