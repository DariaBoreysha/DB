import com.github.javafaker.Faker;
import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.Random;

public class DatabaseTest {

    public DatabaseTest() throws SQLException {
    }
    Main main = new Main();
    Faker faker = new Faker();
    Random random = new Random();

    @Test
    public void getData(){
       main.getFullTable();
    }
    @Test
    public void addNewUser(){
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String pass = faker.internet().password();
        main.insertNewUser(name, email, pass);
        String nameRes = main.getLastUserName();
        String emailRes = main.getLastUserEmail();
        String passRes = main.getLastUserPass();
        Assertions.assertEquals(name,nameRes, "ER: " + name + ", AR: " + nameRes);
        Assertions.assertEquals(email,emailRes, "ER: " + email +  ", AR: " + emailRes);
        Assertions.assertEquals(passRes,pass, "ER: " + pass  +", AR: " + passRes);
    }
    @Test
    public void deleteLastUser(){
       int before = main.getNumberOfRows();
       main.deleteLastRow();
       int after = main.getNumberOfRows();
       Assertions.assertEquals(before-1,after,"The deletion is not correct");
    }
    @Test
    public void changeUsersPassword(){
        int numberOfRows = main.getNumberOfRows();
        int id = random.nextInt(numberOfRows-1+1)+1;
        String before = main.selectUserPassword(id);
        String newPass = faker.internet().password();
        main.updatePassword(newPass,id);
        String after = main.selectUserPassword(id);
        Assertions.assertTrue((newPass.equals(after))&&(newPass!=before), "Password isn't changed correctly");
    }

}
