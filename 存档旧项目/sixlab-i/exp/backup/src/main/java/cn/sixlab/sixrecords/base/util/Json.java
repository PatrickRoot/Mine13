/**
 * @Copyright © Sixlab 2014
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.base.util;

import java.util.HashMap;

/**
 *
 *
 * @author 六楼的雨/loki
 * @date 2014/12/28 19:25
 */
public class Json extends HashMap {

	private Integer code;
	private String success = "1";
	private String message;
	
	public Json() {
		setSuccess("1");
		setMessage("操作成功");
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		put("code", code);
		this.code = code;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		put("success", success);
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		put("message", message);
		this.message = message;
	}
	
	public void put(String key,Object value) {
		super.put(key, value);
	}
}
