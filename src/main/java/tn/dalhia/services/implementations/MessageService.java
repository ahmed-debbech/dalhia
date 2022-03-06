package tn.dalhia.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Channel;
import tn.dalhia.entities.Message;
import tn.dalhia.entities.OwnedChannels;
import tn.dalhia.entities.User;
import tn.dalhia.entities.enumerations.Access;
import tn.dalhia.entities.enumerations.ChannelType;
import tn.dalhia.repositories.ChannelRepository;
import tn.dalhia.repositories.MessageRepository;
import tn.dalhia.repositories.OwnedChannelRepository;
import tn.dalhia.services.IMessageService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class MessageService implements IMessageService {

    @Autowired
    private OwnedChannelRepository ownedChannelRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChannelRepository channelRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;
    //repo of user

    @Autowired
    public MessageService(SimpMessagingTemplate messagingTemplate) {
        this.simpMessagingTemplate = messagingTemplate;
    }

    @Override
    public List<Message> getAllFromChannel(Long id_channel) {
        Channel vv = channelRepository.findById(id_channel).orElse(null);
        if(vv != null){
            //msg.setUser();
            return vv.getMessageList();
        }
        return null;
    }

    @Override
    public Message postNewMessage(Long id_channel, Message msg) {
        Channel vv = channelRepository.findById(id_channel).orElse(null);
        if(vv != null){
            msg.setDateSent(LocalDateTime.now());
            //msg.setUser();
            vv.getMessageList().add(msg);
            return msg;
        }
        return null;
    }

    @Override
    public void postNewMessageViaWebSocket(Message message) {
        System.err.println(message.toString());
        this.simpMessagingTemplate.convertAndSend("/main/g", message);
    }

    @Override
    public Message postFirstMessage(Long usr, Message msg) {
        List<OwnedChannels> allOwnedChannels = ownedChannelRepository.findAll();
        //we get the correspondant user
        //TODO implement this
        boolean found = false;
        for(OwnedChannels oc : allOwnedChannels){
            //if(oc.getUser() == token.id && oc.getUse2() == usr){
                found = true;
            //}
        }
        if(!found){
            // create new owned channel for both users
            Channel c = new Channel();
            c.setAccess(Access.PRIVATE);
            c.setChannelType(ChannelType.INDIV);
            //c.setName("id usr a + b");
            c.setDateCreated(LocalDateTime.now());
            c = channelRepository.save(c);
            OwnedChannels oc = new OwnedChannels();
            oc.setChannel(c);
            //oc.setUser(a);
            //oc.setUser2(b);
            ownedChannelRepository.save(oc);
            //and post first message
            this.postNewMessage(c.getId(), msg);
        }
        return null;
    }

    @Override
    public boolean deleteAndBan(Long msg_id) {
        Message vv = messageRepository.findById(msg_id).orElse(null);
        if(vv != null){
            vv.setBanned(true);
            vv.setDateRemoved(LocalDateTime.now());
            return true;
        }
        return false;
    }
}
