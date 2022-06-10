package com.mamay.inspection.constant;

public interface FormParameter {
	// id
	String USER_ID = "user_id";
	String MAG_ID = "mag_id";
	String SUB_ID = "sub_id";
	String RES_ID = "res_id";
	String STATUS_ID = "status_id";

	// user params
	String USER = "user";
	String ROLE = "role";
	String USERNAME = "userName";
	String LOGIN = "login";
	String PASSWORD = "password";
	String PASS_CONFIRM = "passwordConfirm";
	String AGE = "age";

	// magazine params
	String MAGAZINE = "mag";
	String MAG_LIST = "magList";
	String TITLE = "title";
	String ANNOTATION = "annotation";
	String PERIOD_ID = "period_id";
	String LOCATION = "location";

	// subscription params
	String SUBSCRIPTION = "sub";
	String SUB_LIST = "subList";
	String INDEX = "index";
	String DURATION = "duration";
	String PRICE = "price";

	// errors
	String LOGIN_ERROR = "loginError";
	String USERNAME_ERROR = "userNameError";
	String PASSWORD_ERROR = "passwordError";
	String PASS_CONFIRM_ERROR = "passwordConfirmError";
	String AGE_ERROR = "ageError";
	String TITLE_ERROR = "titleError";
	String ANNOTATION_ERROR = "annotationError";
	String PERIODICITY_ERROR = "periodicityError";
	String PRICE_ERROR = "priceError";
	String DURATION_ERROR = "durationError";
	String INDEX_ERROR = "indexError";
	
	// messages
	String SUCCESS_MESSAGE = "successMessage";

	// pagination params
	String SEQUENCE = "sequence"; // page number
	String PAGE_COUNT = "pageCount";
	String PER_PAGE = "perPage"; // default amount per page

	// locale
	String LANGUAGE = "language";

	String COUNT = "count";

	String RES_LIST = "resList";
	String PERIOD_LIST = "periodList";
	String COMMAND = "command";

}
