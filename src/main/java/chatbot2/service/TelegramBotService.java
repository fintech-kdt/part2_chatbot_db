package chatbot2.service;

import java.io.IOException;
import java.util.List;

import chatbot2.model.*;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelegramBotService {
    private static final String BASE_URL = "https://api.telegram.org/bot";
    private static final String BOT_TOKEN = "";
    private final TelegramBotApi api;

    public TelegramBotService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL + BOT_TOKEN + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.api = retrofit.create(TelegramBotApi.class);
    }

    public List<Update> getUpdates(long offset) throws IOException {
        Call<ApiResponse<List<Update>>> call = api.getUpdates(offset);
        ApiResponse<List<Update>> response = call.execute().body();
        return response != null ? response.getResult() : null;
    }

    public Message sendMessage(long chatId, String text) throws IOException {
        Call<ApiResponse<Message>> call = api.sendMessage(chatId, text);
        ApiResponse<Message> response = call.execute().body();
        return response != null ? response.getResult() : null;
    }
}
