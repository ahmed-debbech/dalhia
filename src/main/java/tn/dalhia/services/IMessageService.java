package tn.dalhia.services;

import tn.dalhia.entities.Message;

import java.util.List;

public interface IMessageService {
    List<Message> getAllFromChannel(Long id_channel);
    Message postNewMessage(Long id_channel, Message msg);
    void postNewMessageViaWebSocket(Message message);
    Message postFirstMessage(Long usr, Message msg);
    boolean deleteAndBan(Long msg_id);
}
