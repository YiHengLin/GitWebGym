package admin_mail.model;

import java.sql.SQLException;
import java.util.*;



public interface MailDAO_interface {
	
	public int sendMail(MailBean mb) throws SQLException;
	
	public MailBean findByPrimaryKey(int mailno) throws SQLException;
	
	public int deleteMail (int mailno) throws SQLException;
    
	public List<MailBean> getMails() throws SQLException;

}

// end of interface MailDAO 