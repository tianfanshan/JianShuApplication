package com.stf.service;

import com.stf.entity.Wallet;
import com.stf.entity.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImp implements WalletService{

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    @Override
    public Optional<Wallet> getWalletById(Long id) {
        return walletRepository.findById(id);
    }

    @Override
    public Wallet chargeWallet(Wallet wallet,Double amount) {
        wallet.setBalance(wallet.getBalance() + amount);
        return walletRepository.save(wallet);
    }
}
