package com.desolatetimelines.acct.security.springrepository;

import com.desolatetimelines.acct.security.model.JpaAcctReportOwner;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface JpaAcctReportOwnersRepository extends CrudRepository<JpaAcctReportOwner, Long> {

    Set<JpaAcctReportOwner> findAllByReportUUIDIn(Collection<String> reportUUIDs);

}
