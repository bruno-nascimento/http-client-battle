package br.com.labbs.workout.httpclientbattle.loader;

import akka.actor.AbstractActor;
import akka.dispatch.Futures;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.labbs.workout.httpclientbattle.metrics.MetricCollector;
import br.com.labbs.workout.httpclientbattle.shared.HttpClient;
import io.prometheus.client.SimpleTimer;
import scala.concurrent.ExecutionContext;


public class Requester extends AbstractActor {
    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    ExecutionContext ec = getContext().getSystem().dispatchers().lookup("async-requester-dispatcher");

    HttpClient client;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(br.com.labbs.workout.httpclientbattle.shared.HttpClient.class, client -> {
//                log.info("Cliente http registrado : "+client.getClientName());
                this.client = client;
            })
            .match(Integer.class, i -> {
                if(i % 100 == 0){
                    log.info("1) mensagem recebida = "+i);
                }
                Futures.future(() -> {
                    SimpleTimer requestTimer = new SimpleTimer();
                    int responseStatusCode = client.doRequest(i);
                    MetricCollector.INSTANCE.time(client.getClientName(), responseStatusCode, requestTimer.elapsedSeconds());
                    MetricCollector.INSTANCE.inc(client.getClientName(), responseStatusCode);
//                    log.info("mensagem processada = "+i);
                    if(i % 100 == 0){
                        log.info("3) future terminado = "+i);
                    }
                    return null;
                }, ec);
                if(i % 100 == 0){
                    log.info("2) future executado = "+i);
                }
            })
            .build();
    }
}
