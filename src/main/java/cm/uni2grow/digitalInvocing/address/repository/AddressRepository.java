package cm.uni2grow.digitalInvocing.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.uni2grow.digitalInvocing.address.model.dao.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
