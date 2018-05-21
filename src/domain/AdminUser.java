package domain;

import java.util.Objects;

public class AdminUser {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminUser adminUser = (AdminUser) o;
        return Objects.equals(name, adminUser.name) &&
                Objects.equals(password, adminUser.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, password);
    }
}
