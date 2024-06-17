package lv.acodemy.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

public class Config {

    private Credentials credentials;

    public static class Credentials {

        private String login;
        private String password;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public static Config readConfig() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("config.yaml")) {
            if (inputStream == null) {
                throw new IOException("config.yaml not found");
            }
            return yaml.loadAs(inputStream, Config.class);
        } catch (IOException e) {
            var message = e.getMessage();
            System.out.println(message);
            return null;
        }
    }
}
