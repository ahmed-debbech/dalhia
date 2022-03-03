package tn.dalhia.services;

import tn.dalhia.entities.Channel;
import tn.dalhia.entities.enumerations.ChannelType;

import java.util.List;

public interface IChannelService {
    List<Channel> getChannelsByType(ChannelType channelType);
    Channel addNewChannel(ChannelType channelType,  Channel channel);
    Channel getChannel(Long id);
    boolean deleteChannel(Long id);
    Channel modChannel(Long id, Channel channel);
}
