import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches("admin123",
                "$2a$10$4us.IyzYX1IOKeEW5QFkZe9NhZHKARx2O2Te2QQEOBIIVHXGzQHe.");
        System.out.println("Password matches: " + matches);
    }
}
