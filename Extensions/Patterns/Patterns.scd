/*
Patterns

Aris Bezas Fri, 12 November 2010, 03:14AM
*/
//##### SKETCH PATTERN with Posc#####
Posc(
	\msg, Pseq([[OF.rgb("r6", 0);], [OF.rgb("r6", 0);]], inf)
	).play;

~pat = Pdef(\sketchPat, Pbind(
		\type, \osc, 
		\dur, 0.25,
	 	\dest, ~addr,
		\msg, ~sketchTuple
	);
);	


//##### SKETCH PATTERN #####

~addr = NetAddr("127.0.0.1", 57120);
Event.addEventType(\osc, {
	if (~msg.rank > 1) {
		~msg do: ~dest.sendBundle(~latency, _)
	}{
		~dest.sendBundle(~latency, ~msg);
	}
});




~sketchTuple = PatternProxy( 
			Ptuple(['takis', 'padXY', Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).mirror, inf)] , inf) 
		);
~sketchDur = PatternProxy(Pseq(Array.geom(82, 0.003, 1.05).pyramid(2),inf));		

~pat = Pdef(\sketchPat, Pbind(
		\type, \osc, 
		\dur, 0.25,
	 	\dest, ~addr,
		\msg, ~sketchTuple
	);
);	


~pat.play;

IperatouPatternsDefs.sketchTuple.source = Pseq([["/testMessage", 123]], inf);
~sketchTuple.source = Pn(["/testMessage", 123], inf);
~sketchTuple.source = Pseq([["/testMessage", 54321], [["/voice1", 54321], ["/voice2", 54321]]], inf);


b = Pbind(\degree, Pseq([1, 2, 3, [0, 2], 1, 0], inf));
b.play;



~sketchDur.source = Pseq([0.01],inf);
~sketchDur.source = Pseq(Array.series(0.01, 0.03, 0.037),inf);
~sketchDur.source = Pseq(Array.geom(25, 0.003, 1.2).pyramid(2),inf);
~sketchTuple.source =  Ptuple(["interactWithSketch", "padXY", Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).reverse, inf)] , inf);
~sketchTuple.source =  Ptuple(["interactWithSketch", "padXY", Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(100, 10, 1000/100).mirror, inf)] , inf);
~sketchTuple.source =  Ptuple(["interactWithSketch", "padXY", Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).reverse, inf)] , inf);
~sketchTuple.source =  Ptuple(["interactWithSketch", "padXY", Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).reverse, inf)] , inf);

Pdef(\sketchPat).play;	
Pdef(\sketchPat).stop

//##### SKETCH BW PATTERN #####
~sketchBWTuple = PatternProxy( 
			Ptuple(["rgb", "sketchBW", Pseq([0, 255], inf), Pseq([0, 255], inf), Pseq([0, 255], inf)]) 
		);
~sketchBWDur = PatternProxy(Pseq([0.1,0.1],inf));		

Pdef(\sketchBWPat,Pbind(
		\type, \osc, 
		\dur, ~sketchBWDur,
	 	\dest, ~addr,
		\msg, ~sketchBWTuple
);
);	
~sketchBWDur.source = Pseq([0.01],inf);
~sketchBWDur.source = Pseq(Array.series(0.01, 0.03, 0.037),inf);
~sketchBWDur.source = Pseq(Array.geom(25, 0.003, 1.2).pyramid(2),inf);
~sketchBWTuple.source =  Ptuple(["interactWithSketch", "padXY", Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).reverse, inf)] , inf);
~sketchBWTuple.source =  Ptuple(["interactWithSketch", "padXY", Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(100, 10, 1000/100).mirror, inf)] , inf);
~sketchBWTuple.source =  Ptuple(["interactWithSketch", "padXY", Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).reverse, inf)] , inf);
~sketchBWTuple.source =  Ptuple(["interactWithSketch", "padXY", Pseq(Array.series(1000, 0, 1).scramble, inf), Pseq(Array.series(200, 10, 1000/200).reverse, inf)] , inf);

Pdef(\sketchBWPat).play;	
Pdef(\sketchBWPat).stop

//##### OMER TECHNO #####
~tuple.source = Ptuple(["interactWithSound", "glBeginType",Pseq([0.2, 1], inf)] , inf);
~omerSeq.source = Pseq([1] , inf);
~omerSeq.source = Pseq(Array.geom(25, 0.003, 1.2).pyramid(2),inf);
~omerSeq.source = Pseq([ Pseq([1, 0.0, 0.0, 0, 0.0, 0.4, 0, 0.6, 0] , 3), Pseq([0.0, 0, 0.1, 0, 0.4, 0.8, 0, 0.6, 0] , 1)] , inf);
			
Pdef(\omerTechno).play;	
Pdef(\omerTechno).stop;	


