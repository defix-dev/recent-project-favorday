package defix.favordayapp.apiKeyTest;

import defix.favordayapp.services.apiAccess.ApiKeyCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

public class ApiKeyTest {
    private final Logger logger = LoggerFactory.getLogger(ApiKeyTest.class);

    @DisplayName("API GENERATE TEST")
    @Test
    public void generateApiKey() {
        ApiKeyCreator creator = new ApiKeyCreator();
        String key = creator.generateApiKey();

        logger.info(key);
        assertTrue(!key.isEmpty());
    }

    @DisplayName("API ALLOWED LET GET TEST")
    @Test
    public void getAllowedLettersTest() {
        List<Character> lets = ApiKeyCreator.getAllowedLetters();

        for(int i = 0; i < lets.stream().count(); i++)
            logger.info(String.valueOf(lets.get(i)));
        assertTrue(!lets.isEmpty());
    }
}
