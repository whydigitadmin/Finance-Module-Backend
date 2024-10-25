package com.base.basesetup.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountInWordsConverter {
	
	private static final String[] units = {
	        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
	        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
	        "Eighteen", "Nineteen"
	    };

	    private static final String[] tens = {
	        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
	    };

	    private static String convertBelowThousand(int number) {
	        if (number < 20) {
	            return units[number];
	        } else if (number < 100) {
	            return tens[number / 10] + (number % 10 != 0 ? " " + units[number % 10] : "");
	        } else {
	            return units[number / 100] + " Hundred" +
	                   (number % 100 != 0 ? " " + convertBelowThousand(number % 100) : "");
	        }
	    }

	    public static String convert(BigDecimal amount) {
	        if (amount.compareTo(BigDecimal.ZERO) == 0) {
	            return "Zero Only";
	        }

	        // Split into rupees and paise
	        BigDecimal rupeesPart = amount.setScale(0, RoundingMode.FLOOR);
	        BigDecimal paisePart = amount.subtract(rupeesPart).movePointRight(2).setScale(0, RoundingMode.HALF_UP);
	        
	        long rupees = rupeesPart.longValue();
	        int paise = paisePart.intValue();

	        StringBuilder result = new StringBuilder();

	        int crore = (int) (rupees / 1_00_00_000);
	        rupees %= 1_00_00_000;

	        int lakh = (int) (rupees / 1_00_000);
	        rupees %= 1_00_000;

	        int thousand = (int) (rupees / 1_000);
	        rupees %= 1_000;

	        int hundred = (int) rupees;

	        if (crore > 0) {
	            result.append(convertBelowThousand(crore)).append(" Crore ");
	        }
	        if (lakh > 0) {
	            result.append(convertBelowThousand(lakh)).append(" Lakh ");
	        }
	        if (thousand > 0) {
	            result.append(convertBelowThousand(thousand)).append(" Thousand ");
	        }
	        if (hundred > 0) {
	            result.append(convertBelowThousand(hundred));
	        }

	        // Add rupees and paise text
	       
	        result.append(" Only");

	        return result.toString().trim();
	    }

	    // Example usage
	    public static void main(String[] args) {
	        BigDecimal amount = new BigDecimal("10200");  // Example input
	        String amountInWords;
				amountInWords = convert(amount);
	        System.out.println(amountInWords);  // Output: "One Lakh Two Hundred Only"
	    }

}
