package admin_members.model;

import java.util.List;

import admin_event.model.EventBean;

public interface MembersDAO_interface {

	void insert(MembersBean membersBean);

	void update(MembersBean membersBean);

	void delete(String memberid);

	MembersBean findByPrimaryKey(String memberid);

	List<MembersBean> getAll();

	void update2(MembersBean membersBean);

	List<MembersBean> getBlackList();

	void updateBlackList(MembersBean membersBean);

	void updateNormal(MembersBean membersBean);

	void delete(MembersBean membersBean);







	
	
	///////////////////////////////////////////


}
