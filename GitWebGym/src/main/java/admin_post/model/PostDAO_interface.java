package admin_post.model;

import java.sql.SQLException;
import java.util.List;



public interface PostDAO_interface {

	int sendPost(PostBean pb) throws SQLException;
	
	PostBean findByPrimaryKey(int postno) throws SQLException;
	
	int deletePost(int postno) throws SQLException;

	List<PostBean> getPosts() throws SQLException;

	

	



}
