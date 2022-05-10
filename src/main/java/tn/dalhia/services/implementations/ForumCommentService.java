package tn.dalhia.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.*;
import tn.dalhia.repositories.*;
import tn.dalhia.services.IForumCommentService;
import tn.dalhia.shared.tools.UtilsUser;
import tn.dalhia.utils.CommentUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ForumCommentService implements IForumCommentService {

    @Autowired
    private ForumCommentRepository repository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CommentReactionRepository commentReactionRepository;
    @Autowired
    private OffensiveWordRepository offensiveWordRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UtilsUser utilsUser;

    @Override
    public ForumComment add(ForumComment comment, Long id) {
        Topic c = topicRepository.findById(id).orElse(null);
        if(c == null){
            return null;
        }
        if(!CommentUtils.isFine(comment.getText(), offensiveWordRepository.findAll())){
            comment.setBanned(true);
            LocalDateTime now = LocalDateTime.now();
            comment.setDateRemoved(now);
            comment.setDatePublished(now);
        }else {
            comment.setBanned(false);
            comment.setDatePublished(LocalDateTime.now());
        }
        User logg = utilsUser.getLoggedInUser();
        comment.setUser(logg);
        c.getForumComments().add(comment);
        topicRepository.save(c);
        return comment;
    }

    @Override
    public List<ForumComment> getAll(Long id) {
        Topic c = topicRepository.findById(id).orElse(null);
        if(c == null) {
            return null;
        }
        List<ForumComment> cmt = new ArrayList<>();
        for(ForumComment fc : c.getForumComments()){
            if(!fc.isBanned()){
                System.err.println("gggg");
                cmt.add(fc);
            }
        }
        return cmt;
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
        ForumComment c = repository.findById(id).orElse(null);
        if(c != null){
            return c.getReplies();
        }
        return null;
    }

    @Override
    public ForumComment reply(ForumComment comment, Long id) {
        ForumComment c = repository.findById(id).orElse(null);
        if(c != null){
            comment.setDatePublished(LocalDateTime.now());
            User logg = utilsUser.getLoggedInUser();
            comment.setUser(logg);
            c.getReplies().add(comment);
            return repository.save(c);
        }
        return null;
    }

    @Override
    public ForumComment getReply(Long rep_id) {
        return repository.findById(rep_id).orElse(null);
    }

    @Override
    public ForumComment modifyReply(ForumComment comment, Long rep_id) {
        ForumComment c = repository.findById(rep_id).orElse(null);
        if(c != null){
            c.setText(comment.getText());
            return repository.save(c);
        }
        return null;
    }

    @Override
    public ForumComment deleteReply(Long rep_id) {
        ForumComment c = repository.findById(rep_id).orElse(null);
        if(c != null){
            c.setDateRemoved(LocalDateTime.now());
            return repository.save(c);
        }
        return null;
    }

    @Override
    public CommentReaction react(CommentReaction reaction, Long id) {
        ForumComment fc = repository.findById(id).orElse(null);
        if(fc != null){
            reaction.setDate(LocalDateTime.now());
            User logg = utilsUser.getLoggedInUser();
            reaction.setUser(logg);
            fc.getCommentReactionList().add(reaction);
            repository.save(fc);
            return reaction;
        }
        return null;
    }

    @Override
    public List<CommentReaction> getAllReactions(Long id) {
        ForumComment fc = repository.findById(id).orElse(null);
        if(fc != null){
            return fc.getCommentReactionList();
        }
        return null;
    }

    @Override
    public boolean deleteReaction(Long id) {
        ForumComment fc = repository.findById(id).orElse(null);
        if(fc != null){
            User loggedin = utilsUser.getLoggedInUser();
            CommentReaction found = null;
            for(CommentReaction cr : fc.getCommentReactionList()){
                if(cr.getUser().getId() == loggedin.getId()){
                    found = cr;
                    break;
                }
            }
            if(found != null){
                commentReactionRepository.delete(found);
            }
            return true;
        }
        return false;
    }
}
