package core.services;

import core.entities.Address;
import core.entities.User;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    void addAddressToUserEntity(User entityUser, String addressLine, String country) {
        Address address = new Address();

        address.setCountry(country);
        address.setAddressLine(addressLine);

        entityUser.setAddress(address);
    }
}
