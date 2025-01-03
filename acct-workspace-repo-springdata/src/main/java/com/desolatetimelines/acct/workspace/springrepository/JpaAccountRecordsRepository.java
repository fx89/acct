package com.desolatetimelines.acct.workspace.springrepository;

import com.desolatetimelines.acct.workspace.model.JpaAcctAccountRecord;
import org.springframework.data.repository.CrudRepository;


public interface JpaAccountRecordsRepository extends CrudRepository<JpaAcctAccountRecord, Long> {

}
