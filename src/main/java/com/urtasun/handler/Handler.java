package com.urtasun.handler;

import com.urtasun.enums.Command;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Handler {

  private List<String> list = new ArrayList<>();

  public SendMessage handleUpdate(Update update) {
    String command = update.getMessage().getText();
    Long chatId = update.getMessage().getChatId();
    if (command.contains(Command.ADD.getText())) {
      return this.generateMessage(chatId, this.addItem(update));
    }
    if (command.contains(Command.LIST.getText())) {
      return this.generateMessage(chatId, this.returnList());
    }
    if (command.contains(Command.DELETE.getText())) {
      return this.generateMessage(chatId, this.removeItem(update));
    }
    if (command.contains(Command.CLEAN.getText())) {
      return this.generateMessage(chatId, this.cleanList());
    } else {
      return this.generateMessage(chatId, "No se reconoció el comando");
    }
  }

  private String addItem(Update update) {
    String item = this.retrieveItem(update);
    if (list.contains(item)) {
      log.debug("El ítem {} ya estaba en la lista", item);
      return "El ítem ya estaba en la lista";
    } else {
      list.add(item);
      log.debug("Se agregó '{}' a la lista", item);
      return "Se agregó '" + item + "' a la lista";
    }
  }

  private String removeItem(Update update) {
    String item = this.retrieveItem(update);
    if (list.contains(item)) {
      list.remove(item);
      log.debug("Se eliminó '{}' de la lista", item);
      return "Se eliminó '" + item + "' de la lista";
    } else {
      log.debug("El ítem '{}' no existe en la lista", item);
      return "El ítem '" + item + "' no existe en la lista";
    }
  }

  private String returnList() {
    if (list.isEmpty()) {
      String message = "La lista está vacía";
      log.debug(message);
      return message;
    } else {
      StringBuilder itemList = new StringBuilder();
      list.forEach(item -> itemList.append("-").append(item).append("\n"));
      log.debug("Devolviendo lista");
      return itemList.toString();
    }
  }

  private String cleanList() {
    list.clear();
    return "Se limpió la lista";
  }

  private String retrieveItem(Update update) {
    String message = update.getMessage().getText();
    if (message.contains(Command.ADD.getText())) {
      return message.substring(Command.ADD.getText().length() + 1);
    } else {
      return message.substring(Command.DELETE.getText().length() + 1);
    }
  }

  private SendMessage generateMessage(Long chatId, String text) {
    return new SendMessage(chatId.toString(), text);
  }

}
