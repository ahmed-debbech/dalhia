package tn.dalhia.services;

import tn.dalhia.entities.CommentReaction;
import tn.dalhia.entities.ForumComment;

import java.util.List;

public interface IForumCommentService {
    ForumComment add(ForumComment comment, Long id);
    List<ForumComment> getAll(Long id);
    ForumComment modify(ForumComment comment, Long id);
    boolean delete(Long id);
    ForumComment get(Long id);
    ForumComment bans(Long id, boolean action);
    List<ForumComment> getReplies(Long id);
    ForumComment reply(Long id);
    ForumComment getReply(Long rep_id);
    ForumComment modifyReply(Long rep_id);
    ForumComment deleteReply(Long rep_id);
    CommentReaction react(Long id);
    List<CommentReaction> getAllReactions(Long id);
    boolean deleteReaction(Long id);
}
