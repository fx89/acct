package com.desolatetimelines.acct.usage.springrepository;

import com.desolatetimelines.acct.usage.model.JpaAcctService;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaServicesRepository extends CrudRepository<JpaAcctService, Long> {

    Optional<JpaAcctService> findFirstByServiceName(String serviceName);

}
