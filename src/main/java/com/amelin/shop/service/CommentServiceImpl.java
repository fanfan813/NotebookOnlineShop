package com.amelin.shop.service;

import com.amelin.shop.dao.CommentDAO;
import com.amelin.shop.model.Account;
import com.amelin.shop.model.Comment;
import com.amelin.shop.model.Notebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CommentService")
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDAO dao;

    public void saveComment(Comment comment) {
        dao.saveComment(comment);
    }

    public void updateComment(Comment comment) {
        dao.updateComment(comment);
    }

    public void deleteComment(Comment comment) {
        dao.deleteComment(comment);
    }

    public Comment findCommentById(int id) {
        return dao.findCommentById(id);
    }

    public List<Comment> findCommentsByNotebook(Notebook notebook) {
        return dao.findCommentsByNotebook(notebook);
    }

    public List<Comment> findCommentsByAuthor(Account account) {
        return dao.findCommentsByAuthor(account);
    }
}
