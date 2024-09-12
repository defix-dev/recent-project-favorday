package defix.favordayapp.services.post;

import defix.favordayapp.db.postgresql.entity.Account;
import defix.favordayapp.services.account.AccountService;
import defix.favordayapp.services.post.exceptions.UnConfirmedStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostFormService {
    private final PostService postService;
    private final AccountService accountService;

    @Autowired
    public PostFormService(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }

    public void createPost(PostDTO post, boolean confirm) throws UnConfirmedStateException {
        if(confirm)
            postService.savePostByDTO(post, getAuthorName());
        else
            throw new UnConfirmedStateException();
    }

    private String getAuthorName() {
        Account currentAccount = accountService.getCurrentAccount();
        Optional<String> accountName = Optional.ofNullable(currentAccount.getName());
        String accountUsername = currentAccount.getUsername();
        return accountName.orElse(accountUsername);
    }
}
