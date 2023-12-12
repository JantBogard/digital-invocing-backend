package cm.uni2grow.digitalInvocing.manageAddress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.uni2grow.digitalInvocing.manageAddress.model.dao.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
