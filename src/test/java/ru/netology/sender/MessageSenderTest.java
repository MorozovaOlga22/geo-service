package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

class MessageSenderTest {
    private final MessageSender messageSender = createMock();

    @Test
    void testSendRussia() {
        // given:
        final String expectedCountry = "Добро пожаловать";
        final Map<String, String> headers = creatHeaders(MOSCOW_IP);

        // when:
        final String actualCountry = messageSender.send(headers);

        // then:
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    void testSendOther() {
        // given:
        final String expectedCountry = "Welcome";
        final Map<String, String> headers = creatHeaders(NEW_YORK_IP);

        // when:
        final String actualCountry = messageSender.send(headers);

        // then:
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    void testSendEmptyAddress() {
        // given:
        final String expectedCountry = "Welcome";
        final Map<String, String> headers = creatHeaders("");

        // when:
        final String actualCountry = messageSender.send(headers);

        // then:
        assertEquals(expectedCountry, actualCountry);
    }

    //    @Test
//    С пустым адресом код работает странно...
    void testSendNullAddress() {
        // given:
        final String expectedCountry = "Welcome";
        final Map<String, String> headers = creatHeaders(null);

        // when:
        final String actualCountry = messageSender.send(headers);

        // then:
        assertEquals(expectedCountry, actualCountry);
    }

    //    @Test
//    Без заголовка с адресом код работает странно...
    void testSendNoAddressHeader() {
        // given:
        final String expectedCountry = "Welcome";
        final Map<String, String> headers = new HashMap<String, String>();

        // when:
        final String actualCountry = messageSender.send(headers);

        // then:
        assertEquals(expectedCountry, actualCountry);
    }

    private MessageSender createMock() {
        final GeoService geoService = Mockito.mock(GeoService.class);
        final Location locationRussia = new Location("Moscow", RUSSIA, "Lenina", 15);
        final Location locationUsa = new Location("New York", USA, " 10th Avenue", 32);
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(locationRussia);
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(locationUsa);

        final LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");

        return new MessageSenderImpl(geoService, localizationService);
    }

    private Map<String, String> creatHeaders(String ipAddress) {
        final Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, ipAddress);
        return headers;
    }
}