package com.study.servlet_study.service;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.repository.AccountRepository;

// Singleton Pattern
public class AccountService {
	
	// AccountService 클래스 자료형(자기 자신 클래스)의 변수 instance 선언
	private static AccountService instance;
	private AccountRepository accountRepository;
	
	// 외부 생성 차단
	private AccountService() {
		accountRepository = AccountRepository.getInstance();
	}
	
	// 객체 생성 만들기(instance 가 null 이면 생성 아니면 기존 공유하는 instance 를 return)
	public static AccountService getInstance() {
		if(instance == null) {
			instance = new AccountService();
		}
		return instance;
	}
	
	public int addAccount(Account account) {
		return accountRepository.saveAccount(account);
	}
	
	public Account getAccount(String userName) {
		return accountRepository.findAccountByUserName(userName);	
	}

}
