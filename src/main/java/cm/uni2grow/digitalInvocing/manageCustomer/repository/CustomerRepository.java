package cm.uni2grow.digitalInvocing.manageCustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.uni2grow.digitalInvocing.manageCustomer.models.dao.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
