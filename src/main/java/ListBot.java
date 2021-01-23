import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ListBot extends TelegramLongPollingBot {

  private final Handler handler = new Handler();

  public String getBotUsername() {
    return "personal_list_bot";
  }

  public String getBotToken() {
    return "1551953970:AAGQfrmIr-ylLVAQW3GhpedW8lEdzHVjitk";
  }

  public void onUpdateReceived(Update update) {
    this.handler.handle(update);
  }
}
