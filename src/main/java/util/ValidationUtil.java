package util;

import java.time.LocalDate;
import java.time.Period;

public class ValidationUtil {
	/**
	 * Validates if the provided text contains only letters and whitespace characters.
	 * 
	 * @param text The text to be validated.
	 * @return True if the text contains only letters and whitespace, false otherwise.
	 */
	public static boolean isTextOnly(String text) {
		return text.matches("[a-zA-Z\\s]+"); // Match letters and whitespace only
	}

	/**
	 * Validates if the provided text is alphanumeric, containing only letters and digits.
	 * 
	 * @param text The text to be validated.
	 * @return True if the text is alphanumeric, false otherwise.
	 */
	public static boolean isAlphanumeric(String text) {
		return text.matches("[a-zA-Z0-9]+"); // Match letters and digits only
	}

	public static boolean isAlphanumericSpace(String text) {
		return text.matches("[a-zA-Z0-9\\s]+"); // Match letters and digits only
	}
	
	/**
	 * Validates if the provided text contains only digits (0-9).
	 * 
	 * @param text The text to be validated.
	 * @return True if the text contains only digits, false otherwise.
	 */
	public static boolean isNumbersOnly(String text) {
		return text.matches("\\d+"); // Match digits only
	}

	public static boolean isPositiveFloatOnly(String text) {
		return text.matches("\\d+(\\.\\d+)?"); // Match positive float numbers
	}

	/**
	 * Validates if the provided text is a valid email address format.
	 * 
	 * @param email The email address to be validated.
	 * @return True if the email address has a valid format, false otherwise.
	 */
	public static boolean isEmail(String email) {
		return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$"); // Match standard email pattern
	}

	/**
	 * Validates if the provided password meets complexity requirements:
	 * - Contains at least one uppercase letter (A-Z)
	 * - Contains at least one lowercase letter (a-z)
	 * - Contains at least one digit (0-9)
	 * - Contains at least one symbol (@$!%*?&).
	 * 
	 * @param password The password to be validated.
	 * @return True if the password meets complexity requirements, false otherwise.
	 */
	public static boolean isValidPassword(String password) {
		return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]*$");
	}

	/**
	 * Validates if the provided text has the specified length.
	 * 
	 * @param text The text to be validated.
	 * @param length The expected length of the text.
	 * @return True if the text has the specified length, false otherwise.
	 */
	public static boolean hasLength(String text, int length) {
		return text.length() == length;
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber.matches("^\\+[0-9]{13}$");
	}

	public static boolean isImage(String fileName) {
		// Get the file extension
		String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

		return extension.equals("jpg") || extension.equals("jpeg") ||
				extension.equals("png") || extension.equals("gif") ||
				extension.equals("bmp") || extension.equals("svg");
	}

	/**
	 * Validates if the provided birthdate indicates that the user is at least 13 years old.
	 * 
	 * @param birthdate The birthdate to be validated.
	 * @return True if the user is 13 years old or older, false otherwise.
	 */
	public static boolean isAtLeast13YearsOld(LocalDate birthdate) {
	    return Period.between(birthdate, LocalDate.now()).getYears() >= 13;
	}
}


