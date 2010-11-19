/* 
Pattern definition

edit IZ draft by Aris Bezas Fri, 19 November 2010, 02:58PM
*/

PoscDef {
	classvar <message, <duration;
	
	*load {
		Event.addEventType(\osc, {
			if (~msg.rank > 1) {
				~msg do: ~dest.sendBundle(~latency, _)
			}{
				~dest.sendBundle(~latency, ~msg);
			}
		});
		message = PatternProxy( 
					Pseq([[['/voice1',1],['/voice2',2], ['/voice3',3]]], inf)
				);
		duration = PatternProxy(Pseq([1],inf));
		Pdef(\sketchPat, Posc(
				\dur, duration,
				\msg, message
			);
		);	
		
	}
}

/*
##########################################
##########	Implementation	#########
##########################################
PoscDef.load;
Pdef(\sketchPat).play;
Pdef(\sketchPat).stop
PoscDef.message.source = Pseq([['/testMessage',123], [['/voice1', 54321], ['/voice2', 54321]]], inf); // Send one msg and two msg
PoscDef.message.source = Ptuple(['interactWithSketch', 'padXY', Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).mirror, inf)] , inf) 
PoscDef.message.source = Ptuple(['msg1', Pseq([1,2], inf)] , inf); 

PoscDef.message.source = 
	Ptuple(
		[	
			Ptuple(['msg1', Pseq([10,100,1000], inf)] , inf),
			Ptuple(['msg2', Pseq([20,200,2000], inf)] , inf),
			Ptuple(['msg3', Pseq([30,300,3000], inf)] , inf)
		], inf) ;

		
PoscDef.duration.source = Pseq(Array.geom(82, 0.003, 1.02).pyramid(2),inf); 
PoscDef.duration.source = Pseq([0.2],inf); 

##########################################
##########	openFrameworks	#########
##########################################


PoscDef.message.source = 
	Ptuple(
		[	
			Ptuple(['rgb', 'r8', Pseq([10,100,1000], inf)] , inf),
			Ptuple(['rgb', 'g8', Pseq([20,200,2000], inf)] , inf),
			Ptuple(['rgb', 'a8', Pseq([30,300,3000], inf)] , inf)
		], inf) ;


*/
