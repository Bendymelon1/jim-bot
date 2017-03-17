import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;

public class BotMain {
    public static void main(String[] args){
        try {
            ClientBuilder clientBuilder = new ClientBuilder();
            clientBuilder.withToken("");
            IDiscordClient client = clientBuilder.build();
            client.login(false);

            EventDispatcher dispatcher = client.getDispatcher();
            dispatcher.registerListener(new ActivityListener());

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
