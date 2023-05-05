package id.co.miniproject.bank.controller;

import id.co.miniproject.bank.entity.Bank;
import id.co.miniproject.bank.model.BankData;
import id.co.miniproject.bank.model.BankInfo;
import id.co.miniproject.bank.service.BankService;
import id.co.miniproject.bank.util.ErrorCode;
import id.co.miniproject.bank.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;
    private final ResponseUtils responseUtils;

    @GetMapping()
    private ResponseEntity<Object> getAllBank() {
        List<Bank> response = bankService.getAllBank();
        if (ObjectUtils.isEmpty(response)) {
            return responseUtils.generate(ErrorCode.Not_Found, HttpStatus.NOT_FOUND, null);
        }
        return responseUtils.generate(ErrorCode.Success, HttpStatus.OK, response);
    }

    @PostMapping()
    private ResponseEntity<Object> addNewBank(@Validated @RequestBody Bank bank) {
        Bank newBank = bankService.addNewBank(bank);
        if (StringUtils.isEmpty(newBank)) {
            return responseUtils.generate(ErrorCode.General_Error, HttpStatus.BAD_REQUEST, null);
        }
        return responseUtils.generate(ErrorCode.Success, HttpStatus.ACCEPTED, newBank);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> getBankById(@PathVariable("id") int id) {
        Bank bank = bankService.getBank(id);
        if (ObjectUtils.isEmpty(bank)) {
            return responseUtils.generate(ErrorCode.Not_Found, HttpStatus.NOT_FOUND, null);
        }
        return responseUtils.generate(ErrorCode.Success, HttpStatus.OK, bank);
    }

    @PutMapping()
    private ResponseEntity<Object> updateBank(@Validated @RequestBody Bank bank) {
        Bank newBank = bankService.updateBankData(bank);
        if (StringUtils.isEmpty(newBank)) {
            return responseUtils.generate(ErrorCode.General_Error, HttpStatus.BAD_REQUEST, null);
        }
        return responseUtils.generate(ErrorCode.Success, HttpStatus.ACCEPTED, newBank);
    }

    @PostMapping("/transfer")
    private ResponseEntity<Object> transfer(@RequestBody BankData bank) {
        Boolean res = bankService.transfer(bank);
        if (Boolean.FALSE.equals(res)) {
            return responseUtils.generate(ErrorCode.General_Error, HttpStatus.BAD_REQUEST, null);
        }
        return responseUtils.generate(ErrorCode.Success, HttpStatus.ACCEPTED, Boolean.TRUE);
    }

    @GetMapping("/info/{nomorKtp}")
    private ResponseEntity<Object> getBankByNomorKtp(@PathVariable("nomorKtp") String nomorKtp) {
        BankInfo bank = bankService.getBankByNomorKtp(nomorKtp);
        if (ObjectUtils.isEmpty(bank)) {
            return responseUtils.generate(ErrorCode.Not_Found, HttpStatus.NOT_FOUND, null);
        }
        return responseUtils.generate(ErrorCode.Success, HttpStatus.OK, bank);
    }
}
