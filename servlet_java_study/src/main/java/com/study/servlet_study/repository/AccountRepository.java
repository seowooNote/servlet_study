package com.study.servlet_study.repository;

import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.entity.Account;

// Singleton Pattern
public class AccountRepository {
	
	// AccountRepository 클래스 자료형(자기 자신 클래스)의 변수 instance 선언
	private static AccountRepository instance;
	private List<Account> accountList;
	
	// 외부 생성 차단
	private AccountRepository() {
		accountList = new ArrayList<>();
	}
	
	// 객체 생성 만들기(instance 가 null 이면 생성 아니면 기존 공유하는 instance 를 return)
	public static AccountRepository getInstance() {
		if(instance == null) {
			instance = new AccountRepository();
		}
		return instance;
	}
	
	public int saveAccount(Account account) {
		accountList.add(account);
		return 1;
	}
	
	public Account findAccountByUserName(String userName) {
		Account findAccount = null;
		for(Account account : accountList) {
			if(account.getUserName().equals(userName)) {
				findAccount = account;
				break;
			}
		}
		return findAccount;
	}
	
}
