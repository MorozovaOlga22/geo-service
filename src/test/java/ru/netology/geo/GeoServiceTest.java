package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceTest {
    private final GeoService geoService = new GeoServiceImpl();

    @Test
    void testByIpLocalhost() {
        // given:
        final Location expectedLocation = new Location(null, null, null, 0);

        // when:
        final Location actualLocation = geoService.byIp(GeoServiceImpl.LOCALHOST);

        // then:
        assertNotNull(actualLocation);
        assertLocations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpMoscowIp() {
        // given:
        final Location expectedLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);

        // when:
        final Location actualLocation = geoService.byIp(GeoServiceImpl.MOSCOW_IP);

        // then:
        assertNotNull(actualLocation);
        assertLocations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpNewYorkIp() {
        // given:
        final Location expectedLocation = new Location("New York", Country.USA, " 10th Avenue", 32);

        // when:
        final Location actualLocation = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);

        // then:
        assertNotNull(actualLocation);
        assertLocations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpMoscow() {
        // given:
        final Location expectedLocation = new Location("Moscow", Country.RUSSIA, null, 0);

        // when:
        final Location actualLocation = geoService.byIp("172.100.100.100");

        // then:
        assertNotNull(actualLocation);
        assertLocations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpNewYork() {
        // given:
        final Location expectedLocation = new Location("New York", Country.USA, null, 0);

        // when:
        final Location actualLocation = geoService.byIp("96.100.100.100");

        // then:
        assertNotNull(actualLocation);
        assertLocations(expectedLocation, actualLocation);
    }

    @Test
    void testByIpNull() {
        // when:
        final Location actualLocation = geoService.byIp("100.100.100.100");

        // then:
        assertNull(actualLocation);
    }

    private void assertLocations(Location expectedLocation, Location actualLocation) {
        assertEquals(expectedLocation.getCity(), actualLocation.getCity());
        assertEquals(expectedLocation.getCountry(), actualLocation.getCountry());
        assertEquals(expectedLocation.getStreet(), actualLocation.getStreet());
        assertEquals(expectedLocation.getBuiling(), actualLocation.getBuiling());
    }
}