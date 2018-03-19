package com.amelin.shop.dao;

import com.amelin.shop.model.Account;
import com.amelin.shop.model.Comment;
import com.amelin.shop.model.Notebook;

import java.util.List;

public interface CommentDAO {
    void saveComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Comment comment);
    Comment findCommentById(int id);
    List<Comment> findCommentsByNotebook(Notebook notebook);
    List<Comment> findCommentsByAuthor(Account account);
}
