package com.workfusion.validation;

public class PhoneNumberValidation 
{
	public boolean isPhoneNumberCorrect(Long phoneNumber)
	{
		boolean isCorrect = false;
		if(String.valueOf(phoneNumber).length() == 10 && ((String.valueOf(phoneNumber).charAt(0) >= '6' && String.valueOf(phoneNumber).charAt(0) <= '9')))
		{
			isCorrect = true;
		}
		return isCorrect;
	}
}
