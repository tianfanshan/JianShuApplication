package com.stf.controller;

import com.stf.entity.Wallet;
import com.stf.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/v1")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/wallets/id/{id}")
    public ResponseEntity<?> getWalletById(@PathVariable long id){
        Optional<Wallet> wallet = walletService.getWalletById(id);
        if (wallet.isEmpty()){
            return new ResponseEntity<>("The wallet with id " + id + " does not exists",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }

    @PutMapping("/wallets/id/{id}/amount/{amount}")
    public ResponseEntity<?> chargeWallet(@PathVariable Long id, @PathVariable double amount){
        Optional<Wallet> wallet = walletService.getWalletById(id);
        if (wallet.isEmpty()){
            return new ResponseEntity<>("The wallet with id " + id + " does not exist",HttpStatus.BAD_REQUEST);
        }
        if (amount < 0){
            return new ResponseEntity<>("Can not charge wallet less than 0",HttpStatus.BAD_REQUEST);
        }
        wallet.get().setBalance(wallet.get().getBalance() + amount);
        Wallet savedWallet = walletService.chargeWallet(wallet.get());
        return new ResponseEntity<>(savedWallet,HttpStatus.OK);
    }
}
