package br.com.labbs.workout.httpclientbattle;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.routing.Broadcast;
import br.com.labbs.workout.httpclientbattle.infra.Akka;
import br.com.labbs.workout.httpclientbattle.infra.ClientScanner;
import br.com.labbs.workout.httpclientbattle.metrics.MetricCollector;
import br.com.labbs.workout.httpclientbattle.shared.Env;
import br.com.labbs.workout.httpclientbattle.shared.HttpClient;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpClient httpClient = ClientScanner.get();
        MetricCollector.INSTANCE.init(httpClient.getClientName());
        final ActorRef requester = Akka.INSTANCE.getRequester();
        requester.tell(new Broadcast(httpClient), requester);
        TimeUnit.SECONDS.sleep(5);

        System.out.println("disparando "+Env.MAX_REQUEST_PER_MINUTE.getInt() +" para os workers");
        IntStream.range(0, Env.MAX_REQUEST_PER_MINUTE.getInt()).parallel().forEach(i -> requester.tell(i, requester));
        System.out.println("disparado e indo dormir");

        TimeUnit.SECONDS.sleep(Env.TEST_DURATION_SECONDS.getInt());
        System.out.println("################### ----------- acordou e vai morrer");

        requester.tell(new Broadcast(PoisonPill.getInstance()), ActorRef.noSender());
        Akka.INSTANCE.getSystem().terminate();
        TimeUnit.SECONDS.sleep(5);
        System.exit(0);

    }
}
