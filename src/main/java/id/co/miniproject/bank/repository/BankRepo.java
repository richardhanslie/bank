package id.co.miniproject.bank.repository;

import id.co.miniproject.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends JpaRepository<Bank, Integer> {
    @Query(value = "SELECT * FROM bank WHERE nomor_rekening = :nomorrekening", nativeQuery = true)
    Bank getBankByNomorRekening(@Param("nomorRekening") String nomorRekening);

    @Query(value = "SELECT * FROM bank WHERE nomor_ktp = :nomorktp", nativeQuery = true)
    Bank getBankByNomorKtp(@Param("nomorktp") String nomorKtp);

    @Query(value = "UPDATE bank saldo = :saldo WHERE nomor_rekening = :nomorRekening", nativeQuery = true)
    Bank updateBankByNomorRekening(@Param("saldo") int saldo, @Param("nomorRekening") String nomorRekening);
}
