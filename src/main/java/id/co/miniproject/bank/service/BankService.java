package id.co.miniproject.bank.service;

import id.co.miniproject.bank.entity.Bank;
import id.co.miniproject.bank.model.BankData;
import id.co.miniproject.bank.model.BankInfo;
import id.co.miniproject.bank.repository.BankRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepo repo;

    public List<Bank> getAllBank() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Bank getBank(int id) {
        try {
            return repo.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("Bank not found with ID: " + id);
        }
    }

    public Bank addNewBank(Bank Bank) {
        Bank newBank = new Bank();
        //generate 16 digit random number dengan awalan 52
        Bank.setNomor_rekening(generateRandom(52));
        try {
            return repo.save(Bank);
        } catch (Exception e) {
            throw new RuntimeException("Add New Bank Failed, Try Again");
        }
    }

    public Bank updateBankData(Bank Bank) {
        try {
            return repo.save(Bank);
        } catch (Exception e) {
            throw new RuntimeException("Update Bank Data Failed, Try Again");
        }
    }

    public BankInfo getBankByRekening(String nomorRekening) {
        try {
            Bank bank = repo.getBankByNomorRekening(nomorRekening);
            BankInfo bankInfo = new BankInfo();

            if(!ObjectUtils.isEmpty(bank)){
                bankInfo.setNama_customer(bank.getNama_customer());
                bankInfo.setSaldo(bank.getSaldo());
            }
            return bankInfo;
        }catch (Exception e){
            throw new RuntimeException("Failed Get Bank with nomor KTP: " + nomorRekening);
        }
    }

    public static String generateRandom(int prefix) {
        Random rand = new Random();

        long x = (long)(rand.nextDouble()*100000000000000L);

        String s = String.valueOf(prefix) + String.format("%014d", x);
        return s;
    }

    @Transactional(rollbackOn = Exception.class)
    public Boolean transfer(BankData bankData) {
        try {
            Bank bankPengirim = repo.getBankByNomorRekening(bankData.getNomorRekeningPengirim());
            Bank bankPenerima = repo.getBankByNomorRekening(bankData.getNomorRekeningPenerima());

            System.out.println("Saldo bank pengirim: " + bankPengirim.getSaldo());
            System.out.println("Harga: " + bankData.getHarga());
            System.out.println("No Rek Pengirim: " + bankData.getNomorRekeningPengirim());
            repo.updateBankByNomorRekening(bankPengirim.getSaldo() - bankData.getHarga()
                    , bankData.getNomorRekeningPengirim());

            repo.updateBankByNomorRekening(bankPenerima.getSaldo() + bankData.getHarga()
                    , bankData.getNomorRekeningPenerima());

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Update Bank Data Failed, Try Again");
        }
    }

    public BankInfo getBankByNomorKtp(String nomorKtp) {
        try {
            Bank bank = repo.getBankByNomorKtp(nomorKtp);
            BankInfo bankInfo = new BankInfo();

            if(!ObjectUtils.isEmpty(bank)){
                bankInfo.setNama_customer(bank.getNama_customer());
                bankInfo.setSaldo(bank.getSaldo());
                bankInfo.setNomor_rekening(bank.getNomor_rekening());
            }
            return bankInfo;
        }catch (Exception e){
            throw new RuntimeException("Failed Get Bank with nomor KTP: " + nomorKtp);
        }
    }
}
