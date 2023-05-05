package id.co.miniproject.bank.model;

import lombok.Data;

@Data
public class BankInfo {
    private String nama_customer;
    private String nomor_rekening;
    private int saldo;
}
