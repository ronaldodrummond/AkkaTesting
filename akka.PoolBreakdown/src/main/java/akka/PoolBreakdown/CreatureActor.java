package akka.PoolBreakdown;

import Creature.EyeActor;
import Creature.MouthActor;
import Creature.NoseActor;
import Stimuli.*;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

public class CreatureActor extends UntypedActor {
	private final ActorRef mouth = getContext().actorOf(new Props(MouthActor.class).withRouter(new RoundRobinRouter(5)),"mouth");
	private final ActorRef nose = getContext().actorOf(new Props(NoseActor.class).withRouter(new RoundRobinRouter(5)),"nose");
	private final ActorRef eye = getContext().actorOf(new Props(EyeActor.class).withRouter(new RoundRobinRouter(5)),"eye");
	
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		if(arg0 instanceof SmellStimulusMessage) {
			System.out.println("Smell stimulus received. Forwarding to nose...");
			nose.tell(arg0);
		} else if(arg0 instanceof LuminousStimulusMessage) {
			System.out.println(this.getSelf().toString()+": Luminous stimulus received. Forwarding to eye...");
			eye.tell(arg0);
		} else if(arg0 instanceof PheromoneStimulusMessage) {
			System.out.println(this.getSelf().toString()+": Pheromone stimulus received. Forwarding to nose...");
			nose.tell(arg0);
		} else if(arg0 instanceof SpikeStimulusMessage) {
			System.out.println(this.getSelf().toString()+": Spike stimulus received. Forwarding to nose...");
			mouth.tell(arg0);
		} else if(arg0 instanceof TickleStimulusMessage) {
			System.out.println(this.getSelf().toString()+": Tickle stimulus received. Forwarding to nose...");
			mouth.tell(arg0);
		} else if(arg0 instanceof EnergeticStimulusMessage) {
			System.out.println(this.getSelf().toString()+": Energetic stimulus received. Forwarding to nose...");
			mouth.tell(arg0);
		} else if(arg0 instanceof MechanicalStimulusMessage) {
			System.out.println(this.getSelf().toString()+": Mechanical stimulus received. Forwarding to nose...");
			mouth.tell(arg0);
		} if(arg0 instanceof StimulusMessage) {
			System.out.println(this.getSelf().toString()+": Unknown stimulus received.\n"+((StimulusMessage)arg0).getMessage()+"\nDiscarding... Ref.: " + ((StimulusMessage) arg0).getSequenceNumber());
		} else {
			throw new Exception(this.getSelf().toString()+": Message type not supported.");
		}
		
	}

}