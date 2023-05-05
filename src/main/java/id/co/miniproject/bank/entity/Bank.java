package id.co.miniproject.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "[nama_customer] cannot be blank")
    private String nama_customer;

    @NotNull(message = "[saldo] cannot be blank")
    private int saldo;

    private String nomor_rekening;

    @NotBlank(message = "[nomor_ktp] cannot be blank")
    @Size(min = 16)
    private String nomor_ktp;
}
