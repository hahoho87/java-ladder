package nextstep.optional;

import java.util.Optional;

public class User {
    private final String name;
    private final Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public boolean matchName(String name) {
        return this.name.equals(name);
    }

    public static boolean ageIsInRange1(User user) {
        return user != null && user.getAge() != null
                && (user.getAge() >= 30
                && user.getAge() <= 45);
    }

    public static boolean ageIsInRange2(User user) {
        return Optional.ofNullable(user)
                .map(User::getAge)
                .filter(age -> age >= 30 && age <= 45)
                .isPresent();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (name == null) {
            return other.name == null;
        } else return name.equals(other.name);
    }
}
