package com.elyes.ecommerce.customer;


import com.elyes.ecommerce.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(format("Customer with id %s not found", request.id())));

    mergeCustomer(customer,request);
    repository.save(customer);

    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank((request.firstName()))){
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank((request.lastName()))){
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank((request.email()))){
            customer.setEmail(request.email());
        }
        if (request.adress() != null){
            customer.setAdress(request.adress());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", customerId))
                );
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
