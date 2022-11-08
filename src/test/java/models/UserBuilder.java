package models;
public class UserBuilder {

    String login;
    String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public static class Builder {
        private UserBuilder newUser;

        public Builder(){
            newUser = new UserBuilder();
        }

        public Builder withPassword (String password){
            newUser.password = password;
            return this;
        }

        public Builder withLogin(String login){
            newUser.login = login;
            return this;
        }

        public UserBuilder build(){
            return newUser;
        }
    }
}
