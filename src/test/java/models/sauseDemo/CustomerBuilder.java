package models.sauseDemo;
public class CustomerBuilder {
    String firstName;
    String lastName;
    String zipPostalCode;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public static class Builder {
        private CustomerBuilder newCustomer;

        public Builder(){
            newCustomer = new CustomerBuilder();
        }

        public Builder withFirstName(String firstName){
            newCustomer.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            newCustomer.lastName = lastName;
            return this;
        }

        public Builder withZipPostalCode(String zipPostalCode){
            newCustomer.zipPostalCode = zipPostalCode;
            return this;
        }

        public CustomerBuilder build(){
            return newCustomer;
        }
    }
}
