package com.amelin.shop.dao;

import com.amelin.shop.model.Account;
import com.amelin.shop.model.Comment;
import com.amelin.shop.model.Notebook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("CommentDAO")
@Transactional
public class CommentDAOImpl extends AbstractDAO<Integer, Comment> implements CommentDAO {
    public void saveComment(Comment comment) {
        persist(comment);
    }

    public void updateComment(Comment comment) {
        getSession().update(comment);
    }

    public void deleteComment(Comment comment) {
        getSession().delete(comment);
    }

    public Comment findCommentById(int id) {
        Criteria criteria = getSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("comment_id", id));
        return (Comment)criteria.uniqueResult();
    }

    public List<Comment> findCommentsByNotebook(Notebook notebook) {
        Query query = getSession().createQuery("FROM Comment c JOIN FETCH c.parentComment JOIN FETCH c.accountId WHERE c.notebookId = :id");
        query.setParameter("id", notebook);
        return (List<Comment>)query.list();
    }

    public List<Comment> findCommentsByAuthor(Account account) {
        Query query = getSession().createQuery("SELECT c FROM Comment c WHERE accountId = :id");
        query.setParameter("id", account);
        return (List<Comment>)query.list();
    }
}
