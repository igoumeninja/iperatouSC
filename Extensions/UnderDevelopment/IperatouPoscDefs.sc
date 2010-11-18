/* 
Draft of simple class for pattern definition for a project 

IperatouPoscDefs.load;
Pdef(\sketchPat).play;
Pdef(\sketchPat).stop
IperatouPoscDefs.sketchTuple.source = Pseq([['/testMessage', 54321], [['/voice1', 54321], ['/voice2', 54321]]], inf);
IperatouPoscDefs.sketchDur.source = Pseq(Array.geom(82, 0.003, 1.05).pyramid(2),inf); 
IperatouPoscDefs.sketchDur.source = Pseq([1],inf); 
*/

IperatouPoscDefs {
	classvar <sketchTuple, <sketchDur;
	*load {
		var addr;
		addr = NetAddr("127.0.0.1", 57120);
		
		Event.addEventType(\osc, {
			if (~msg.rank > 1) {
				~msg do: ~dest.sendBundle(~latency, _)
			}{
				~dest.sendBundle(~latency, ~msg);
			}
		});
		sketchTuple = PatternProxy( 
					Ptuple(['interactWithSketch', 'padXY', Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).mirror, inf)] , inf) 
				);
		sketchDur = PatternProxy(Pseq(Array.geom(82, 0.003, 1.05).pyramid(2),inf));		Pdef(\sketchPat, Posc(
				\dur, sketchDur,
				\msg, sketchTuple
			);
		);	
		
	}
}