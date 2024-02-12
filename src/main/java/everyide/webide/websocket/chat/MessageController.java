package everyide.webide.websocket.chat;

import everyide.webide.websocket.chat.domain.Message;
import everyide.webide.websocket.chat.domain.MessageDto;
import everyide.webide.websocket.kafka.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Controller
@RequiredArgsConstructor
public class MessageController {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageRepository messageRepository;

    @Transactional
    @MessageMapping("/room/{roomId}/chat")
    public void message(MessageDto messageDto, @DestinationVariable String roomId) {
        Message message = Message.builder()
                .roomId(roomId)
                .contentType(messageDto.getContentType())
                .content(messageDto.getContent())
                .senderId(messageDto.getSenderId())
                .build();
        messageRepository.save(message);
        kafkaTemplate.send(KafkaProperties.CHAT_TOPIC, messageDto);
    }

    @KafkaListener(topics = KafkaProperties.CHAT_TOPIC)
    public void receive(MessageDto messageDto) {
        simpMessagingTemplate.convertAndSend("/topic/room/" + messageDto.getRoomId() + "/chat", messageDto);
    }
}