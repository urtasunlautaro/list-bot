package com.urtasun.handler;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Handler {

  private List<String> list = new ArrayList<>();
  private final String addCommand = "/agregar";
  private final String listCommand = "/lista";
  private final String deleteCommand = "/borrar";

  public SendMessage handle(Update update) {
    String command = update.getMessage().getText();
    Long chatId = update.getMessage().getChatId();
    if (command.contains(addCommand)) {
      return this.generateMessage(chatId, this.addItem(update));
    }
    if (command.contains(listCommand)) {
      return this.generateMessage(chatId, this.returnList());
    }
    if (command.contains(deleteCommand)) {
      return this.generateMessage(chatId, this.removeItem(update));
    } else {
      return this.generateMessage(chatId, "No se reconoció el comando");
    }
  }

  private String addItem(Update update) {
    String item = this.retrieveItem(update);
    if (list.contains(item)) {
      return "El ítem ya estaba en la lista";
    } else {
      list.add(item);
      return "Se agregó '" + item + "' a la lista";
    }
  }

  private String removeItem(Update update) {
    String item = this.retrieveItem(update);
    if (list.contains(item)) {
      list.remove(item);
      return "Se eliminó '" + item + "' de la lista";
    } else {
      return "El ítem '" + item + "' no existe en la lista";
    }
  }

  private String returnList() {
    StringBuilder itemList = new StringBuilder();
    list.forEach(item -> itemList.append("-").append(item).append("\n"));
    return itemList.toString();
  }

  private String retrieveItem(Update update) {
    String message = update.getMessage().getText();
    if (message.contains(addCommand)) {
      return message.substring(addCommand.length() + 1);
    } else {
      return message.substring(deleteCommand.length() + 1);
    }
  }

  private SendMessage generateMessage(Long chatId, String text) {
    return new SendMessage(chatId.toString(), text);
  }

}
