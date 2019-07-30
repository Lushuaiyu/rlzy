package com.nado.rlzy.utils.telMessage;

public class SendMsgUtil {
    /**
     * 获取
     *
     * @param phone 发短信的电话号码
     * @param vailedate 短信的关键内容
     * @param type 短信内容（可让产品发下模版内容，对照看下）
     * @return 如果发短信需要的保存的则会保存在线程中，具体讲解可查看交接文档中，
     * 有时间可以把下面的关键静态变量放在其他位置，保证代码的规范性。
     */
	/*public static int send(String phone, String vailedate, String type) {
		String time = VaildateType.getTimeByType(type);
		String sendId = VaildateType.getModelIdByType(type);
		if (time == null || sendId == null) {
			return 1;
		}
		HashMap<String, Object> result = null;

		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		restAPI.init("app.cloopen.com", "8883");
		restAPI.setAccount("8aaf07086077a6e60160915d106c0b61", "f17b155c090a4f7a806c6683547e597f");//开发者主账号获得，后面一个为token
		restAPI.setAppId("8a216da86077dcd00160915db2180be7");//应用管理获得
		result = restAPI.sendTemplateSMS(phone, sendId, new String[] {vailedate});
		 System.out.println("SDKTestGetSubAccounts result=" + result);
		 System.out.println("mobanid" + sendId);
		if ("000000".equals(result.get("statusCode"))) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
			@SuppressWarnings("unchecked")
			HashMap<String, Object> templateSMS = (HashMap<String, Object>) data.get("templateSMS");
			if (type.equals("sendPassword") || type.equals("ordernotice") || type.equals("pointsintoaccount")) {
			} else {
				Title t = TitleDateUtil.getTitle(phone, (String) templateSMS.get("dateCreated"), type, vailedate, time);
				ValidateMsgUtil.addTitle(t);
			}
			return 0;
		} else {
			 System.out.println("错误码=" + result.get("statusCode") +" 错误信息=	 "+result.get("statusMsg"));

			return 2;
		}
	}*/

}
