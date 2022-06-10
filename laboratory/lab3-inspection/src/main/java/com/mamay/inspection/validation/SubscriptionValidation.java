package com.mamay.inspection.validation;

import javax.servlet.http.HttpServletRequest;

import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.utility.Checker;

public class SubscriptionValidation {
	private static final int PRICE_MIN = 10;
	private static final int PRICE_MAX = 100_000_000;
	private static final int INDEX_LENGTH = 5;

	public static boolean checkInputParameters(HttpServletRequest request) {
		boolean flag = true;
		String index = request.getParameter(FormParameter.INDEX);
		String duration = request.getParameter(FormParameter.DURATION);
		String price = request.getParameter(FormParameter.PRICE);
		String emptyField = ActionHelper.findProperty(request, "error.empty");
		if (checkEmpty(duration)) {
			request.setAttribute(FormParameter.DURATION_ERROR, emptyField);
			flag = false;
		}
		if (!checkPrice(price)) {
			String priceError = ActionHelper.findProperty(request, "error.price");
			request.setAttribute(FormParameter.PRICE_ERROR, priceError);
			flag = false;
		}
		if (!checkIndex(index)) {
			String indexError = ActionHelper.findProperty(request, "error.index");
			request.setAttribute(FormParameter.INDEX_ERROR, indexError);
			flag = false;
		}
		return flag;
	}

	private static boolean checkEmpty(String field) {
		return field == null || "".equals(field.trim());
	}

	private static boolean checkIndex(String field) {
		if (!Checker.isInteger(field)) {
			return false;
		}
		boolean flag = true;
		int index = Integer.parseInt(field);
		if (!Checker.inRange(INDEX_LENGTH, index)) {
			flag = false;
		}
		return flag;
	}

	private static boolean checkPrice(String field) {
		if (!Checker.isInteger(field)) {
			return false;
		}
		int price = Integer.parseInt(field);
		return price >= PRICE_MIN && price <= PRICE_MAX;
	}
}
