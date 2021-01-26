package com.urtasun.bot;

import com.urtasun.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class ListBot extends TelegramLongPollingBot {

  private final Handler handler;

  public String getBotUsername() {
    return "personal_list_bot";
  }

  public String getBotToken() {
    return "1551953970:AAGQfrmIr-ylLVAQW3GhpedW8lEdzHVjitk";
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
