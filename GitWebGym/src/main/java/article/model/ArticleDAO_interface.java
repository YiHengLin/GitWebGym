package article.model;

import java.sql.SQLException;
import java.util.List;



public interface ArticleDAO_interface {
	public int PostArticle(ArticleBean ab) throws SQLException;
	public int ModifyArticle(ArticleBean ab)throws SQLException;
	public int DeletArticle(int ArticleNo) throws SQLException;
	public ArticleBean findbyArticleNO(int ArticleNo) throws SQLException;
	public List<ArticleBean> getAritcle() throws SQLException;

}
