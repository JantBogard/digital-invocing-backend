package cm.uni2grow.digitalInvocing.manageAddress.model.dao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cm.uni2grow.digitalInvocing.manageCustomer.models.dao.Customer;
import cm.uni2grow.digitalInvocing.manageInvoice.models.dao.Invoice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "billingAddress")
    @JsonIgnore
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Customer> customer;
}
