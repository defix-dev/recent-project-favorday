package defix.favordayapp.services.account;

import defix.favordayapp.db.postgresql.entity.Account;
import defix.favordayapp.db.postgresql.entity.Role;
import defix.favordayapp.db.postgresql.repositories.AccountRepository;
import defix.favordayapp.services.account.exceptions.AccountAlreadyExistsException;
import defix.favordayapp.services.account.exceptions.PasswordDoNotMatchException;
import defix.favordayapp.services.account.exceptions.UnCorrectAccountDataException;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import defix.favordayapp.services.account.exceptions.UnauthorizedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        return account;
    }

    public void registerAccount(RegisterDTO account) throws UnCorrectAccountDataException,
            PasswordDoNotMatchException, AccountAlreadyExistsException {
        Set<ConstraintViolation<RegisterDTO>> violations = getRegisterViolations(account);

        if(!violations.isEmpty())
            throw new UnCorrectAccountDataException(violations);

        if(!tryMatchPasswords(account.getPassword(), account.getConfirmPassword()))
            throw new PasswordDoNotMatchException();

        if(checkAccountExists(account.getUsername()))
            throw new AccountAlreadyExistsException();

        saveAccountByRegisterDTO(account);
    }

    private Set<ConstraintViolation<RegisterDTO>> getRegisterViolations(RegisterDTO account) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator.validate(account);
    }

    private boolean tryMatchPasswords(String a, String b) {
        return a.equals(b);
    }

    private void saveAccountByRegisterDTO(RegisterDTO account) {
        Account accountToSave = new Account();
        accountToSave.setUsername(account.getUsername());
        accountToSave.setPassword(passwordEncoder.encode(account.getPassword()));
        accountToSave.setRoles(Collections.singleton(Role.defaultAccountRole()));
        accountToSave.setDate(LocalDate.now());
        accountToSave.setName(account.getName());
        accountToSave.setSurname(account.getSurname());

        accountRepository.save(accountToSave);
    }

    private boolean checkAccountExists(String login) {
        return accountRepository.findByUsername(login) != null;
    }

    public Account getCurrentAccount() throws UnauthorizedAccountException {
        Optional<Authentication> authorization = Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication());

        if(authorization.isEmpty())
            throw new UnauthorizedAccountException();

        return accountRepository
                .findByUsername(((UserDetails)authorization.get().getPrincipal()).getUsername());
    }
}
