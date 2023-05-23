package brave.btc.repository.bo;

import org.springframework.data.jpa.repository.JpaRepository;

import brave.btc.domain.bo.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}