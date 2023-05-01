package id.co.miniproject.bank.repository;

import id.co.miniproject.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends JpaRepository<Bank, Integer> {
    @Query(value = "SELECT * FROM bank WHERE nomor_ktp = :nomorKtp", nativeQuery = true)
    Bank getBankByNomorKtp(@Param("nomorKtp") String nomorKtp);

    @Query(value = "UPDATE bank saldo = :saldo WHERE nomor_ktp = :nomorKtp", nativeQuery = true)
    Bank updateBankByNomorKtp(@Param("saldo") int saldo, @Param("nomorKtp") String nomorKtp);
}
