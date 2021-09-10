package br.senac.spring.authentication.account;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountService {

    @Autowired
    private UserAccountRepository repository;

    public UserAccount saveUserAccount(UserAccount userAccount) {
        return this.repository.save(userAccount);
    }

    public List<UserAccount> getListUserAccount(Predicate predicate) {
        return this.repository.findAll(predicate);
    }

    public UserAccount getUserAccount(Integer id) {
        return this.repository.findById(id).get();
    }

    public void deleteUserAccount(Integer id) {
        this.repository.deleteById(id);
    }
}