package cm.uni2grow.digitalInvocing.manageAddress.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cm.uni2grow.digitalInvocing.manageCustomer.models.dao.Customer;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String city;

    @Column(nullable = true)
    private String state;

    @Column(nullable = true)
    private String zipCode;

    private String country;

    @OneToOne(mappedBy = "billingAddress")
    @JsonIgnore
    private Invoice invoice;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Customer customer;
}
