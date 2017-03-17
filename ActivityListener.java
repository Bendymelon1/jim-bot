import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class ActivityListener {

    String[][] options = {
            { ".dmoj id <id>",          "Queries problem information by ID" },
            { ".dmoj user <username>",  "Queries user information by username"}
    };

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        IMessage message = event.getMessage();
        if (!message.getContent().startsWith(".")) return;

        try {
            if (message.getContent().toLowerCase().startsWith(".ping")) {
                message.getChannel().sendMessage("pong.");
            } else if (message.getContent().toLowerCase().startsWith(".dmoj")){
                String[] tokens = message.getContent().toLowerCase().split("\\s+");
                if (tokens.length > 2 && tokens[1].equals("id")) {
                    DMOJProblem problem = DMOJ.getProblemById(tokens[2]);
                    message.getChannel().sendMessage("```" + problem.toString() + "```");
                } else if (tokens.length > 2 && tokens[1].equals("user")){
                    DMOJUser user = DMOJ.getUserInfo(tokens[2]);
                    message.getChannel().sendMessage("```" + user.toString() + "```");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("```");
                    for (String[] cur : options){
                        sb.append(String.format("%s\t\t%s\n", cur[0], cur[1]));
                    }
                    sb.append("```");
                    message.getChannel().sendMessage(sb.toString());
                }
            } else {
                message.getChannel().sendMessage(String.format("\"%s\" is an invalid command.",
                        message.getContent().substring(1)));
            }
        } catch (Exception ex){

        }
    }
}