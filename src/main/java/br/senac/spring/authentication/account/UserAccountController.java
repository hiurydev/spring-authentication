package br.senac.spring.authentication.account;

import lombok.AllArgsConstructor;
import com.querydsl.core.types.Predicate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class UserAccountController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserAccountService userAccountService;

    @PostMapping
    @RequestMapping("/user")
    public ResponseEntity<UserAccountRepresentation.ListaUserAccount> createAccount(@RequestBody UserAccount userAccount) {
        userAccount.setPassword(this.bCryptPasswordEncoder.encode(userAccount.getPassword()));
        UserAccount userAccountSaved = this.userAccountService.saveUserAccount(userAccount);
        return ResponseEntity.ok(UserAccountRepresentation.ListaUserAccount.from(userAccountSaved));
    }


    @GetMapping
    public ResponseEntity<List<UserAccountRepresentation.ListaUserAccount>> getAllUserAccount(
            @QuerydslPredicate(root = UserAccount.class) Predicate predicate) {

        return ResponseEntity.ok(UserAccountRepresentation.ListaUserAccount.from(this.userAccountService.getListUserAccount(predicate)));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserAccountRepresentation.ListaUserAccount> atualizaUserAccount(@PathVariable("id") Integer id,
                                                                                          @Valid @RequestBody UserAccount userAccount) {
        UserAccount oldUserAccount = this.userAccountService.getUserAccount(id);
        oldUserAccount.setFullName(userAccount.getFullName());
        oldUserAccount.setUserName(userAccount.getUserName());
        UserAccount updatedUserAccount = this.userAccountService.saveUserAccount(oldUserAccount);
        return ResponseEntity.ok(UserAccountRepresentation.ListaUserAccount.from(updatedUserAccount));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUserAccount(@PathVariable("id") Integer id) {
        this.userAccountService.deleteUserAccount(id);
        return ResponseEntity.noContent().build();
    }

}