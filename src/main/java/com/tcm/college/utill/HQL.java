package com.tcm.college.utill;

public final class HQL {

	private HQL() {

	}

	public static final String SELECT_CONSUMER_BY_EMAIL = "FROM Consumer c WHERE c.email = :email";
	public static final String UPDATE_CONSUMER_LOGIN_FAIL_AS_ZERO = "UPDATE Consumer c SET c.invalidLoginCount = 0 WHERE c.email = :email";
	public static final String UPDATE_CONSUMER_STATUS_WITH_LOGIN_FAIL_COUNT = "UPDATE Consumer c SET c.invalidLoginCount = "
																				+ ":invalCount, c.status = :status WHERE c.email = :email";
	public static final String UPDATE_CONSUMER_LOGIN_FAIL_COUNT = "UPDATE Consumer c SET c.invalidLoginCount = c.invalidLoginCount + 1 WHERE c.email = :email";
	public static final String SELECT_ALL_CONSUMER_ROLE_LIST = "FROM ConsumerRole cr";
	public static final String SELECT_CONSUMER_ROLE_LIST = "FROM ConsumerRole cr WHERE cr.roleName != '"+ Constant.ROLE.SUPER_ADMIN +"' ORDER BY cr.roleName";
}
