package model.bo;

import java.util.List;

import model.beans.Comment;
import model.dao.CommentDAO;

public class CommentBO {
	CommentDAO commentDAO = new CommentDAO();

	public List<Comment> getListOfComments(String isbn) {
		return commentDAO.getListOfComments(isbn);
	}

	public void insertComment(Comment comment) {
		commentDAO.insertComment(comment);
	}

}
