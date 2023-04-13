
package com.jetbrains.teamcity.anewtodolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class UserController {

    public List<User> users = new CopyOnWriteArrayList<>(Arrays.asList(new User("Marco")));

    public static final class User {
        private final String nam;

        public User(String nam) {
            this.nam = nam;
        }

        public String nam() {
            return nam;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (User) obj;
            return Objects.equals(this.nam, that.nam);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nam);
        }

        @Override
        public String toString() {
            return "User[" +
                    "nam=" + nam + ']';
        }

        }


    @GetMapping("/users")
    public List<User> users() {
        return users;
    }


}
