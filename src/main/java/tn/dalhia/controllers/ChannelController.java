package tn.dalhia.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.Channel;
import tn.dalhia.entities.TopicClaim;
import tn.dalhia.entities.enumerations.ChannelType;
import tn.dalhia.services.IChannelService;

import java.util.List;

@RestController
@RequestMapping("/forum/channels")
@RequiredArgsConstructor
@Slf4j
public class ChannelController {

    @Autowired
    private IChannelService channelService;

    @PostMapping("/{type}")
    public ResponseEntity<Channel> newchannel(@RequestBody Channel channel, @PathVariable("type") ChannelType channelType){
        Channel b = channelService.addNewChannel(channelType, channel);
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
    @GetMapping("/q/{id}")
    public ResponseEntity<Channel> getChannel(@PathVariable("id") Long id){
        Channel b = channelService.getChannel(id);
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
    @GetMapping("/{type}")
    public ResponseEntity<List<Channel>> getCHannelByType(@PathVariable("type") ChannelType channelType){
        List<Channel> b = channelService.getChannelsByType(channelType);
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
    @DeleteMapping("/q/{id}")
    public ResponseEntity<Boolean> deleteChannel(@PathVariable("id") Long id){
        boolean b = channelService.deleteChannel(id);
        if(!b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    false
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                true
        );
    }
    @PutMapping("/q/{id}")
    public ResponseEntity<Channel> modChannel(@PathVariable("id") Long id, @RequestBody Channel channel){
        Channel b = channelService.modChannel(id, channel);
        if(b == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    null
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                b
        );
    }
}
