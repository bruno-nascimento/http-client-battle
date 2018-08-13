package br.com.labbs.workout.httpclientbattle.infra;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import br.com.labbs.workout.httpclientbattle.loader.Requester;

public enum Akka {

    INSTANCE;

    private ActorSystem system = ActorSystem.create("http-client-battle");

    public ActorSystem getSystem() {
        return system;
    }

    public ActorRef getRequester() {
        return Akka.INSTANCE.getSystem().actorOf(FromConfig.getInstance().props(Props.create(Requester.class)), "requester");
    }


}
