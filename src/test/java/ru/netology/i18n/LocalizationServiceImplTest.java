package ru.netology.i18n;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

class LocalizationServiceImplTest {
    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    void testLocaleRussia() {
        // given:
        final String expectedText = "Добро пожаловать";

        // when:
        final String actualText = localizationService.locale(RUSSIA);

        // then:
        assertEquals(expectedText, actualText);
    }

    @Test
    void testLocaleOther() {
        // given:
        final String expectedText = "Welcome";

        // when:
        final String actualText = localizationService.locale(USA);

        // then:
        assertEquals(expectedText, actualText);
    }
}