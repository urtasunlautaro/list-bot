package com.urtasun.bot;

import com.urtasun.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class ListBot extends TelegramLongPollingBot {

  @Value("${com.urtasun.bot-username}")
  private String username;
  @Value("${com.urtasun.bot-token}")
  private String token;
  private final Handler handler;

  public String getBotUsername() {
    return username;
  }

  public String getBotToken() {
    return token;
  }

  public void onUpdateReceived(Update update) {
    this.sendMessage(this.handler.handleUpdate(update));
  }

  private void sendMessage(SendMessage sendMessage) {
    try {
      execute(sendMessage);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }


}
