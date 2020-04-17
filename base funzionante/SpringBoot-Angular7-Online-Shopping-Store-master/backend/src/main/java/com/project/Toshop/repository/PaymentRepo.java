package com.project.Toshop.repository;

import com.project.Toshop.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment,String> {

    Payment findByTxnId(String txnId);
}
