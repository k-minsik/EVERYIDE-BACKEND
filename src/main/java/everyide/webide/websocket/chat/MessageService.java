package everyide.webide.websocket.chat;

import everyide.webide.websocket.chat.domain.Message;
import everyide.webide.websocket.chat.domain.MessageDto;
import everyide.webide.websocket.kafka.KafkaProperties;
import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

//    private final KafkaTemplate<String, Object> kafkaTemplate;
//    private final SimpMessagingTemplate simpMessagingTemplate;
//    private final MessageRepository messageRepository;
//
//    @Transactional
//    public void send(MessageDto messageDto, Long chatId) {
//        Message message = Message.builder()
//                .chatId(chatId)
//                .contentType(messageDto.getContentType())
//                .content(messageDto.getContent())
//                .senderId(messageDto.getSenderId())
//                .build();
//        messageRepository.save(message);
////        kafkaTemplate.send(KafkaProperties.KAFKA_TOPIC, messageDto);
//    }
//
////    @KafkaListener(topics = KafkaProperties.KAFKA_TOPIC)
//    public void receive(MessageDto messageDto) {
//        simpMessagingTemplate.convertAndSend("/topic/message/" + messageDto.getChatId(), messageDto);
//    }
}