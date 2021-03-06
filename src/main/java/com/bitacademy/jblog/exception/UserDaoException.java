package com.bitacademy.jblog.exception;

import com.bitacademy.jblog.repository.UserVo;

public class UserDaoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	// field
	private UserVo vo = null;
	
	public UserDaoException () {
		super();
	}
	public UserDaoException (String message) {
		super(message);
	}
	public UserDaoException (String message, UserVo vo) {
		super(message);
		this.vo = vo;
	}
	public UserVo getVo() {
		return vo;
	}
	public void setVo(UserVo vo) {
		this.vo = vo;
	}
	@Override
	public String toString() {
		return "UserDaoException [vo=" + vo + "]";
	}

}
