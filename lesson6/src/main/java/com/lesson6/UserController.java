package com.lesson6;

import com.lesson6.exception.UserNotFoundException;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private static final Logger logInfo = LoggerFactory.getLogger("fileLoggerInfo");
    private static final Logger logError = LoggerFactory.getLogger("fileLoggerError");
    private final List<User> users = new ArrayList<>();

    // Összes felhasználó lekérdezése
    /*@GetMapping
    public List<User> getAllUsers() {
        return users;
    }*/

    @GetMapping
    public Object getAllUsers() {
        logInfo.info("Felhasználói lista lekérdezve: {}", users);
        return (users.isEmpty()) ? "A felhasználói lista üres." : users;
    }

    // Felhasználó létrehozása
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        /*if (user.getName() == null || user.getEmail() == null) {
            throw new NullPointerException("Hiányzó felhasználó adatok.");
        }*/
        user.setId(generateUserId());
        users.add(user);
        logInfo.info("Felhasználó létrehozva: ID={} - '{}' ('{}')", user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    private Long generateUserId() {
        return users.stream().mapToLong(User::getId).max().orElse(0) + 1;
    }

    // Felhasználó frissítése
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") @Min(1) Long id, @RequestBody @Valid User updatedUser) {
        String errorMessage = "Frissítési hiba. Az ID=" + id + " felhasználó nem található.";
        User user = getUserById(id, errorMessage);
        /*if (user == null) {
            logError.error("Frissítési hiba. Az ID=" + id + " felhasználó nem található.");
            throw new UserUpdateException("Frissítési hiba. Az ID=" + id + " felhasználó nem található.");
        }*/
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        logInfo.info("ID={} felhasználó frissítve. Új adatok='{}', '{}'", id, updatedUser.getName(), updatedUser.getEmail());
        return ResponseEntity.ok(user);
    }

    // Felhasználó lekérdezése az azonosító alapján
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") @Min(1) Long id) {
        String errorMessage = "Lekérdezési hiba. Az ID=" + id + " felhasználó nem található.";
        User user = getUserById(id, errorMessage);
        /*if (user == null) {
            //log.error("Felhasználó nem található: ID={}", id);
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nincs ilyen felhasználó!");
            throw new UserNotFoundException("Lekérdezési hiba. Az ID=" + id + " felhasználó nem található.");
        }*/
        logInfo.info("Felhasználó lekérdezve: ID={}, felhasználó neve='{}', email címe: '{}'", id, user.getName(), user.getEmail());
        return ResponseEntity.ok(user);
    }

    // Felhasználó törlése
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") @Min(1) Long id) {
        String errorMessage = "Törlési hiba. Az ID=" + id + " felhasználó nem található.";
        User user = getUserById(id, errorMessage);
        /*if (user == null) {
            throw new UserNotFoundException("Hiba törléskor. Az ID=" + id + " felhasználó nem található.");
        }*/
        users.remove(user);
        String infoMessage = "Felhasználó törölve. ID=" + id + ", felhasználó neve='" + user.getName() + "'.";
        logInfo.info(infoMessage);
        return ResponseEntity.ok(infoMessage);
    }

    /*private User getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }*/

    private User getUserById(Long id, String errorMessage) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() -> {
            logError.error(errorMessage);
            return new UserNotFoundException(errorMessage);
        });
    }
}