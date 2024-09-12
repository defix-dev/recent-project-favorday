package defix.favordayapp.voiceUtils;

import defix.favordayapp.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class VoiceDataManager {
    private final AccountService accountService;

    @Autowired
    public VoiceDataManager(AccountService accountService) {
        this.accountService = accountService;
    }

    public void useVoiceForPost(long postId) {
        try(FileWriter writer = new FileWriter(new ClassPathResource("voices.txt").getPath(), true)) {
            writer.append(accountService.getCurrentAccount().getUsername().concat("_").concat(String.valueOf(postId)));
        } catch (IOException ignored) { }
    }
}
