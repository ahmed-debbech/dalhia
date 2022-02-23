package tn.dalhia.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.CommentReaction;
import tn.dalhia.entities.ForumComment;
import tn.dalhia.entities.Topic;
import tn.dalhia.repositories.ForumCommentRepository;
import tn.dalhia.repositories.TopicClaimRepository;
import tn.dalhia.repositories.TopicRepository;
import tn.dalhia.services.IForumCommentService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ForumCommentService implements IForumCommentService {

    @Autowired
    private ForumCommentRepository repository;
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public ForumComment add(ForumComment comment, Long id) {
        Topic c = topicRepository.findById(id).orElse(null);
        if(c == null){
            return null;
        }
        comment.setBanned(false);
        comment.setDatePublished(LocalDateTime.now());
        c.getForumComments().add(comment);
        topicRepository.save(c);
        return comment;
    }

    @Override
    public List<ForumComment> getAll(Long id) {
        Topic c = topicRepository.findById(id).orElse(null);
        if(c == null){
            return null;
        }
        return c.getForumComments();
    }

    @Override
    public ForumComment modify(ForumComment comment, Long id) {
        ForumComment c = repository.findById(id).orElse(null);
        if(c == null){
            return null;
        }
        c.setText(comment.getText());
        return repository.save(c);
    }

    @Override
    public boolean delete(Long id) {
        ForumComment c = repository.findById(id).orElse(null);
        if(c != null){
            c.setDateRemoved(LocalDateTime.now());
            repository.save(c);
            return true;
        }
        return false;
    }

    @Override
    public ForumComment get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ForumComment bans(Long id, boolean action) {
        ForumComment c = repository.findById(id).orElse(null);
        if(c != null){
            if(action) {
                c.setDateRemoved(LocalDateTime.now());
            }
            c.setBanned(action);
            return repository.save(c);
        }
        return null;
    }

    @Override
    public List<ForumComment> getReplies(Long id) {
        return null;
    }

    @Override
    public ForumComment reply(Long id) {
        return null;
    }

    @Override
    public ForumComment getReply(Long rep_id) {
        return null;
    }

    @Override
    public ForumComment modifyReply(Long rep_id) {
        return null;
    }

    @Override
    public ForumComment deleteReply(Long rep_id) {
        return null;
    }

    @Override
    public CommentReaction react(Long id) {
        return null;
    }

    @Override
    public List<CommentReaction> getAllReactions(Long id) {
        return null;
    }

    @Override
    public boolean deleteReaction(Long id) {
        return false;
    }
}
