package net.fahd.whatsappclone.chat;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.fahd.whatsappclone.commun.StringResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "/api/v1/chats",
        produces = MediaType.APPLICATION_JSON_VALUE // <--- S'applique à TOUTES les méthodes
)
@Tag(name = "Chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<StringResponse> createChat (
            @RequestParam(name = "sender-id") String senderId,
            @RequestParam(name = "receiver-id") String receiverId
    ) {
        final String chatId = chatService.createChat(senderId, receiverId);
        StringResponse response = StringResponse.builder()
                .response(chatId)
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatByReceiver (
            Authentication authentication
    ) {
        return  ResponseEntity.ok(chatService.getChatByReceiverId(authentication));
    }
}
