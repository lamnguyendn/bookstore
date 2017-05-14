package model.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.ThanhToanException;
import model.beans.Comment;
import model.dao.CommentDAO;

public class CommentBO {
	CommentDAO commentDAO = new CommentDAO();

	public List<Comment> getListOfCommentsByIsbn(String isbn) {
		return commentDAO.getListOfCommentsByIsbn(isbn);
	}

	public void insertComment(Comment comment, HttpServletRequest request) throws ThanhToanException {
		commentDAO.insertComment(comment, request);
	}

	public List<Comment> getListOfComments() {
		return commentDAO.getListOfComments();
	}

	public void deleteComment(int ma_BL, HttpServletRequest request) throws ThanhToanException {
		commentDAO.deleteComment(ma_BL, request);
	}

}
