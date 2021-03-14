package com.deepglint.fast_mail.service;

public interface FightService {

	public void fight(Long orderId);

	public int getUserId(Long orderId);

	public String getUserTelephone(int userId);
}
