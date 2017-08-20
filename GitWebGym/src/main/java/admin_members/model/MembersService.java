package admin_members.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;



public class MembersService {

		private MembersDAO_interface dao;

		public MembersService() {

			dao = new MembersDAO();
		}
	
		public MembersBean addMem(String memberid, String m_password, String m_name, String m_id_number, String m_gender,
				java.sql.Date m_bday, String m_mail, String m_mobile,String m_tel,String m_address,java.sql.Date m_register,Blob m_photo,String is_blacklist,String m_identity) {

			MembersBean membersBean = new MembersBean();

			membersBean.setMemberid(memberid);
			membersBean.setM_password(m_password);
			membersBean.setM_name(m_name);
			membersBean.setM_id_number(m_id_number);
			membersBean.setM_gender(m_gender);
			membersBean.setM_bday(m_bday);
			membersBean.setM_mail(m_mail);
			membersBean.setM_mobile(m_mobile);
			membersBean.setM_tel(m_tel);
			membersBean.setM_address(m_address);
			membersBean.setM_register(m_register);
			membersBean.setM_photo(m_photo);
			membersBean.setIs_blacklist(is_blacklist);
			membersBean.setM_identity(m_identity);
			
			
			dao.insert(membersBean);

			return membersBean;
		}
		

		public void addMem(MembersBean membersBean) {

			dao.insert(membersBean);
		}
	
		public void updateMem(MembersBean membersBean) {

			dao.update(membersBean);
		}
		
	
		public void updateMem2(MembersBean membersBean) {

			dao.update2(membersBean);
		}
	
		public void deleteMem(String memberid) {
		dao.delete(memberid);
	}
		
	
		public MembersBean getOneMem(String memberid) {
			return dao.findByPrimaryKey(memberid);
		}

		public List<MembersBean> getAll() {
			return dao.getAll();
		}
		
	
		public List<MembersBean> getBlackList() {
			return dao.getBlackList();
		}
		
		public void updateNormal(MembersBean membersBean) {

			dao.updateNormal(membersBean);
		}

		public void updateBlackList(MembersBean membersBean) {

			dao.updateBlackList(membersBean);
		}

		
}
