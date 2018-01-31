package com.tcm.college.utill;

public class Constant {

	private Constant() {

	}

	public static class IRV {

		private IRV() {

		}

		public static final String LOGIN = "login";
		public static final String MANAGE_CONSUMER = "manage_consumer";
		public static final String ERROR = "error/error";
		public static final String CREATE_CONTENT = "create_content";
		public static final String VIEW_CONTENT = "view_content";
	}

	public static class ROLE {
		private ROLE() {

		}

		public static final String SUPER_ADMIN = "ROLE_SUPER_ADMIN";
		public static final String INSTRUCTOR = "ROLE_INSTRUCTOR";
		public static final String STUDENT = "ROLE_STUDENT";
	}

	public static class URL {
		private URL() {

		}

		public static final String ROOT = "/";
		public static final String LOGIN = "login.do";
		public static final String AUTHENTICATE = "authenticate.do";
		public static final String MANAGE_CONSUMER = "manageConsumer.do";
		public static final String CREATE_CONTENT = "createContent.do";
		public static final String VIEW_CONTENT = "viewContent.do";
		public static final String CREATE_CONSUMER = "createConsumer.do";

	}

	public static class RA {
		private RA() {

		}

		public static final String ERR = "ra_err";
		public static final String CONSUMER = "ra_consumer";
	}

	public static class SA {
		private SA() {

		}

		public static final String CONSUMER_NAME = "sa_consumer_name";
		public static final String CONSUMER_PRECEDENCE = "sa_consumer_precedence";
		public static final String CONSUMER_ID = "sa_consumer_id";
		public static final String CONSUMER_ROLE_LIST = "sa_consumer_role_list";

	}

	public static class REGEX {
		private REGEX() {

		}
		
		public static final String STRING_VALIDATE = "^[0-9A-Za-z_\\s]+$";
		public static final String EMAIL_VALIDATE = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	}

	public static final String AUTH_SUCCESS = "AS";
	public static final String AUTH_FAIL = "AF";

	public static final int CONSUMER_ACTIVE = 1;
	public static final int CONSUMER_INACTIVE = 0;

	public static final String NOT_AVALIABLE = "NA";

}
