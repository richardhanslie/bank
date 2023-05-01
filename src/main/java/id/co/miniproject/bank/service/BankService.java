package id.co.miniproject.bank.service;

import id.co.miniproject.bank.entity.Bank;
import id.co.miniproject.bank.model.BankInfo;
import id.co.miniproject.bank.repository.BankRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

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

    public BankInfo getBankByNomorKtp(String nomorKtp) {
        try {
            Bank bank = repo.getBankByNomorKtp(nomorKtp);
            BankInfo bankInfo = new BankInfo();

            if(!ObjectUtils.isEmpty(bank)){
                bankInfo.setNama_customer(bank.getNama_customer());
                bankInfo.setSaldo(bank.getSaldo());
            }
            return bankInfo;
        }catch (Exception e){
            throw new RuntimeException("Failed Get Bank with nomor KTP: " + nomorKtp);
        }
    }
}
