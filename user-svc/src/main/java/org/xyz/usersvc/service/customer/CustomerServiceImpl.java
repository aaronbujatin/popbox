package org.xyz.usersvc.service.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xyz.usersvc.dto.RegisterCustomerRequest;
import org.xyz.usersvc.dto.CustomerResponse;
import org.xyz.usersvc.entity.Address;
import org.xyz.usersvc.entity.Customer;
import org.xyz.usersvc.repository.AddressRepository;
import org.xyz.usersvc.repository.CustomerRepository;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Override
    public CustomerResponse createCustomer(RegisterCustomerRequest registerCustomerRequest) {

        var customer = Customer.builder()
                .firstName(registerCustomerRequest.firstName())
                .lastName(registerCustomerRequest.lastName())
                .email(registerCustomerRequest.email())
                .phone(registerCustomerRequest.phone())
                .isActive(true)
                .build();

        var savedCustomer = customerRepository.save(customer);

        var address = Address.builder()
                .customer(savedCustomer)
                .street(registerCustomerRequest.addressRequest().street())
                .postalCode(registerCustomerRequest.addressRequest().postalCode())
                .city(registerCustomerRequest.addressRequest().city())
                .country(registerCustomerRequest.addressRequest().country())
                .build();

        addressRepository.save(address);

        return mapToCustomerResp(savedCustomer);
    }

    @Override
    public CustomerResponse getUserById(Long id) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("customer not found"));
        return mapToCustomerResp(customer);
    }

    @Override
    public List<CustomerResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public CustomerResponse updateUser(RegisterCustomerRequest registerCustomerRequest) {
        return null;
    }

    @Override
    public String deleteUserById(Long id) {
        return "";
    }

    private CustomerResponse mapToCustomerResp(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getCreatedAt(),
                customer.getUpdatedAt(),
                customer.isActive()
        );

    }
}
