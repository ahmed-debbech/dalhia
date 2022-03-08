package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.CommentReaction;
import tn.dalhia.entities.ForumComment;
import tn.dalhia.entities.TopicClaim;
import tn.dalhia.repositories.ForumCommentRepository;
import tn.dalhia.services.implementations.ForumCommentService;

import java.util.List;

@RestController
@RequestMapping("/forum")
@RequiredArgsConstructor
@Slf4j
public class ForumCommentController {

    @Autowired
    private ForumCommentService commentService;

    @GetMapping("/topics/{id}/comments")
    public ResponseEntity<List<ForumComment>> getAllComments(@PathVariable("id") Long id){
        List<ForumComment> tc = commentService.getAll(id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
    @PostMapping("/topics/{id}/comments")
    public ResponseEntity<ForumComment> writeComment(@RequestBody ForumComment forumComment, @PathVariable("id") Long id){
        ForumComment tc = commentService.add(forumComment, id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
    @PutMapping("/comments/{cmt_id}")
    public ResponseEntity<ForumComment> modComment(@RequestBody ForumComment forumComment, @PathVariable("cmt_id") Long id){
        ForumComment tc = commentService.modify(forumComment, id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
    @DeleteMapping("/comments/{cmt_id}")
    public ResponseEntity<Boolean> delComment(@PathVariable("cmt_id") Long id){
        boolean tc = commentService.delete(id);
        if(!tc){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @GetMapping("/comments/{cmt_id}")
    public ResponseEntity<Boolean> getComment(@PathVariable("cmt_id") Long id){
        ForumComment tc = commentService.get(id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @PutMapping("/comments/{cmt_id}/bans")
    public ResponseEntity<Boolean> bans(@PathVariable("cmt_id") Long id){
        ForumComment tc = commentService.bans(id, true);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @DeleteMapping("/comments/{cmt_id}/bans")
    public ResponseEntity<Boolean> removeBan(@PathVariable("cmt_id") Long id){
        ForumComment tc = commentService.bans(id, false);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @GetMapping("/comments/{cmt_id}/replies")
    public ResponseEntity<List<ForumComment>> getReplies(@PathVariable("cmt_id") Long id){
        List<ForumComment> tc = commentService.getReplies(id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
    @PostMapping("/comments/{cmt_id}/replies")
    public ResponseEntity<ForumComment> writeReplies(@RequestBody ForumComment comment, @PathVariable("cmt_id") Long id){
        ForumComment tc = commentService.reply(comment, id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
    @PutMapping("/replies/{rep_id}")
    public ResponseEntity<ForumComment> modReply(@RequestBody ForumComment comment, @PathVariable("rep_id") Long id){
        ForumComment tc = commentService.modifyReply(comment, id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
    @GetMapping("/replies/{rep_id}")
    public ResponseEntity<ForumComment> getReply(@PathVariable("rep_id") Long id){
        ForumComment tc = commentService.getReply(id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
    @DeleteMapping("/replies/{rep_id}")
    public ResponseEntity<ForumComment> deleteReply(@PathVariable("rep_id") Long id){
        ForumComment tc = commentService.deleteReply(id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
    @PostMapping("/comments/{cmt_id}/reactions")
    public ResponseEntity<Boolean> addReaction(@RequestBody CommentReaction reaction, @PathVariable("cmt_id") Long id){
        CommentReaction tc = commentService.react(reaction, id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @DeleteMapping("/comments/{cmt_id}/reactions")
    public ResponseEntity<Boolean> deleteReaction(@PathVariable("cmt_id") Long id){
        boolean tc = commentService.deleteReaction(id);
        if(!tc){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @GetMapping("/comments/{cmt_id}/reactions")
    public ResponseEntity<List<CommentReaction>> getAllreactions(@PathVariable("cmt_id") Long id){
        List<CommentReaction> tc = commentService.getAllReactions(id);
        if(tc == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                tc
        );
    }
}
