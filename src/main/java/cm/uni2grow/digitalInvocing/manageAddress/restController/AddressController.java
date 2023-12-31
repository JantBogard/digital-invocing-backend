package cm.uni2grow.digitalInvocing.manageAddress.restController;

import org.springframework.web.bind.annotation.RestController;

import cm.uni2grow.digitalInvocing.manageAddress.metier.AddressMetier;
import cm.uni2grow.digitalInvocing.manageAddress.model.dao.Address;
import cm.uni2grow.digitalInvocing.manageAddress.model.dto.AddressDto;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/address")
public class AddressController {
    AddressMetier addressMetier;

    AddressController(AddressMetier theAddressMetier) {
        addressMetier = theAddressMetier;
    }

    @GetMapping("")
    public List<AddressDto> getAllAddress() {
        return this.addressMetier.getAll();
    }

    @GetMapping("/{idAddress}")
    public AddressDto getAddress(@PathVariable Long idAddress) {
        return this.addressMetier.getOne(idAddress);
    }

    @PostMapping("")
    public AddressDto addAddress(@RequestBody Address address) {
        return this.addressMetier.save(address);
    }

    @PutMapping("/{idAddress}")
    public AddressDto updateAddress(@PathVariable Long idAddress, @RequestBody AddressDto addressDto) {
        return this.addressMetier.update(idAddress, addressDto);
    }

    @DeleteMapping("/{idAddress}")
    public String deleteAddress(@PathVariable Long idAddress) {
        return this.addressMetier.remove(idAddress);
    }
}
