package defix.favordayapp.services.apiAccess;

import com.rabbitmq.client.Channel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

@Deprecated
public class ApiKeyCreator {
    private static final byte KEY_LENGTH = 60;
    private static final byte MIN_RANGE_ASCII = 48;
    private static final byte MAX_RANGE_ASCII = 91;
    private static final byte[] PROHIBITED_LETTERS = {
            58, 59, 60, 61, 62, 63, 64
    };

    // (0-100) LESS -->
    private static final byte LOWER_CASE_ABUNDANCE = 50;

    private static List<Character> cachedAllowedLetters = new ArrayList<>();

    public String generateApiKey() {
        StringBuilder apiKeyBuilder = new StringBuilder();
        for (int i = 0; i < KEY_LENGTH; i++)
            apiKeyBuilder.append(getRandomLetter());

        return apiKeyBuilder.toString();
    }

    private char getRandomLetter() {
        long seed = System.nanoTime() * System.currentTimeMillis();
        char generatedLet = (char) new Random(seed).nextInt(MIN_RANGE_ASCII, MAX_RANGE_ASCII);
        if(!isAllowedLetter(generatedLet))
            return getRandomLetter();
        if(new Random().nextInt(0, 100) > LOWER_CASE_ABUNDANCE)
            generatedLet = Character.toLowerCase(generatedLet);
        return generatedLet;
    }

    public static List<Character> getAllowedLetters() {
        if(!cachedAllowedLetters.isEmpty())
            return cachedAllowedLetters;

        List<Character> allowedLetters = new ArrayList<>();
        for(byte i = MIN_RANGE_ASCII; i < MAX_RANGE_ASCII; i++) {
            char curLet = (char) i;
            if(isAllowedLetter(curLet))
                allowedLetters.add(curLet);
        }
        cachedAllowedLetters = allowedLetters;
        return allowedLetters;
    }

    private static boolean isAllowedLetter(char letter) {
        return IntStream.range(0, PROHIBITED_LETTERS.length).allMatch(v -> (char)PROHIBITED_LETTERS[v] != letter);
    }
}
