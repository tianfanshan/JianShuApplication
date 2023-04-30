package com.stf.service;

import com.stf.entity.Wallet;

import java.util.Optional;

public interface WalletService {

    Optional<Wallet> getWalletById(Long id);

    Wallet chargeWallet(Wallet wallet,Double amount);
}
