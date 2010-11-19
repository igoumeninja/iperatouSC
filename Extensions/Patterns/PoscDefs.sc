/* 
Draft of simple class for pattern definition for a project 

PoscDefinition.load;
Pdef(\sketchPat).play;
Pdef(\sketchPat).stop
PoscDefinition.message.source = Pseq([['/testMessage', 54321], [['/voice1', 54321], ['/voice2', 54321]]], inf); // Send one msg and two msg
PoscDefinition.message.source = Pseq([[['/voice1', 54321], ['/voice2', 54321], ['/voice3', 54321]]], inf); // Send a packete of three mesg

PoscDefinition.message.source = Ptuple(['interactWithSketch', 'padXY', Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).mirror, inf)] , inf) 
PoscDefinition.duration.source = Pseq(Array.geom(82, 0.003, 1.05).pyramid(2),inf); 
PoscDefinition.duration.source = Pseq([1],inf); 

PoscDefinition.address = NetAddr("127.0.0.1", 57121);
*/

PoscDefinition {
	classvar <message, <duration;	//, <address;
	
	*load {
		//address = NetAddr("127.0.0.1", 57120);
		
		
		Event.addEventType(\osc, {
			if (~msg.rank > 1) {
				~msg do: ~dest.sendBundle(~latency, _)
			}{
				~dest.sendBundle(~latency, ~msg);
			}
		});
		message = PatternProxy( 
					Pseq([[['/voice1', 54321], ['/voice2', 54321], ['/voice3', 54321]]], inf)
				);
		duration = PatternProxy(Pseq([1],inf));
		Pdef(\sketchPat, Posc(
				\dur, duration,
				\msg, message
			);
		);	
		
	}
}