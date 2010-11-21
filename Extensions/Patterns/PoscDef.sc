/* 
Pattern definition

edit IZ draft by Aris Bezas Fri, 19 November 2010, 02:58PM
*/

PoscDef {
	classvar <msg0, <dur0, <msg2, <dur2;
	classvar <msg1, <dur1;
	
	*load {
		Event.addEventType(\osc, {
			if (~msg.rank > 1) {
				~msg do: ~dest.sendBundle(~latency, _)
			}{
				~dest.sendBundle(~latency, ~msg);
			}
		});
		msg0 = msg1 = PatternProxy( Pseq([[['/voice1',1],['/voice2',2], ['/voice3',3]]], inf));
		dur0 = dur1 = PatternProxy(Pseq([1],inf));
		PoscDef.msg0.source = 								Ptuple(													[													Ptuple(['particle', 'push', Pseq([100,100,100], inf), Pseq([100,300,500], inf)], inf),						Ptuple(['particle', 'forceRadius', Pseq([100,200,300], inf)], inf),						Ptuple(['particle', 'particleNeighborhood', Pseq([10,20,30], inf)], inf),						Ptuple(['rgb', 'particle', Pseq([0,255], inf), Pseq([0,255], inf), Pseq([0,255], inf)], inf)					], inf) ;
		
		
		Pdef(\myPat0, Posc(
				\dur, dur0,
				\msg, msg0
			);
		);	
		Pdef(\myPat1, Posc(
				\dur, dur1,
				\msg, msg1
			);
		);	
		
	}
}

/*
##########################################
##########	Implementation	#########
##########################################
PoscDef.load;
Pdef(\myPat).play;
Pdef(\myPat).stop
PoscDef.msg.source = Pseq([['/testmsg',123], [['/voice1', 54321], ['/voice2', 54321]]], inf); // Send one msg and two msg
PoscDef.msg.source = Ptuple(['interactWithSketch', 'padXY', Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).mirror, inf)] , inf) 
PoscDef.msg.source = Ptuple(['msg1', Pseq([1,2], inf)] , inf); 

PoscDef.msg.source = 
	Ptuple(
		[	
			Ptuple(['msg1', Pseq([10,100,1000], inf)] , inf),
			Ptuple(['msg2', Pseq([20,200,2000], inf)] , inf),
			Ptuple(['msg3', Pseq([30,300,3000], inf)] , inf)
		], inf) ;

		
PoscDef.dur.source = Pseq(Array.geom(82, 0.003, 1.02).pyramid(2),inf); 
PoscDef.dur.source = Pseq([0.2],inf); 

##########################################
##########	openFrameworks	#########
##########################################


PoscDef.msg.source = 
	Ptuple(
		[	
			Ptuple(['rgb', 'r8', Pseq([10,100,1000], inf)] , inf),
			Ptuple(['rgb', 'g8', Pseq([20,200,2000], inf)] , inf),
			Ptuple(['rgb', 'a8', Pseq([30,300,3000], inf)] , inf)
		], inf) ;


*/
