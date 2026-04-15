package net.fahd.whatsappclone.message;

import net.fahd.whatsappclone.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(name = MessageConstant.FIND_MESSAGE_BY_CHAT_ID)
    List<Message>findMessageByChatId(String chatId);

    @Query(name = MessageConstant.SET_MESSAGES_TO_SEEN_BY_CHAT)
    @Modifying
    void setMessagesToSeenByChatId(@Param("chatId") String chatId, @Param("newState") MessageState messageState);
}
