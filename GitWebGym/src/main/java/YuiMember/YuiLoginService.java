package YuiMember;

public class YuiLoginService {
	public YuiMemberBean checkIDPassword(String id, String password) {
		YuiMemberDAO yuimemberdao = new YuiMemberDAO();
		YuiMemberBean yuimemberbean = yuimemberdao.select(id);
		if (yuimemberbean != null && password.equals(yuimemberbean.getM_password())) {

			return yuimemberbean;
		}

		return null;

	}

}
